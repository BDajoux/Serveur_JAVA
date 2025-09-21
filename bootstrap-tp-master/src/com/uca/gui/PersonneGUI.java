package com.uca.gui;

import com.uca.core.PersonneCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PersonneGUI {

    public static String getAllPersonnes() throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("personnes", PersonneCore.getAllPersonnes());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("personnes/personnes.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
