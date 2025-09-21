package com.uca.entity;

import static spark.Spark.*;

public class SyndicatEntity {
    private String nom;
    private String adresse;
    private String referent;
    private String telephone;
    private String email;
    private String idSyndicat;

    public String getNom(){
        return this.nom;
    }

    public String getAdresse(){
        return this.adresse;
    }

    public String getReferent(){
        return this.referent;
    }

    public String getTelephone(){
        return this.telephone;
    }

    public String getEmail(){
        return this.email;
    }

    public String getIdSyndicat(){
        return this.idSyndicat;
    }

    public void setNom(String nom) throws Exception {
        try {
            if (estVide(nom)){
                throw new Exception("Le nom ne doit pas être vide");
            }
            this.nom = nom;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void setAdresse(String adresse) throws Exception {
        try {
            if (estVide(adresse)){
                throw new Exception("L'adresse ne doit pas être vide");
            }
            this.adresse = adresse;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void setReferent(String referent) throws Exception {
        try {
            if (estVide(referent)){
                throw new Exception("Le referent ne doit pas être vide");
            }
            this.referent = referent;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void setTelephone(String telephone) throws Exception {
        try {
            if (estVide(telephone)){
                throw new Exception("Le téléphone ne doit pas être vide");
            } else if (telephone.length() != 10){
                throw new Exception("Le numéro n'est pas au bon format: 'XXXXXXXXXX', avec 0<X<9");
            } else {
                for(int i=0;i<telephone.length();i++){
                    char c = telephone.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("Le numéro n'est pas au bon format: 'XXXXXXXXXX', avec 0<X<9");
                    }
                }
            }
            this.telephone = telephone;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void setEmail(String email) throws Exception {
        try {
            if (estVide(email)){
                throw new Exception("L'email ne doit pas être vide");
            }
            this.email = email;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void setIdSyndicat(String id) throws Exception {
        try {
            if (estVide(id)){
                throw new Exception("L'id ne doit pas être vide");
            } else if (id.length() != 8){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
            } else {
                for(int i=0; i<id.length(); i++){
                    char c = id.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
                    }
                }
            }
            this.idSyndicat = id;
        } catch (Exception e) {
            throw e;
        }
    }
    
    

    private boolean estVide(String ch){
        return ch.length() == 0;
    }
}
