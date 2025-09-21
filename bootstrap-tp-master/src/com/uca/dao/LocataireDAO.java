package com.uca.dao;

import com.uca.entity.LocataireEntity;
import java.sql.*;
import java.util.ArrayList;

public class LocataireDAO {

    // Méthode pour ajouter un locataire à la base de données
    public void createLocataire(LocataireEntity locataire) throws SQLException {
        Connection connection = _Connector.getInstance();
        
        // Insérer les informations communes aux personnes dans la table personnes
        String personneQuery = "INSERT INTO personne (id_personne, nom, prenom, telephone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement personneStatement = connection.prepareStatement(personneQuery)) {
            personneStatement.setString(1, locataire.getIdPersonne());
            personneStatement.setString(2, locataire.getNom());
            personneStatement.setString(3, locataire.getPrenom());
            personneStatement.setString(4, locataire.getTelephone());
            personneStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        // Insérer les informations spécifiques aux locataires dans la table locataires
        String locataireQuery = "INSERT INTO locataire (id_personne, autre_attribut) VALUES (?, ?)";
        try (PreparedStatement locataireStatement = connection.prepareStatement(locataireQuery)) {
            locataireStatement.setString(1, locataire.getIdPersonne());
            // Set other attributes specific to locataires
            // locataireStatement.setString(2, locataire.getAutreAttribut());
            locataireStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode pour supprimer un locataire de la base de données
    public void deleteLocataire(String idPersonne) throws SQLException {
        Connection connection = _Connector.getInstance();
        
        // Supprimer l'entrée correspondante dans la table locataires
        String locataireQuery = "DELETE FROM locataire WHERE id_personne = '?'";
        try (PreparedStatement locataireStatement = connection.prepareStatement(locataireQuery)) {
            locataireStatement.setString(1, idPersonne);
            locataireStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
        // Supprimer l'entrée correspondante dans la table personnes
        String personneQuery = "DELETE FROM personne WHERE id_personne = '?'";
        try (PreparedStatement personneStatement = connection.prepareStatement(personneQuery)) {
            personneStatement.setString(1, idPersonne);
            personneStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode pour récupérer tous les locataires de la base de données
    public ArrayList<LocataireEntity> getAllLocataires() throws SQLException {
        Connection connection = _Connector.getInstance();
        ArrayList<LocataireEntity> locataires = new ArrayList<>();

        // Sélectionner toutes les entrées de la table locataires
        String query = "SELECT * FROM locataire";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                LocataireEntity locataire = new LocataireEntity();
                locataire.setIdPersonne(resultSet.getString("id_personne"));
                // Set other attributes specific to locataires
                // locataire.setAutreAttribut(resultSet.getString("autre_attribut"));
                locataires.add(locataire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return locataires;
    }

    public LocataireEntity getLocByAppart(String id) throws Exception{
        Connection connection = _Connector.getInstance();
        LocataireEntity locataire = new LocataireEntity();

        // Sélectionner toutes les entrées de la table locataires
        String query = "SELECT locataire.id_personne, personne.nom_personne, personne.prenom_personne, personne.telephone_personne, personne.email_personne " +
        "FROM locataire " +
        "INNER JOIN personne ON locataire.id_personne = personne.id_personne " +
        "INNER JOIN appartement ON locataire.id_personne = appartement.id_locataire " +
        "WHERE appartement.id_appartement = '" + id + "';";
        try (PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                locataire.setIdPersonne(resultSet.getString("id_personne"));
                locataire.setNom(resultSet.getString("nom_personne"));
                locataire.setPrenom(resultSet.getString("prenom_personne"));
                locataire.setTelephone(resultSet.getString("telephone_personne"));
                locataire.setEmail(resultSet.getString("email_personne"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return locataire;
    }
}
