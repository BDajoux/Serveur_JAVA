package com.uca.dao;

import com.uca.entity.ImmeubleEntity;
import java.sql.*;
import java.util.ArrayList;

public class ImmeubleDAO {

    // Méthode pour ajouter un immeuble à la base de données
    public void create(ImmeubleEntity immeuble) throws SQLException {
        Connection connection = _Connector.getInstance();
        String query = "INSERT INTO immeuble (id_immeuble, nom_immeuble, adresse_immeuble, identifiant_syndicat, nb_appartements) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, immeuble.getIdImmeuble());
            statement.setString(2, immeuble.getNom());
            statement.setString(3, immeuble.getAdresse());
            statement.setString(4, immeuble.getSyndicat());
            statement.setString(5, String.valueOf(immeuble.getNbAppart()));
            statement.executeUpdate();
            System.out.println("test pitie");
        } catch (SQLException e) {
            System.out.println("er");
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode pour supprimer un immeuble de la base de données
    public void delete(String idImmeuble) throws SQLException {
        Connection connection = _Connector.getInstance();
        String query = "DELETE FROM immeuble WHERE id_immeuble = '?'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idImmeuble);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode pour récupérer tous les immeubles de la base de données
    public ArrayList<ImmeubleEntity> getAllImmeubles() throws SQLException, Exception {
        Connection connection = _Connector.getInstance();
        ArrayList<ImmeubleEntity> immeubles = new ArrayList<>();
        String query = "SELECT * FROM immeuble";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ImmeubleEntity immeuble = new ImmeubleEntity();
                immeuble.setIdImmeuble(resultSet.getString("id_immeuble"));
                immeuble.setNom(resultSet.getString("nom_immeuble"));
                immeuble.setAdresse(resultSet.getString("adresse_immeuble"));
                immeuble.setSyndicat(resultSet.getString("identifiant_syndicat"));
                immeubles.add(immeuble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return immeubles;
    }

    public ImmeubleEntity getImmById(String id) throws SQLException,Exception {
        Connection connection = _Connector.getInstance();
        ImmeubleEntity immeuble = new ImmeubleEntity();
        String query = "SELECT * FROM immeuble WHERE id_immeuble='" + id + "';";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                immeuble.setIdImmeuble(resultSet.getString("id_immeuble"));
                immeuble.setNom(resultSet.getString("nom_immeuble"));
                immeuble.setAdresse(resultSet.getString("adresse_immeuble"));
                immeuble.setSyndicat(resultSet.getString("identifiant_syndicat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return immeuble;
    }

    public ArrayList<ImmeubleEntity> getImmeublesBySyndic(String id) throws SQLException, Exception {
        Connection connection = _Connector.getInstance();
        ArrayList<ImmeubleEntity> immeubles = new ArrayList<>();
        String query = "SELECT * FROM immeuble where identifiant_syndicat = '" + id + "';";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ImmeubleEntity immeuble = new ImmeubleEntity();
                immeuble.setIdImmeuble(resultSet.getString("id_immeuble"));
                immeuble.setNom(resultSet.getString("nom_immeuble"));
                immeuble.setAdresse(resultSet.getString("adresse_immeuble"));
                immeuble.setSyndicat(resultSet.getString("identifiant_syndicat"));
                immeubles.add(immeuble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return immeubles;
    }

    public int getLastImmeubleId() {
        int id = 0;
        Connection connection = _Connector.getInstance();
    
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_immeuble FROM immeuble ORDER BY id_immeuble DESC LIMIT 1;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id_immeuble"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return id;
    }

    // Méthode pour vérifier si l'immeuble existe déjà
public static boolean isImmeubleExists(String immeuble) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean immeubleExists = false;
    
    try {
        connection = _Connector.getInstance();
        
        String query = "SELECT * FROM immeuble WHERE id_immeuble = '?'";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, immeuble);
        resultSet = preparedStatement.executeQuery();
        
        immeubleExists = resultSet.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return immeubleExists;
}
}
