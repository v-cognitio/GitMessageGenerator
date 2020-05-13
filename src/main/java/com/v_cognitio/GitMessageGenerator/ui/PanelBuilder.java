package com.v_cognitio.GitMessageGenerator.ui;

import com.intellij.util.ui.JBUI;
import com.v_cognitio.GitMessageGenerator.utils.Settings;

import javax.swing.*;
import java.awt.*;

public class PanelBuilder {

    private CommitPanel panel;
    private Settings settings;

    private int lastGridY = 0;

    public PanelBuilder(CommitPanel panel, Settings settings) {
        this.panel = panel;
        this.settings = settings;
    }

    private void addNewScrollableTextArea(String text) {
        GridBagConstraints c1 = new GridBagConstraints();
        c1.anchor = GridBagConstraints.NORTH;
        c1.fill = GridBagConstraints.CENTER;
        c1.gridy = lastGridY;
        c1.insets = JBUI.insetsRight(settings.defaultLabelRightInset);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.anchor = GridBagConstraints.NORTH;
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.gridx = 1; c2.gridy = lastGridY++;
        c2.weightx = 1.0; c2.weighty = 0.5;

        JTextArea area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(-1, settings.defaultScrollableHeight));

        panel.addElement(new JLabel(text), c1, 0);
        panel.addElement(scroll, c2, settings.defaultScrollableHeight);

    }
}
