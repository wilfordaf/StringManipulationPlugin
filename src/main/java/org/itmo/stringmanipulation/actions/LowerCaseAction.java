package org.itmo.stringmanipulation.actions;

import org.jetbrains.annotations.NotNull;

public class LowerCaseAction extends StringManipulationAction {

    @Override
    public String getTransformedText(@NotNull String text) {
        return text.toLowerCase();
    }
}
