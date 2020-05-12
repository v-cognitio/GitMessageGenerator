package com.v_cognitio.GitMessageGenerator.engine;

import com.v_cognitio.GitMessageGenerator.utils.Settings;
import com.v_cognitio.GitMessageGenerator.utils.Utils;
import org.apache.commons.lang.StringUtils;

public class CommitMessage {

    private final String content;
    private Settings settings;

    public CommitMessage(Settings settings,
                         String type,
                         String changeScope,
                         String shortDescription,
                         String longDescription,
                         String closedIssues,
                         String breakingChanges) {
        this.settings = settings;
        this.content = buildContent(
                type,
                changeScope,
                shortDescription,
                longDescription,
                breakingChanges,
                closedIssues
        );
    }

    private String buildContent(String type,
                                String changeScope,
                                String shortDescription,
                                String longDescription,
                                String breakingChanges,
                                String closedIssues) {
        CommitTemplate commitTemplate = new CommitTemplate();

        if (StringUtils.isNotBlank(type)) {
            commitTemplate.setType(type);
        }
        if (StringUtils.isNotBlank(changeScope)) {
            commitTemplate.setScope(changeScope);
        }
        if (StringUtils.isNotBlank(shortDescription)) {
            commitTemplate.setSubject(shortDescription);
        }
        if (StringUtils.isNotBlank(longDescription)) {
            commitTemplate.setBody(longDescription);
        }
        if (StringUtils.isNotBlank(breakingChanges)) {
            commitTemplate.setChanges(breakingChanges);
        }
        if (StringUtils.isNotBlank(closedIssues)) {
            commitTemplate.setCloses(closedIssues);
        }

        return Utils.convert(settings.messageTemplate, commitTemplate);
    }

    @Override
    public String toString() {
        return content;
    }
}