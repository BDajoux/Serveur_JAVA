package com.uca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import spark.Request;
import spark.Response;
import spark.Spark;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;

import static spark.Spark.halt;

import com.uca.dao._Initializer;
import com.uca.gui.*;
import com.uca.core.*;
import com.uca.security.*;
import com.uca.entity.*;
import org.mindrot.jbcrypt.BCrypt;
import com.google.gson.Gson;

import static spark.Spark.*;

public class StartServer {

    public static void main(String[] args) {

        // Configure Spark
        staticFiles.location("/static/");
        port(8081);

        _Initializer.Init();

        // Clé d'identifiant admin
        String adminKey = "F0KxB8gduAwCKggOrOFuJEo16wCWHJHBAeVAEZEWzom4DS1QLre5m8pAzTPPI86NRUGX90LdovUhuWAA";

        // Vérifie si l'utilisateur est admin avant de lui accorder le droit de créer
        // une ressource (sécurité)
        Spark.before("*/create", (req, res) -> {
            String token = req.cookie("token");
            if (!isAdmin(token)) {
                res.redirect("/missauthorization");
                halt();
            }
        });

        // Vérifie si l'utilisateur est admin avant de lui accorder le droit de
        // supprimer une ressource (sécurité)
        Spark.before("*/delete", (req, res) -> {
            String token = req.cookie("token");
            if (!isAdmin(token)) {
                res.redirect("/missauthorization");
                halt();
            }
        });

        // Informe l'utilisateur qu'il n'a pas les droits d'accès à la page
        get("/missauthorization", (req, res) -> {
            String token = req.cookie("token");
            int role;
            if (isTokenValid(token)) {
                role = 1;
            } else {
                role = 0;
            }
            return UserGUI.nonAutorise(role);
        });

        // Accès à la liste des appartements
        get("ressources/appartements", (req, res) -> {
            String token = req.cookie("token");
            if (isAdmin(token)) {
                return AppartementGUI.getAllAppartements(2);
            } else if (isTokenValid(token)) {
                return AppartementGUI.getAllAppartements(1);
            } else {
                return AppartementGUI.getAllAppartements(0);
            }
        });

        // Affichage d'un appartement spécifique
        get("ressources/appartements/:id", (req, res) -> {
            String token = req.cookie("token");
            int role;
            if (isAdmin(token)) {
                role = 2;
            } else if (isTokenValid(token)) {
                role = 1;
            } else {
                role = 0;
            }
            String id = req.params(":id");
            if (id.equals("create")) {
                return AppartementGUI.appartementCreation();
            } else if (id.equals("delete")) {
                return AppartementGUI.appartementSuppression();
            } else {
                return AppartementGUI.getAppartById(id, role);
            }
        });

        // Affichage page d'accueil
        get("/accueil", (req, res) -> {
            String token = req.cookie("token");
            int role;
            if (isAdmin(token)) {
                role = 2;
            } else if (isTokenValid(token)) {
                role = 1;
            } else {
                role = 0;
            }
            return UserGUI.Index(role);
        });

        // Redirige le lien de base vers la page d'accueil
        get("/", (req, res) -> {
            res.redirect("/accueil");
            halt();
            return "";
        });

        // Affiche la page login
        get("/login", (req, res) -> {
            String token = req.cookie("token");
            if (!isTokenValid(token)) {
                return UserGUI.login();
            } else {
                res.redirect("/account");
                halt();
                return null;
            }
        });

        // Affiche le formulaire d'enregistrement
        get("/register", (req, res) -> {
            String token = req.cookie("token");
            if (isAdmin(token)) {
                return UserGUI.register();
            } else {
                res.redirect("/missauthorization");
                halt();
                return null;
            }
        });

        // Affiche la liste des syndicats
        get("ressources/syndicat", (req, res) -> {
            String token = req.cookie("token");
            if (isAdmin(token)) {
                return SyndicatGUI.getAllSyndicats(2);
            } else if (isTokenValid(token)) {
                return SyndicatGUI.getAllSyndicats(1);
            } else {
                return SyndicatGUI.getAllSyndicats(0);
            }
        });

        // Affiche les informations d'un syndicat particulier
        get("ressources/syndicat/:id", (req, res) -> {
            String token = req.cookie("token");
            int role;
            if (isAdmin(token)) {
                role = 2;
            } else if (isTokenValid(token)) {
                role = 1;
            } else {
                role = 0;
            }
            String id = req.params(":id");
            if (id.equals("create")) {
                return SyndicatGUI.syndicatCreation();
            } else if (id.equals("delete")) {
                return SyndicatGUI.syndicatSuppression();
            } else {
                return SyndicatGUI.getSyndicatById(id, role);
            }
        });

        // Affiche la liste des immeubles
        get("ressources/immeubles", (req, res) -> {
            String token = req.cookie("token");
            if (isAdmin(token)) {
                return ImmeubleGUI.getAllImmeubles(2);
            } else if (isTokenValid(token)) {
                return ImmeubleGUI.getAllImmeubles(1);
            } else {
                return ImmeubleGUI.getAllImmeubles(0);
            }
        });

        // Affiche les informations d'un immeuble particulier
        get("ressources/immeubles/:id", (req, res) -> {
            String token = req.cookie("token");
            int role;
            if (isAdmin(token)) {
                role = 2;
            } else if (isTokenValid(token)) {
                role = 1;
            } else {
                role = 0;
            }
            String id = req.params(":id");
            if (id.equals("create")) {
                return ImmeubleGUI.immeubleCreation();
            } else if (id.equals("delete")) {
                return ImmeubleGUI.immeubleSuppression();
            } else {
                return ImmeubleGUI.getImmeubleById(id, role);
            }

        });

        // Vérifie si le username existe deja
        post("/check-username", (req, res) -> {
            String username = req.queryParams("username");
            if (!username.equals(adminKey)) {
                boolean usernameExists = new UserCore().isUsernameExists(username);
                Map<String, Object> jsonResponse = new HashMap<>();
                jsonResponse.put("usernameExists", usernameExists);
                return new Gson().toJson(jsonResponse);
            } else {
                Map<String, Object> jsonResponse = new HashMap<>();
                jsonResponse.put("usernameExists", false);
                return new Gson().toJson(jsonResponse);
            }
        });

        // Traitement de l'inscription
        post("/register", (req, res) -> {
            String username = req.queryParams("userName");
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            String password = req.queryParams("password");
            String email = req.queryParams("email");
            String role = "1";

            // Si l'utilisateur a entré la clé admin en nom d'utilisateur
            if (username.substring(6).equals("admin_")) {
                role = "0";
            }

            // Hasher et saler le mot de passe
            String salt = "$2a$10$KRAprlMNgO9LYmog8VV3gu";
            String hashedPassword = BCrypt.hashpw(password, salt);
            new UserCore().createUsers(username, firstName, lastName, email, hashedPassword, role);
            res.redirect("/successregister");
            halt();
            return null;
        });

        // Affiche que le compte de l'utilisateur est créé
        get("/successregister", (req, res) -> {
            return UserGUI.succesRegister(true);
        });

        get("/logout", (req,res) -> {
            res.removeCookie("token");
            res.redirect("/accueil");
            halt();
            return null;
        });

        // Vérifie si le username existe deja
        post("/check-login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            boolean isAuthenticate = new StartServer().authenticateUser(username, password);
            System.out.println(username + " " + password + " " + isAuthenticate);
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("isAuthenticate", isAuthenticate);
            return new Gson().toJson(jsonResponse);
        });

        // Gère l'authentification
        post("/login", (req, res) -> {
            String username = req.queryParams("userName");
            String password = req.queryParams("password");

            UserEntity currUser = new UserCore().getAllByUsername(username);
            String token = doLogin.createToken(currUser.getUserName(), currUser.getFirstName(),
                    currUser.getLastName(), currUser.getId(), currUser.getRole(),
                    "b04bb7c1-43c1-4b95-9a20-8fe61b582037");
            res.cookie("token", token, 3600);
            res.redirect("/successlogin");
            halt();
            return null;
        });

        get("/successlogin", (req, res) -> {
            return UserGUI.successlogin();
        });

        get("/failedlogin", (req, res) -> {
            return UserGUI.failedlogin();
        });

        // Ajouter un nouvel immeuble
        post("ressources/immeubles/create", (req, res) -> {
            String nomImmeuble = req.queryParams("nomImmeuble");
            String adresseImmeuble = req.queryParams("adresseImmeuble");
            String idSyndicat = req.queryParams("idSyndicat");
            String nbAppart = req.queryParams("nbAppart");
            try {
                new ImmeubleCore().createImmeuble(nomImmeuble, adresseImmeuble, idSyndicat, Integer.parseInt(nbAppart));
                res.redirect("/successimmeublecreated");
            } catch (SQLException e) {
                e.printStackTrace();
                res.redirect("/error");
                return "Erreur lors de la création de l'immeuble";
            }
            return null;
        });

        // Supprimer un nouvel immeuble
        post("ressources/immeubles/delete", (req, res) -> {
            String idImmeuble = req.queryParams("idImmeuble");
            try {
                new ImmeubleCore().deleteImmeuble(idImmeuble);
                res.redirect("/successimmeubledeleted");
            } catch (SQLException e) {
                e.printStackTrace();
                res.redirect("/error");
                return "Erreur lors de la suppression de l'immeuble";
            }
            return null;
        });

        // Ajouter un syndicat
        post("ressources/syndicat/create", (req, res) -> {
            String nomSyndicat = req.queryParams("nomSyndicat");
            String adresseSyndicat = req.queryParams("adresseSyndicat");
            String referentSyndicat = req.queryParams("referentSyndicat");
            String telephoneSyndicat = req.queryParams("telephoneSyndicat");
            String emailSyndicat = req.queryParams("emailSyndicat");

            try {
                new SyndicatCore().createSyndicat(nomSyndicat, adresseSyndicat, referentSyndicat, telephoneSyndicat,
                        emailSyndicat);
                res.redirect("/successsyndicatcreated");
            } catch (SQLException e) {
                e.printStackTrace();
                res.redirect("/error");
                return "Erreur lors de la création du syndicat";
            }
            return "Succès";
        });

        // Supprimer un syndicat
        post("ressources/syndicat/delete", (req, res) -> {
            String idSyndicat = req.queryParams("idSyndicat");
            try {
                new SyndicatCore().deleteSyndicat(idSyndicat);
                res.redirect("/successsyndicatdeleted");
            } catch (SQLException e) {
                e.printStackTrace();
                res.redirect("/error");
                return "Erreur lors de la suppression du syndicat";
            }
            return null;
        });

        // Ajouter un nouvel appartement
        post("ressources/appartements/create", (req, res) -> {
            String numeroAppartement = req.queryParams("numeroAppartement");
            String etageAppartement = req.queryParams("etageAppartement");
            int superficieAppartement = Integer.parseInt(req.queryParams("superficieAppartement"));
            String idImmeuble = req.queryParams("idImmeuble");

            try {
                new AppartementCore().createAppartement(numeroAppartement, etageAppartement, superficieAppartement,
                        idImmeuble);
                res.redirect("/successappartementcreated");
            } catch (SQLException e) {
                e.printStackTrace();
                res.redirect("/error");
                return "Erreur lors de la création de l'appartement";
            }
            return null;
        });

        // Supprimer un appartement
        post("ressources/appartements/delete", (req, res) -> {
            String idAppartement = req.queryParams("idAppartement");
            try {
                new AppartementCore().deleteAppartement(idAppartement);
                res.redirect("/successappartementdeleted");
            } catch (SQLException e) {
                e.printStackTrace();
                res.redirect("/error");
                return "Erreur lors de la suppression de l'appartement";
            }
            return null;
        });

        // Vérifie si le proprietaire existe deja
        post("/check-proprietaire", (req, res) -> {
            String proprietaire = req.queryParams("proprietaire");

            boolean proprietaireExists = new ProprietaireCore().isProprietaireExists(proprietaire);
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("proprietaireExists", proprietaireExists);
            return new Gson().toJson(jsonResponse);
        });

        // Vérifie si le proprietaire existe deja
        post("/check-proprietaire2", (req, res) -> {
            String proprietaireName = req.queryParams("proprietaireName");
            String proprietaireSurname = req.queryParams("proprietaireSurname");
            boolean proprietaireExists2 = new ProprietaireCore().isProprietaireExists2(proprietaireName,
                    proprietaireSurname);
            Map<String, Object> jsonResponse = new HashMap<>();
            jsonResponse.put("proprietaireExists2", proprietaireExists2);
            return new Gson().toJson(jsonResponse);
        });

        // Page de modification de propriétaire
        post("ressources/appartements/modify/proprietaire", (req, res) -> {
            return null;
        });

        // Page de modification d'un locataire
        post("ressources/appartements/modify/locataire", (req, res) -> {
            return null;
        });

        // Page de creation de propriétaire
        post("ressources/appartements/create/proprietaire", (req, res) -> {
            String selectProprioById = req.queryParams("selectProprioById");

            if (selectProprioById == "true") {
                String idAppartement = req.queryParams("idAppartement");
                String idProprietaire = req.queryParams("idProprietaire");
                try {
                    new ProprietaireCore().assignProprietaireForAppartement(idAppartement, idProprietaire);
                    res.redirect("/successproprietairecreated");
                } catch (SQLException e) {
                    e.printStackTrace();
                    res.redirect("/error");
                    return "Erreur lors de la création de l'appartement";
                }

            } else {
                String idAppartement = req.queryParams("idAppartement");
                String nomProprietaire = req.queryParams("nomProprietaire");
                String prenomProprietaire = req.queryParams("prenomProprietaire");
                String emailProprietaire = req.queryParams("emailProprietaire");
                String numeroTelephoneProprietaire = req.queryParams("numeroProprietaire");

                try {
                    new ProprietaireCore().createProprietaireForAppartement(idAppartement, nomProprietaire,
                            prenomProprietaire, numeroTelephoneProprietaire, emailProprietaire);
                    res.redirect("/successappartementcreated");
                } catch (SQLException e) {
                    e.printStackTrace();
                    res.redirect("/error");
                    return "Erreur lors de la création de l'appartement";
                }
            }
            return null;
        });

        // Page de creation d'un locataire
        post("ressources/appartements/create/locataire", (req, res) -> {
            return null;
        });

        // Page de suppression de propriétaire
        post("ressources/appartements/delete/proprietaire", (req, res) -> {

            String idAppartement = req.queryParams("idAppartement");
            String idProprietaire = req.queryParams("idProprietaire");
            System.out.println(idAppartement);
            System.out.println(idProprietaire);
            try {
                new ProprietaireCore().deleteProprietaireForAppartement(idAppartement, idProprietaire);
                res.redirect("/successproprietairedeleted");

            } catch (SQLException e) {
                e.printStackTrace();
                res.redirect("/error");
                return "Erreur lors de la création de l'appartement";
            }
            return null;
        });

        // Page de suppression d'un locataire
        post("ressources/appartements/delete/locataire", (req, res) -> {
            return null;
        });

        get("/successproprietairedeleted", (req, res) -> {
            return ImmeubleGUI.succesCreation();
        });

        // Page de modification de propriétaire
        get("ressources/appartements/modify/proprietaire", (req, res) -> {
            return ProprietaireGUI.proprietaireModify();
        });

        // Page de modification d'un locataire
        get("ressources/appartements/modify/locataire", (req, res) -> {
            return LocataireGUI.locataireModify();
        });

        // Page de creation de propriétaire
        get("ressources/appartements/create/proprietaire", (req, res) -> {
            return ProprietaireGUI.proprietaireCreate();
        });

        // Page de creation d'un locataire
        get("ressources/appartements/create/locataire", (req, res) -> {
            return LocataireGUI.locataireCreate();
        });

        // Page de suppression de propriétaire
        get("ressources/appartements/delete/proprietaire", (req, res) -> {
            return ProprietaireGUI.proprietaireDelete();
        });

        // Page de suppression d'un locataire
        get("ressources/appartements/delete/locataire", (req, res) -> {
            return LocataireGUI.locataireDelete();
        });

        // Confirme que l'immeuble a bien été créé
        get("/successimmeublecreated", (req, res) -> {
            return ImmeubleGUI.succesCreation();
        });

        // Confirme que l'immeuble a bien été supprimé
        get("/successimmeubledeleted", (req, res) -> {
            return ImmeubleGUI.succesSuppression();
        });

        // Confirme que le syndicat a bien été créé
        get("/successsyndicatcreated", (req, res) -> {
            return SyndicatGUI.succesCreation();
        });

        // Confirme que le syndicat a bien été supprimé
        get("/successsyndicatdeleted", (req, res) -> {
            return SyndicatGUI.succesSuppression();
        });

        // Confirme que l'appartement a bien été créé
        get("/successappartementcreated", (req, res) -> {
            return AppartementGUI.succesCreation();
        });

        // Confirme que l'appartement a bien été supprimé
        get("/successappartementdeleted", (req, res) -> {
            return AppartementGUI.succesSuppression();
        });

        // Page affiché en cas d'erreur serveur de suppression/création
        get("/error", (req, res) -> {
            return ImmeubleGUI.error();
        });

        get("/account", (req, res) -> {
            String token = req.cookie("token");
            Map<String, String> maptoken = doLogin.introspect(token);
            String id = maptoken.get("id");
            return UserGUI.account(id);
        });
    }

    // Vérifie si le couple nom d'utilisateur mdp est bon
    public boolean authenticateUser(String username, String password) throws Exception {
        String storedHashedPassword = new UserCore().getPasswordByUsername(username);
        return BCrypt.checkpw(password, storedHashedPassword);
    }

    // Vérifier la validité du token
    public static boolean isTokenValid(String token) {
        if (token == null) {
            return false;
        }
        return true;
    }

    public static boolean isAdmin(String token) {
        if (isTokenValid(token)) {

            String jwtToken = token;
            try {
                Map<String, String> tokenValues = doLogin.introspect(jwtToken);
                if (tokenValues.get("role").equals("0")) {
                    return true;
                } else if (tokenValues.get("role").equals("1")) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
