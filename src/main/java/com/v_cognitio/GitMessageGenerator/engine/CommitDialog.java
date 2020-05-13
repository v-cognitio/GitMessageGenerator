package com.v_cognitio.GitMessageGenerator.engine;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.v_cognitio.GitMessageGenerator.ui.CommitPanel;
import com.v_cognitio.GitMessageGenerator.utils.Settings;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CommitDialog extends DialogWrapper {

    private final CommitPanel panel;

    public CommitDialog(@Nullable Project project, Settings settings) {
        super(project);
        this.setResizable(false);
        panel = new CommitPanel(project, settings);
        setTitle("Commit");
        setOKButtonText("Ok");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel.getMainPanel();
    }

    public CommitMessage getCommitMessage() {
        return panel.getCommitMessage();
    }

}