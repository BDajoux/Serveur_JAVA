package com.uca.gui;

import com.uca.core.ImmeubleCore;
import com.uca.core.SyndicatCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class SyndicatGUI {

    public static String getAllSyndicats(int role) throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();


        input.put("syndicat", SyndicatCore.getAllSyndicats());
        input.put("role", role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("syndicat/syndicat.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String getSyndicatById(String id, int role) throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();


        input.put("syndicat", SyndicatCore.getSyndicatById(id));
        input.put("immeubles", ImmeubleCore.getImmeublesBySyndic(id));
        input.put("role", role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("syndicat/syndicat_spec.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String syndicatCreation() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("syndicat/Ajouter_syndicat.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String syndicatSuppression() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("syndicat/Supprimer_syndicat.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String succesCreation() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("syndicat/Succes_ajout.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String succesSuppression() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("syndicat/Succes_suppr.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
