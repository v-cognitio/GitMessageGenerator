package com.v_cognitio.GitMessageGenerator;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;

import javax.swing.*;
import java.io.File;
import java.util.List;


public class CommitPanel {
    private JPanel mainPanel;
    private JTextField type;
    private JTextField changeScope;
    private JTextField shortDescription;
    private JTextArea longDescription;
    private JTextField closedIssues;
    private JTextArea breakingChanges;

    public CommitPanel(Project project) {
        //parameter
    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    CommitMessage getCommitMessage() {
        return new CommitMessage(
                type.getText().trim(),
                changeScope.getText().trim(),
                shortDescription.getText().trim(),
                longDescription.getText().trim(),
                closedIssues.getText().trim(),
                breakingChanges.getText().trim()
        );
    }

}