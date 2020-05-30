package com.v_cognitio.GitMessageGenerator.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

public class PanelFieldHandler {

    @JsonDeserialize(as = ArrayList.class)
    public List<PanelField> fields;
}
