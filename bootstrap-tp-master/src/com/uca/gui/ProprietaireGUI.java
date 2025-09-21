package com.uca.gui;

import com.uca.core.ProprietaireCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ProprietaireGUI {

    public static String proprietaireCreate() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("proprietaires/ajouter_proprietaires.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String proprietaireDelete(){
        return "";
    }

    public static String proprietaireModify(){
        return "";
    }
}
