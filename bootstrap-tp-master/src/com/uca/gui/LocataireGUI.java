package com.uca.gui;

import com.uca.core.LocataireCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class LocataireGUI {

    public static String getAllLocataires() throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("locataires", LocataireCore.getAllLocataires());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("locataires/locataires.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String locataireCreate(){
        return "";
    }

    public static String locataireDelete(){
        return "";
    }

    public static String locataireModify(){
        return "";
    }
}
