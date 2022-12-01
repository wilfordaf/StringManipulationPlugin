package org.itmo.stringmanipulation.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public abstract class StringManipulationAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        if (editor == null)
            return;

        Project project = event.getRequiredData(PlatformDataKeys.PROJECT);
        Document document = editor.getDocument();

        Caret caret = editor.getCaretModel().getPrimaryCaret();
        int start = caret.getSelectionStart();
        int end = caret.getSelectionEnd();

        String text = caret.getSelectedText();
        if (text == null)
        {
            Messages.showMessageDialog(
                    "No text selected",
                    "String Manipulation Failed",
                    Messages.getErrorIcon());
            return;
        }

        WriteCommandAction.runWriteCommandAction(project, () ->
                document.replaceString(start, end, getTransformedText(text)));

        caret.removeSelection();
    }

    public abstract String getTransformedText(@NotNull String text);
}
