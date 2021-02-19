package org.burningokr.model.okr;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.burningokr.model.okrUnits.OkrParentUnit;
import org.burningokr.model.okrUnits.OkrUnit;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class OkrTopicDraft extends OkrTopicDescription {

  @ManyToMany
  @JoinTable(
      name = "okr_topic_drafts_okr_unit",
      joinColumns = @JoinColumn(name = "okr_topic_draft_id"),
      inverseJoinColumns = @JoinColumn(name = "unit_id")
  )
  private Collection<OkrUnit> okrParentUnits = new ArrayList<>();
}
