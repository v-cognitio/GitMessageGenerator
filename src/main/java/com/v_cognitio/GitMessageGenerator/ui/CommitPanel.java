package com.v_cognitio.GitMessageGenerator.ui;

import com.intellij.openapi.project.Project;
import com.intellij.util.containers.hash.HashMap;
import com.v_cognitio.GitMessageGenerator.model.CommitMessage;
import com.v_cognitio.GitMessageGenerator.utils.Settings;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CommitPanel {

    private JPanel mainPanel;

    private Map<String, JTextComponent> componentList = new HashMap<>();

    private Settings settings;
    private Project project;

    private int minPanelSize;

    public CommitPanel(Project project, Settings settings) {
        this.settings = settings;
        this.minPanelSize = settings.panelBaseSize;

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        new PanelBuilder(this, settings).build();

        mainPanel.setMinimumSize(new Dimension(settings.minimalTextFieldWidth, minPanelSize));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void addElement(Component comp, Object constraints, int elementSize) {
        mainPanel.add(comp, constraints);
        minPanelSize += elementSize;
    }

    public void addTextComponent(String name, JTextComponent component) {
        componentList.put(name, component);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CommitMessage getCommitMessage() {
        return new CommitMessage(settings, componentList);
    }

}