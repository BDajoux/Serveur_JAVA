package com.uca.dao;

import com.uca.entity.AppartementEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class AppartementDAO {

    // Méthode pour récupérer tous les appartements de la base de données
    public ArrayList<AppartementEntity> getAllAppartements() throws SQLException,Exception {
        Connection connection = _Connector.getInstance();
        ArrayList<AppartementEntity> appartements = new ArrayList<>();
        String query = "SELECT * FROM appartement";
        try (PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                AppartementEntity appartement = new AppartementEntity();
                appartement.setIdAppartement(resultSet.getString("id_appartement"));
                appartement.setNumeroAppartement(resultSet.getString("numero_appartement"));
                appartement.setEtage(resultSet.getString("etage_appartement"));
                appartement.setSuperficie(resultSet.getInt("superficie_appartement"));
                appartement.setIdImmeuble(resultSet.getString("id_immeuble"));
                appartement.setIdProprietaire(resultSet.getString("id_proprietaire"));
                appartement.setIdLocataire(resultSet.getString("id_locataire"));
                appartements.add(appartement);
            }
        } catch (SQLException e) {
            // Gérer l'exception SQLException ici
            e.printStackTrace();
            throw e;
        }
        return appartements;
    }

    public ArrayList<AppartementEntity> getAppartByImm(String id) throws SQLException, Exception {
        Connection connection = _Connector.getInstance();
        ArrayList<AppartementEntity> appartements = new ArrayList<>();
        String query = "SELECT * FROM appartement where id_immeuble = '" + id + "';";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                AppartementEntity appartement = new AppartementEntity();
                appartement.setIdAppartement(resultSet.getString("id_appartement"));
                appartement.setNumeroAppartement(resultSet.getString("numero_appartement"));
                appartement.setEtage(resultSet.getString("etage_appartement"));
                appartement.setSuperficie(resultSet.getInt("superficie_appartement"));
                appartement.setIdImmeuble(resultSet.getString("id_immeuble"));
                appartement.setIdProprietaire(resultSet.getString("id_proprietaire"));
                appartement.setIdLocataire(resultSet.getString("id_locataire"));
                appartements.add(appartement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return appartements;
    }

    public AppartementEntity getAppartById(String id) throws SQLException,Exception {
        Connection connection = _Connector.getInstance();
        AppartementEntity appartement = new AppartementEntity();
        String query = "SELECT * FROM appartement WHERE id_appartement ='" + id + "';";
        try (PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                appartement.setIdAppartement(resultSet.getString("id_appartement"));
                appartement.setNumeroAppartement(resultSet.getString("numero_appartement"));
                appartement.setEtage(resultSet.getString("etage_appartement"));
                appartement.setSuperficie(resultSet.getInt("superficie_appartement"));
                appartement.setIdImmeuble(resultSet.getString("id_immeuble"));
                appartement.setIdProprietaire(resultSet.getString("id_proprietaire"));
                appartement.setIdLocataire(resultSet.getString("id_locataire"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return appartement;
    }

    public int getLastAppartementId() {
        int id = 0;
        Connection connection = _Connector.getInstance();
    
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_appartement FROM appartement ORDER BY id_appartement DESC LIMIT 1;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id_appartement"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return id;
    }
    
    // Méthode pour ajouter un appartement à la base de données
public void create(AppartementEntity appartement) throws SQLException {
    Connection connection = _Connector.getInstance();
    String query = "INSERT INTO appartement (id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, appartement.getIdAppartement());
        statement.setString(2, appartement.getNumeroAppartement());
        statement.setString(3, appartement.getEtage());
        statement.setInt(4, appartement.getSuperficie());
        statement.setString(5, appartement.getIdImmeuble());
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}

// Méthode pour supprimer un appartement de la base de données
public void delete(String idAppartement) throws SQLException {
    Connection connection = _Connector.getInstance();
    String query = "DELETE FROM appartement WHERE id_appartement = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, idAppartement);
        statement.executeUpdate();
    } catch (SQLException e) {
    e.printStackTrace();
    throw e;
    }
}

public void modifyProprietaire(String idAppartement, String idProprietaire) throws SQLException {
    Connection connection = _Connector.getInstance();
    String query = "UPDATE appartement SET id_proprietaire = ? WHERE id_appartement = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        System.out.println("id:" + idProprietaire);
        statement.setString(1, idProprietaire);
        statement.setString(2, idAppartement);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
}


// Méthode pour vérifier si l'appartement existe déjà
public static boolean isAppartementExists(String appartement) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean appartementExists = false;
    
    try {
        connection = _Connector.getInstance();
        
        String query = "SELECT * FROM appartement WHERE id_appartement = '?'";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, appartement);
        resultSet = preparedStatement.executeQuery();
        
        appartementExists = resultSet.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return appartementExists;
}

}
