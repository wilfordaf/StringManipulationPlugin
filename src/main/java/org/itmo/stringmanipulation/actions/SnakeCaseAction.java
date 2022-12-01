package org.itmo.stringmanipulation.actions;

import org.jetbrains.annotations.NotNull;

public class SnakeCaseAction extends StringManipulationAction {

    @Override
    public String getTransformedText(@NotNull String text) {
        var builder = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c))
                builder.append('_').append(Character.toLowerCase(c));
            else
                builder.append(c);
        }

        return builder.toString();
    }
}
