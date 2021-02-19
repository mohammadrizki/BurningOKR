package org.burningokr.model.okrUnits;

import org.burningokr.model.okr.OkrTopicDraft;

import java.util.Collection;

public interface OkrParentUnit {
  Collection<OkrChildUnit> getOkrChildUnits();

  void setOkrChildUnits(Collection<OkrChildUnit> departments);

  Collection<OkrTopicDraft> getOkrTopicDrafts();

  void setOkrTopicDrafts(Collection<OkrTopicDraft> topicDrafts);
}
