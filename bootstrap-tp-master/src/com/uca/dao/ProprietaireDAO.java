package com.uca.dao;

import com.uca.entity.ProprietaireEntity;

import java.sql.*;
import java.util.ArrayList;

public class ProprietaireDAO extends _Generic<ProprietaireEntity> {

    public ArrayList<ProprietaireEntity> getAllProprietaires() {
        ArrayList<ProprietaireEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM proprietaire ORDER BY id_personne ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProprietaireEntity entity = new ProprietaireEntity();
                entity.setIdPersonne(resultSet.getString("id_personne"));                
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    public ProprietaireEntity create(ProprietaireEntity obj) {
        
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("INSERT INTO personne(id_personne, nom_personne, prenom_personne, telephone_personne, email_personne) VALUES(?,?,?,?,?);");
            statement.setString(1, obj.getIdPersonne());
            statement.setString(2, obj.getNom());
            statement.setString(3, obj.getPrenom());
            statement.setString(4, obj.getTelephone());
            statement.setString(5, obj.getEmail());
            statement.executeUpdate();
            statement = connection.prepareStatement("INSERT INTO proprietaire(id_personne) VALUES(?);");
            statement.setString(1, obj.getIdPersonne());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public void delete(ProprietaireEntity obj) {
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("DELETE FROM proprietaire WHERE id_personne = '?';");
            statement.setString(1, obj.getIdPersonne());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLastProprietaireId() {
        int id = 0;
        Connection connection = _Connector.getInstance();
    
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_personne FROM proprietaire ORDER BY id_personne DESC LIMIT 1;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id_personne"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return id;
    }

    // Méthode pour vérifier si le proprietaire existe déjà
    public static boolean isProprietaireExists(String proprietaire) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean proprietaireExists = false;
        
        try {
            connection = _Connector.getInstance();
            
            String query = "SELECT * FROM proprietaire WHERE id_personne = '?'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, proprietaire);
            resultSet = preparedStatement.executeQuery();
            
            proprietaireExists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return proprietaireExists;
    }

    // Méthode pour vérifier si le nom d'utilisateur existe déjà
    public static boolean isProprietaireExists2(String proprietaireName, String proprietaireSurname) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean proprietaireExists = false;
        
        try {
            connection = _Connector.getInstance();
            
        String query = "SELECT * FROM personne WHERE (nom_personne = ? AND prenom_personne = ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, proprietaireSurname);
            preparedStatement.setString(2, proprietaireName);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {                
                if(isProprietaireExists(resultSet.getString("id_personne"))){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public static boolean isProprietaireUsefull(String proprietaire) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean proprietaireExists = false;
        
        try {
            connection = _Connector.getInstance();
            
            String query = "SELECT * FROM appartement WHERE id_proprietaire = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, proprietaire);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            proprietaireExists = resultSet.next();
            System.out.println(proprietaireExists);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return proprietaireExists;
    }

    public ProprietaireEntity getPropByAppart(String id) throws Exception{
        Connection connection = _Connector.getInstance();
        ProprietaireEntity proprietaire = new ProprietaireEntity();

        // Sélectionner toutes les entrées de la table proprietaires
        String query = "SELECT proprietaire.id_personne, personne.nom_personne, personne.prenom_personne, personne.telephone_personne, personne.email_personne " +
        "FROM proprietaire " +
        "INNER JOIN personne ON proprietaire.id_personne = personne.id_personne " +
        "INNER JOIN appartement ON proprietaire.id_personne = appartement.id_proprietaire " +
        "WHERE appartement.id_appartement = '" + id + "';";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                proprietaire.setIdPersonne(resultSet.getString("id_personne"));
                proprietaire.setNom(resultSet.getString("nom_personne"));
                proprietaire.setPrenom(resultSet.getString("prenom_personne"));
                proprietaire.setTelephone(resultSet.getString("telephone_personne"));
                proprietaire.setEmail(resultSet.getString("email_personne"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return proprietaire;
    }
            
}

