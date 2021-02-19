CREATE TABLE okr_topic_draft
(
    id bigint NOT NULL,
    CONSTRAINT pk_topic_draft_id PRIMARY KEY(id),
    CONSTRAINT fk_topic_draft_id FOREIGN KEY(id) REFERENCES okr_topic_description(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE okr_topic_drafts_okr_unit
(
    unit_id bigint,
    okr_topic_draft_id bigint,
    CONSTRAINT pk_unit_id_okr_topic_draft_id PRIMARY KEY (unit_id, okr_topic_draft_id),
    CONSTRAINT fk_unit_id FOREIGN KEY (unit_id) REFERENCES okr_unit(id),
    CONSTRAINT fk_okr_topic_draft_id FOREIGN KEY (okr_topic_draft_id) REFERENCES okr_topic_draft(id)
);
