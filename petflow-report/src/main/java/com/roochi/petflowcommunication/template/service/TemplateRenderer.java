package com.roochi.petflowcommunication.template.service;

import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface TemplateRenderer {

    String render(String template, Map<String, Object> variables);
}
