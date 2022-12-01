package org.itmo.stringmanipulation.windows;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;
import org.itmo.stringmanipulation.actions.CamelCaseAction;
import org.itmo.stringmanipulation.actions.LowerCaseAction;
import org.itmo.stringmanipulation.actions.SnakeCaseAction;
import org.itmo.stringmanipulation.actions.UpperCaseAction;

import javax.swing.*;
import java.util.Map;
import java.util.Objects;

public class PluginToolWindow {

    private JPanel toolWindowContent;
    private JButton lowerButton;
    private JButton upperButton;
    private JButton snakeCaseButton;
    private JButton camelCaseButton;

    public PluginToolWindow(ToolWindow window) {
        Map<JButton, Class<? extends AnAction>> actions = Map.ofEntries(
                Map.entry(lowerButton, LowerCaseAction.class),
                Map.entry(upperButton, UpperCaseAction.class),
                Map.entry(snakeCaseButton, SnakeCaseAction.class),
                Map.entry(camelCaseButton, CamelCaseAction.class)
        );

        actions.forEach((b, t) ->
            b.addActionListener(e -> {
                try {
                    AnAction action = t.getDeclaredConstructor().newInstance();
                    action.actionPerformed(getSelectedEditorEvent());
                } catch (Exception ignored) { }
            })
        );

        window.show();
    }

    public JComponent getContent() {
        return toolWindowContent;
    }

    private AnActionEvent getSelectedEditorEvent() {
        Project currentProject = ProjectManager.getInstance().getOpenProjects()[0];
        FileEditor selectedEditor = FileEditorManager.getInstance(currentProject).getSelectedEditor();
        JComponent editorComponent = Objects.requireNonNull(selectedEditor).getComponent();
        DataContext dataContext = DataManager.getInstance().getDataContext(editorComponent);
        return new AnActionEvent(null, dataContext, "", new Presentation(), ActionManager.getInstance(), 0);
    }
}
