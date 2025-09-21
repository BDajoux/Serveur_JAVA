package com.uca.entity;

import static spark.Spark.*;
public class PersonneEntity {
    private String idPersonne;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonneEntity(){

    }

    public String getIdPersonne() {
        return this.idPersonne;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setIdPersonne(String idPersonne) {
        try {
            /*if (estVide(idPersonne)){
                throw new Exception("L'id ne doit pas être vide");
            }
            else if (idPersonne.length() != 8){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<idPersonne.length();i++){
                    char c = idPersonne.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
                    }
                }
            }*/
            this.idPersonne = idPersonne;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setNom(String nom) {
        try {
            if (estVide(nom)){
                throw new Exception("Le nom ne doit pas être vide");
            }
            this.nom = nom;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setPrenom(String prenom) {
        try {
            if (estVide(prenom)){
                throw new Exception("Le prénom ne doit pas être vide");
            }
            this.prenom = prenom;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setTelephone(String telephone) {
        try {
            if (estVide(telephone)){
                throw new Exception("Le téléphone ne doit pas être vide");
            }
            else if (telephone.length() != 10){
                throw new Exception("Le numéro n'est pas au bon format: 'XXXXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<telephone.length();i++){
                    char c = telephone.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("Le numéro n'est pas au bon format: 'XXXXXXXXXX', avec 0<X<9");
                    }
                }
            }
    
            this.telephone = telephone;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private boolean estVide(String ch){
        return ch.length() == 0;
    }
}
