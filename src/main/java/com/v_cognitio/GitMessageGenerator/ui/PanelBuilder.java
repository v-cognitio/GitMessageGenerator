package com.v_cognitio.GitMessageGenerator.ui;

import com.intellij.util.ui.JBUI;
import com.v_cognitio.GitMessageGenerator.model.PanelField;
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

    public void build() {
        for (PanelField field : settings.handler.fields) {
            switch (field.fieldType) {
                case DEFAULT_TEXT:
                    addNewDefaultTextArea(field.fieldName);
                    break;
                case SCROLLABLE:
                    addNewScrollableTextArea(field.fieldName);
                    break;
            }
        }
    }

    private void addNewDefaultTextArea(String text) {
        GridBagConstraints c1 = getLabelConstraints();

        GridBagConstraints c2 = getTextFieldConstraints();
        c2.weighty = 1.0;

        JTextField area = new JTextField();
        area.setPreferredSize(new Dimension(-1, settings.defaultTextFieldHeight));

        panel.addElement(new JLabel(text), c1, 0);
        panel.addElement(area, c2, settings.defaultTextFieldHeight);
        panel.addTextComponent(text, area);
    }

    private void addNewScrollableTextArea(String text) {
        GridBagConstraints c1 = getLabelConstraints();

        GridBagConstraints c2 = getTextFieldConstraints();
        c2.weighty = 0.0;
        c2.gridwidth = settings.defaultTextFieldHeight;

        JTextArea area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(-1,
                settings.defaultScrollableHeight + settings.defaultTextBottomInset));

        panel.addElement(new JLabel(text), c1, 0);
        panel.addElement(scroll, c2, settings.defaultScrollableHeight);
        panel.addTextComponent(text, area);
    }

    private GridBagConstraints getDefaultConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.CENTER;
        c.gridy = lastGridY;

        return c;
    }

    private GridBagConstraints getTextFieldConstraints() {
        GridBagConstraints c = getDefaultConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1; c.gridy = lastGridY++;
        c.weightx = 1.0;
        c.insets = JBUI.insetsBottom(settings.defaultTextBottomInset);

        return c;
    }

    private GridBagConstraints getLabelConstraints() {
        GridBagConstraints c = getDefaultConstraints();
        c.insets = JBUI.insetsRight(settings.defaultLabelRightInset);

        return c;
    }
}
