CREATE TABLE public.okr_topic_drafts_okr_unit
(
    unit_id bigint,
    okr_topic_draft_id bigint,
    CONSTRAINT pk_unit_id_okr_topic_draft_id PRIMARY KEY (unit_id, okr_topic_draft_id),
    CONSTRAINT fk_unit_id FOREIGN KEY (unit_id) REFERENCES okr_unit(id),
    CONSTRAINT fk_okr_topic_draft_id FOREIGN KEY (okr_topic_draft_id) REFERENCES okr_topic_draft(id)
);

ALTER TABLE public.okr_topic_draft DROP COLUMN parent_unit_id;
