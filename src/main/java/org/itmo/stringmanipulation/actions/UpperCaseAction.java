package org.itmo.stringmanipulation.actions;

import org.jetbrains.annotations.NotNull;

public class UpperCaseAction extends StringManipulationAction {

    @Override
    public String getTransformedText(@NotNull String text) {
        return text.toUpperCase();
    }
}
