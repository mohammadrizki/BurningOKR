package org.burningokr.dto.okr;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class OkrTopicDraftDto extends OkrTopicDescriptionDto {
  private Collection<Long> okrParentUnitIds;
}
