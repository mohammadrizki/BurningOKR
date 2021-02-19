package org.burningokr.model.okrUnits;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

import lombok.*;
import org.burningokr.model.okr.OkrTopicDraft;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class OkrBranch extends OkrChildUnit implements OkrParentUnit {

  @OneToMany(
      mappedBy = "parentOkrUnit",
      cascade = CascadeType.REMOVE,
      targetEntity = OkrChildUnit.class)
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  @EqualsAndHashCode.Exclude
  protected Collection<OkrChildUnit> okrChildUnits = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "okr_topic_drafts_okr_unit",
      joinColumns = @JoinColumn(name = "unit_id"),
      inverseJoinColumns = @JoinColumn(name = "okr_topic_draft_id")
  )
  protected Collection<OkrTopicDraft> okrTopicDrafts = new ArrayList<>();

  public boolean hasDepartments() {
    return !okrChildUnits.isEmpty();
  }

  @Override
  public Collection<OkrChildUnit> getOkrChildUnits() {
    return okrChildUnits;
  }

  @Override
  public void setOkrChildUnits(Collection<OkrChildUnit> subDepartments) {
    this.okrChildUnits = subDepartments;
  }

  /**
   * Creates a copy of the OkrBranch without relations.
   *
   * <p>The values that are copied are:
   *
   * <ul>
   *   <li>Name
   *   <li>label</li>
   *   <li>active</li>
   * </ul>
   *
   * @return a copy of the OkrBranch without relations
   */
  public OkrBranch getCopyWithoutRelations() {
    OkrBranch copy = new OkrBranch();
    copy.setName(this.getName());
    copy.setLabel(this.getLabel());
    copy.setActive(this.isActive);
    return copy;
  }
}
