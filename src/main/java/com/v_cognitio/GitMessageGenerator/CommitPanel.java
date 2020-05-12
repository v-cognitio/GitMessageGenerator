package com.v_cognitio.GitMessageGenerator;

import com.intellij.openapi.project.Project;

import javax.swing.*;


public class CommitPanel {
    private JPanel mainPanel;
    private JTextField type;
    private JTextField changeScope;
    private JTextField shortDescription;
    private JTextArea longDescription;
    private JTextField closedIssues;
    private JTextArea breakingChanges;

    private Settings settings;
    private Project project;

    public CommitPanel(Project project, Settings settings) {
        this.settings = settings;
    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    CommitMessage getCommitMessage() {
        return new CommitMessage(
                settings,
                type.getText().trim(),
                changeScope.getText().trim(),
                shortDescription.getText().trim(),
                longDescription.getText().trim(),
                closedIssues.getText().trim(),
                breakingChanges.getText().trim()
        );
    }

}