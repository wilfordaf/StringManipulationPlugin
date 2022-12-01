package org.itmo.stringmanipulation.actions;

import org.jetbrains.annotations.NotNull;

public class CamelCaseAction extends StringManipulationAction {

    @Override
    public String getTransformedText(@NotNull String text) {
        var builder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '_' && ++i < text.length())
                builder.append(Character.toUpperCase(text.charAt(i)));
            else
                builder.append(c);
        }

        return builder.toString();
    }
}
