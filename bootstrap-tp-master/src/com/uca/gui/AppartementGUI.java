package com.uca.gui;

import com.uca.StartServer;
import com.uca.core.AppartementCore;
import com.uca.core.ProprietaireCore;
import com.uca.core.LocataireCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class AppartementGUI {

    public static String getAllAppartements(int role) throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("appartement", AppartementCore.getAllAppartements());
        input.put("role",role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Appartements/Appartements.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String getAppartById(String id, int role) throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("appart", AppartementCore.getAppartById(id));
        input.put("loc", LocataireCore.getLocByAppart(id));
        input.put("prop", ProprietaireCore.getPropByAppart(id));
        input.put("role", role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Appartements/Appartements_spec.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String succesCreation() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Appartements/Succes_creation.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String succesSuppression() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Appartements/Succes_suppr.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String appartementCreation() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();


        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Appartements/Ajouter_appart.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String appartementSuppression() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();


        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Appartements/Supprimer_appart.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}

