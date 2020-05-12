package com.v_cognitio.GitMessageGenerator;

import org.apache.commons.lang.StringUtils;

public class CommitMessage {

    private final String content;

    public CommitMessage(String type, String changeScope, String shortDescription, String longDescription, String closedIssues, String breakingChanges) {
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
                                String closedIssues
    ) {

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
        String DEFAULT_TEMPLATE =
                "#if($type)${type}#end\n" +
                "#if($scope)(${scope})#end: #if($subject)${subject}#end\n" +
                "${newline}\n" +
                "#if($body)${body}#end\n" +
                "${newline}\n" +
                "#if($changes)BREAKING CHANGE: ${changes}#end\n" +
                "${newline}\n" +
                "#if($closes)Closes ${closes}#end\n";

        return VelocityUtils.convert(DEFAULT_TEMPLATE, commitTemplate);
    }

    @Override
    public String toString() {
        return content;
    }
}