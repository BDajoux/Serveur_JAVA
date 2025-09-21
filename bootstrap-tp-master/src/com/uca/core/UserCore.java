package com.uca.core;

import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class UserCore {

    public static ArrayList<UserEntity> getAllUsers() throws Exception{
        try{
            return new UserDAO().getAllUsers();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public String getPasswordByUsername(String username) throws Exception{
        try{
            return new UserDAO().getPasswordByUsername(username);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public UserEntity getAllByUsername(String username) throws Exception{
        try{
            return new UserDAO().getAllByUsername(username);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static UserEntity getUsersById(String id) throws Exception{
        try{
            return new UserDAO().getUsersById(id);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public UserEntity createUsers(String username, String firstName, String lastName, String email, String password, String role) throws Exception{
        try{
            UserEntity newUser = new UserEntity();
            newUser.setUserName(username);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setRole(role);
            newUser.setId(generateNewUserId());
            return new UserDAO().createUsers(newUser);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static String generateNewUserId() {
        // Récupérer l'ID du dernier utilisateur enregistré
        int lastUserId = new UserDAO().getLastUserId();

        // Générer le nouvel ID utilisateur en incrémentant l'ID du dernier utilisateur
        int newUserId = lastUserId + 1;

        // Formater le nouvel ID utilisateur en une chaîne de 8 chiffres (avec des zéros à gauche si nécessaire)
        String formattedUserId = String.format("%08d", newUserId);

        return formattedUserId;
    }

    public boolean isUsernameExists(String username) throws Exception{
        try{
            return new UserDAO().isUsernameExists(username);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
