package com.uca.dao;

import com.uca.entity.SyndicatEntity;
import java.sql.*;
import java.util.ArrayList;

public class SyndicatDAO {

    // Méthode pour ajouter un syndicat à la base de données
    public void create(SyndicatEntity syndicat) throws SQLException {
        Connection connection = _Connector.getInstance();
        String query = "INSERT INTO syndicat (id_syndicat, nom_syndicat, adresse_syndicat, referent_syndicat, numero_telephone_syndicat, email_syndicat ) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, syndicat.getIdSyndicat());
            statement.setString(2, syndicat.getNom());
            statement.setString(3, syndicat.getAdresse());
            statement.setString(4, syndicat.getReferent());
            statement.setString(5, syndicat.getTelephone());
            statement.setString(6, syndicat.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int getLastSyndicatId() {
        int id = 0;
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_syndicat FROM syndicat ORDER BY id_syndicat DESC LIMIT 1;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id_syndicat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération du dernier ID de syndicat", e);
        }

        return id;
    }

    // Méthode pour supprimer un syndicat de la base de données
    public void delete(String idSyndicat) throws SQLException {
        Connection connection = _Connector.getInstance();
        String query = "DELETE FROM syndicat WHERE id_syndicat = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idSyndicat);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode pour récupérer tous les syndicats de la base de données
    public ArrayList<SyndicatEntity> getAllSyndicats() throws SQLException,Exception {
        Connection connection = _Connector.getInstance();
        ArrayList<SyndicatEntity> syndicats = new ArrayList<>();
        String query = "SELECT * FROM syndicat";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                SyndicatEntity syndicat = new SyndicatEntity();
                syndicat.setIdSyndicat(resultSet.getString("id_syndicat"));
                syndicat.setNom(resultSet.getString("nom_syndicat"));
                syndicat.setAdresse(resultSet.getString("adresse_syndicat"));
                syndicat.setReferent(resultSet.getString("referent_syndicat"));
                syndicat.setTelephone(resultSet.getString("numero_telephone_syndicat"));
                syndicat.setEmail(resultSet.getString("email_syndicat"));
                syndicats.add(syndicat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return syndicats;
    }

    public SyndicatEntity getSyndicatById(String id) throws SQLException,Exception {
        Connection connection = _Connector.getInstance();
        SyndicatEntity syndicat = new SyndicatEntity();
        String query = "SELECT * FROM syndicat  WHERE id_syndicat ='" + id + "';";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                syndicat.setIdSyndicat(resultSet.getString("id_syndicat"));
                syndicat.setNom(resultSet.getString("nom_syndicat"));
                syndicat.setAdresse(resultSet.getString("adresse_syndicat"));
                syndicat.setReferent(resultSet.getString("referent_syndicat"));
                syndicat.setTelephone(resultSet.getString("numero_telephone_syndicat"));
                syndicat.setEmail(resultSet.getString("email_syndicat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return syndicat;
    }

    // Méthode pour vérifier si le syndicat existe déjà
public static boolean isSyndicatExists(String syndicat) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean syndicatExists = false;
    
    try {
        connection = _Connector.getInstance();
        
        String query = "SELECT * FROM syndicat WHERE id_syndicat = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, syndicat);
        resultSet = preparedStatement.executeQuery();
        
        syndicatExists = resultSet.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return syndicatExists;
}
}
