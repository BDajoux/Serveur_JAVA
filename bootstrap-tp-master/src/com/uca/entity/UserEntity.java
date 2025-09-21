package com.uca.entity;

import java.sql.Timestamp;

public class UserEntity {
    private String firstName;
    private String lastName;
    private String id;
    private String userName;
    private String email;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception{
        if (estVide(password)){
            throw new Exception("Le mot de passe ne dois pas être vide");
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if (estVide(email)){
            throw new Exception("L'email ne dois pas être vide");
        }
        this.email = email;
    }

    public UserEntity() {
        //Ignored !
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception{
        if (estVide(firstName)){
            throw new Exception("Le nom ne dois pas être vide");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) throws Exception{
        if (estVide(userName)){
            throw new Exception("Le nom ne dois pas être vide");
        }
        this.userName = userName;
    }

    private boolean estVide(String ch){
        return ch.length() == 0;
    }
}
