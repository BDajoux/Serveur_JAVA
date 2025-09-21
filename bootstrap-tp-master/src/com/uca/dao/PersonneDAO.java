package com.uca.dao;

import com.uca.entity.PersonneEntity;
import java.sql.*;
import java.util.ArrayList;

public class PersonneDAO {

    // Méthode pour ajouter une personne à la base de données
    public void create(PersonneEntity personne) throws SQLException {
        Connection connection = _Connector.getInstance();
        String query = "INSERT INTO personne (id_personne, nom_personne, prenom_personne, telephone_personne, email_personne) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, personne.getIdPersonne());
            statement.setString(2, personne.getNom());
            statement.setString(3, personne.getPrenom());
            statement.setString(4, personne.getTelephone());
            statement.setString(5, personne.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode pour supprimer une personne de la base de données
    public void delete(String idPersonne) throws SQLException {
        Connection connection = _Connector.getInstance();
        String query = "DELETE FROM personne WHERE id_personne = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idPersonne);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode pour récupérer toutes les personnes de la base de données
    public ArrayList<PersonneEntity> getAllPersonnes() throws SQLException, Exception {
        Connection connection = _Connector.getInstance();
        ArrayList<PersonneEntity> personnes = new ArrayList<>();
        String query = "SELECT * FROM personne";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                PersonneEntity personne = new PersonneEntity();
                personne.setIdPersonne(resultSet.getString("id_personne"));
                personne.setNom(resultSet.getString("nom"));
                personne.setPrenom(resultSet.getString("prenom"));
                personne.setTelephone(resultSet.getString("telephone"));
                personnes.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return personnes;
    }

    public int getLastPersonneId() {
        int id = 0;
        Connection connection = _Connector.getInstance();
    
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_personne FROM personne ORDER BY id_personne DESC LIMIT 1;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id_personne"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return id;
    }
}
