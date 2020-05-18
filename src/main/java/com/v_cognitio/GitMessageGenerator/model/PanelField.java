package com.v_cognitio.GitMessageGenerator.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class PanelField {

    public String fieldName;
    public FieldType fieldType;
}
