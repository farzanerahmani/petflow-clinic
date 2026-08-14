package com.roochi.petflowcommunication.template.service.impl;

import com.roochi.petflowcommunication.template.service.TemplateRenderer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Component
public class TemplateRendererImpl implements TemplateRenderer {

    private static final Pattern VARIABLE_PATTERN =
            Pattern.compile("\\{\\{\\s*([a-zA-Z0-9_.-]+)\\s*}}");

    @Override
    public String render(
            String template,
            Map<String, Object> variables
    ) {

        if (template == null || template.isBlank()) {
            return template;
        }

        if (variables == null || variables.isEmpty()) {
            return template;
        }

        Matcher matcher =
                VARIABLE_PATTERN.matcher(template);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {

            String variableName = matcher.group(1);

            Object value =
                    variables.get(variableName);

            String replacement =
                    value != null
                            ? Matcher.quoteReplacement(
                            String.valueOf(value)
                    )
                            : matcher.group(0);

            matcher.appendReplacement(
                    result,
                    replacement
            );
        }

        matcher.appendTail(result);

        return result.toString();
    }
}
