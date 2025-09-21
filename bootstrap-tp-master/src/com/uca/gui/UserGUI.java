package com.uca.gui;

import com.uca.core.UserCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class UserGUI {

    public static String getAllUsers(boolean connected) throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("users", UserCore.getAllUsers());
        input.put("connected", connected);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/users.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String login() throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("users", UserCore.getAllUsers());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("login/login.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String Index(int role) throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("role", role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Accueil/Index.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String account(String id) throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("user", UserCore.getUsersById(id));

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("users/account.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String successlogin() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("login/success_login.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String failedlogin() throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("login/failed_login.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }


    public static String register() throws IOException, TemplateException, Exception {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("users", UserCore.getAllUsers());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("register/register.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String succesRegister(boolean connected) throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("users", UserCore.getAllUsers());
        input.put("connected", connected);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("register/success_register.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String nonAutorise(int role) throws IOException, TemplateException, Exception{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("role",role);

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("Error/missed_authorization.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
