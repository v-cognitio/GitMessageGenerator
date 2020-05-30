package com.v_cognitio.GitMessageGenerator.model;

import com.v_cognitio.GitMessageGenerator.utils.Settings;
import com.v_cognitio.GitMessageGenerator.utils.Utils;

import javax.swing.text.JTextComponent;
import java.util.Map;

public class CommitMessage {

    private final String content;
    private Settings settings;

    public CommitMessage(Settings settings, Map<String, JTextComponent> components) {
        this.settings = settings;
        this.content = buildContent(components);
    }

    private String buildContent(Map<String, JTextComponent> components) {
        return Utils.convert(settings.messageTemplate, settings.handler, components);
    }

    @Override
    public String toString() {
        return content;
    }
}