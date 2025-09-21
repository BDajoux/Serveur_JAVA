package com.uca.gui;

import com.uca.core.ImmeubleCore;
import com.uca.core.AppartementCore;
import com.uca.core.SyndicatCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ImmeubleGUI {

    public static String getAllImmeubles(int role) throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("immeuble", ImmeubleCore.getAllImmeubles());
        input.put("role", role);

        System.out.println(role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Immeubles/Immeubles.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String getImmeubleById(String id, int role) throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();


        input.put("immeuble", ImmeubleCore.getImmById(id));
        input.put("appartements", AppartementCore.getAppartByImm(id));
        input.put("role", role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Immeubles/Immeubles_spec.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String succesCreation() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Immeubles/Succes_immeuble.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String succesSuppression() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Immeubles/Failed_immeuble.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String immeubleCreation() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Immeubles/Ajouter_immeuble.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String immeubleSuppression() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Immeubles/Supprimer_immeuble.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String error() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Error/action_error.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
