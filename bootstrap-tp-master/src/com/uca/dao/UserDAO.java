package com.uca.dao;

import com.uca.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends _Generic<UserEntity> {

    public ArrayList<UserEntity> getAllUsers() throws Exception{
        ArrayList<UserEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserEntity entity = new UserEntity();
                entity.setId(resultSet.getString("id"));
                entity.setFirstName(resultSet.getString("firstname"));
                entity.setLastName(resultSet.getString("lastname"));
                entity.setUserName(resultSet.getString("username"));
                entity.setEmail(resultSet.getString("email"));
                entity.setPassword(resultSet.getString("password"));
                entity.setRole(resultSet.getString("role"));

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    public UserEntity createUsers(UserEntity obj) {
        
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("INSERT INTO users(id, firstname, lastname, username, email, password, role) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1,obj.getId());
            statement.setString(2,obj.getFirstName());
            statement.setString(3,obj.getLastName());
            statement.setString(4,obj.getUserName());
            statement.setString(5,obj.getEmail());
            statement.setString(6,obj.getPassword());
            statement.setString(7,obj.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public void delete(UserEntity obj) {
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("DELETE FROM users WHERE id = ?;");
            statement.setString(1, obj.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer l'ID du dernier utilisateur enregistré dans la base de données
    public int getLastUserId() {

        int id=0;
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM users ORDER BY id DESC LIMIT 1;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public String getPasswordByUsername(String username) {
        String password = null;
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?;");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println("DAO"+ password);
        return password;
    }

    // Méthode pour récupérer toutes les informations de l'utilisateur en fonction de son nom d'utilisateur
    public UserEntity getAllByUsername(String username) throws Exception{
        UserEntity user = null;
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?;");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Créer une instance de UserEntity avec les informations récupérées de la base de données
                user = new UserEntity();
                user.setId(resultSet.getString("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserEntity getUsersById(String id) throws Exception{
        UserEntity entity = new UserEntity();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM users WHERE id = ?;");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entity.setId(resultSet.getString("id"));
                entity.setFirstName(resultSet.getString("firstname"));
                entity.setLastName(resultSet.getString("lastname"));
                entity.setUserName(resultSet.getString("username"));
                entity.setEmail(resultSet.getString("email"));
                entity.setPassword(resultSet.getString("password"));
                entity.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }


            // Méthode pour vérifier si le nom d'utilisateur existe déjà
    public static boolean isUsernameExists(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean usernameExists = false;
        
        try {
            connection = _Connector.getInstance();
            
            String query = "SELECT * FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            
            usernameExists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usernameExists;
    }
}
