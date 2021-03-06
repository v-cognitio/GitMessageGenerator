package com.v_cognitio.GitMessageGenerator.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.ui.Refreshable;
import com.v_cognitio.GitMessageGenerator.engine.CommitDialog;
import com.v_cognitio.GitMessageGenerator.utils.Settings;
import com.v_cognitio.GitMessageGenerator.utils.Utils;
import org.jetbrains.annotations.Nullable;

public class CustomCommitAction extends AnAction implements DumbAware {

    private Settings settings = new Settings();

    public CustomCommitAction() {
        try {
            Utils.loadProperties(
                    getClass().getClassLoader().getResourceAsStream("config.properties"),
                    settings
            );

            settings.handler = Utils.getFieldsHandler(
                    getClass().getClassLoader().getResourceAsStream("panel.json")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        final CommitMessageI commitPanel = getCommitPanel(actionEvent);
        if (commitPanel == null) {
            return;
        }
        CommitDialog dialog = new CommitDialog(actionEvent.getProject(), settings);
        dialog.show();
        if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            commitPanel.setCommitMessage(dialog.getCommitMessage().toString());
        }
    }

    @Nullable
    private static CommitMessageI getCommitPanel(@Nullable AnActionEvent e) {
        if (e == null) {
            return null;
        }
        Refreshable data = Refreshable.PANEL_KEY.getData(e.getDataContext());
        if (data instanceof CommitMessageI) {
            return (CommitMessageI) data;
        }
        return VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(e.getDataContext());
    }
}
