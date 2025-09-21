package com.uca.entity;

import static spark.Spark.*;

public class AppartementEntity {
    private String idAppartement;
    private String numeroAppartement;
    private String etage;
    private int superficie;
    private String idImmeuble;
    private String idProprietaire;
    private String idLocataire;

    public String getIdProprietaire() {
        return idProprietaire;
    }
    public void setIdProprietaire(String idProprietaire) {
        try {
            if (estVide(idProprietaire)){
                throw new Exception("L'id ne doit pas être vide");
            }
            else if (idProprietaire.length() != 8){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<idProprietaire.length();i++){
                    char c = idProprietaire.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
                    }
                }
            }
            this.idProprietaire = idProprietaire;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getIdLocataire() {
        return idLocataire;
    }
    public void setIdLocataire(String idLocataire) {
        try {
            if (estVide(idLocataire)){
                throw new Exception("L'id ne doit pas être vide");
            }
            else if (idLocataire.length() != 8){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<idLocataire.length();i++){
                    char c = idLocataire.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
                    }
                }
            }
            this.idLocataire = idLocataire;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getIdAppartement() {
        return idAppartement;
    }
    public String getNumeroAppartement() {
        return numeroAppartement;
    }

    public String getEtage() {
        return etage;
    }

    public int getSuperficie() {
        return superficie;
    }

    public String getIdImmeuble() {
        return idImmeuble;
    }

    public void setIdAppartement(String idAppartement) {
        try {
            if (estVide(idAppartement)){
                throw new Exception("L'id ne doit pas être vide");
            }
            else if (idAppartement.length() != 8){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<idAppartement.length();i++){
                    char c = idAppartement.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
                    }
                }
            }
            this.idAppartement = idAppartement;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setNumeroAppartement(String numeroAppartement) {
        try {
            if (estVide(numeroAppartement)){
                throw new Exception("Le numero d'appartement ne doit pas être vide");
            }
            this.numeroAppartement = numeroAppartement;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setEtage(String etage) {
        try {
            if (estVide(etage)){
                throw new Exception("L'etage ne doit pas être vide");
            }
            this.etage = etage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }
    
    public void setIdImmeuble(String idImmeuble) {
        try {
            if (estVide(idImmeuble)){
                throw new Exception("L'id ne doit pas être vide");
            }
            else if (idImmeuble.length() != 8){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<idImmeuble.length();i++){
                    char c = idImmeuble.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
                    }
                }
            }
            this.idImmeuble = idImmeuble;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private boolean estVide(String ch){
        return ch.length() == 0;
    }
}
