package com.v_cognitio.GitMessageGenerator.ui;

import com.intellij.openapi.project.Project;
import com.v_cognitio.GitMessageGenerator.model.CommitMessage;
import com.v_cognitio.GitMessageGenerator.utils.Settings;

import javax.swing.*;
import java.awt.*;


public class CommitPanel {

    private static final int DEFAULT_SCROLLABLE_SIZE = 120;

    private JPanel mainPanel;
    private JTextField type;
    private JTextField changeScope;
    private JTextField shortDescription;
    private JTextArea longDescription;
    private JTextField closedIssues;
    private JTextArea breakingChanges;

    private Settings settings;
    private Project project;

    private int minPanelSize;

    public CommitPanel(Project project, Settings settings) {
        this.settings = settings;
        this.minPanelSize = settings.panelBaseSize;

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


        mainPanel.setMinimumSize(new Dimension(settings.minimalTextFieldWidth, minPanelSize));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void addElement(Component comp, Object constraints, int elementSize) {
        mainPanel.add(comp, constraints);
        minPanelSize += elementSize;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CommitMessage getCommitMessage() {
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