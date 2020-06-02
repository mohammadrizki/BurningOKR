package org.burningokr.controller.structure;

import java.util.Collection;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.burningokr.annotation.RestApiController;
import org.burningokr.dto.structure.StructureSchemaDto;
import org.burningokr.dto.structure.SubStructureDto;
import org.burningokr.mapper.interfaces.DataMapper;
import org.burningokr.mapper.structure.StructureMapperPicker;
import org.burningokr.mapper.structure.StructureSchemaMapper;
import org.burningokr.model.structures.Company;
import org.burningokr.model.structures.SubStructure;
import org.burningokr.model.users.User;
import org.burningokr.service.structure.StructureService;
import org.burningokr.service.structure.StructureServicePicker;
import org.burningokr.service.structureutil.EntityCrawlerService;
import org.burningokr.service.userhandling.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiController
@RequiredArgsConstructor
public class StructureController {

  private final StructureServicePicker<SubStructure> structureServicePicker;
  private final StructureMapperPicker mapperPicker;
  private final EntityCrawlerService entityCrawlerService;
  private final UserService userService;
  private final StructureSchemaMapper structureSchemaMapper;

  @GetMapping("/structures/{structureId}")
  public ResponseEntity<SubStructureDto> getStructureByStructureId(@PathVariable long structureId) {
    StructureService<SubStructure> structureService =
        structureServicePicker.getRoleServiceForDepartment(structureId);
    SubStructure structure = structureService.findById(structureId);
    DataMapper mapper = mapperPicker.getMapper(structure.getClass());
    return ResponseEntity.ok((SubStructureDto) mapper.mapEntityToDto(structure));
  }

  @PutMapping("/structures/{structureId}")
  public ResponseEntity<SubStructureDto> updateStructure(
      @PathVariable long structureId,
      @Valid @RequestBody SubStructureDto subStructureDto,
      User user) {
    StructureService<SubStructure> structureService =
        structureServicePicker.getRoleServiceForDepartment(structureId);

    SubStructure structure = structureService.findById(structureId);
    DataMapper mapper = mapperPicker.getMapper(structure.getClass());
    SubStructure receivedStructure = (SubStructure) mapper.mapDtoToEntity(subStructureDto);

    receivedStructure.setId(structureId);

    structureService.updateStructure(receivedStructure, user);

    return ResponseEntity.ok((SubStructureDto) mapper.mapEntityToDto(receivedStructure));
  }

  @DeleteMapping("/structures/{structureId}")
  public ResponseEntity deleteStructure(@PathVariable long structureId, User user) {
    StructureService<SubStructure> structureService =
        structureServicePicker.getRoleServiceForDepartment(structureId);
    structureService.deleteStructure(structureId, user);
    return ResponseEntity.ok().build();
  }

  /**
   * API Endpoint to get the structure of all the related Departments.
   *
   * @param structureId a long value
   * @return a {@link ResponseEntity} ok with a {@link Collection} of DepartmentStructure
   */
  @GetMapping("/structures/{structureId}/structure")
  public ResponseEntity<Collection<StructureSchemaDto>> getDepartmentStructureOfDepartment(
      @PathVariable long structureId) {
    StructureService<SubStructure> structureService =
        structureServicePicker.getRoleServiceForDepartment(structureId);
    SubStructure structure = structureService.findById(structureId);
    Company parentCompany = entityCrawlerService.getCompanyOfStructure(structure);
    UUID currentUserId = userService.getCurrentUser().getId();
    return ResponseEntity.ok(
        structureSchemaMapper.mapStructureListToStructureSchemaList(
            parentCompany.getSubStructures(), currentUserId));
  }
}
