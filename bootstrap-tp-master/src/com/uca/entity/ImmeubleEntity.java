package com.uca.entity;

import static spark.Spark.*;

public class ImmeubleEntity{
    private String nom;
    private String adresse;
    private String syndicat;
    private String idImmeuble;
    private int nbAppart;

    public int getNbAppart() {
        return nbAppart;
    }

    public void setNbAppart(int nbAppart) {
        this.nbAppart = nbAppart;
    }

    public ImmeubleEntity(){

    }

    public String getNom(){
        return this.nom;
    }

    public String getAdresse(){
        return this.adresse;
    }

    public String getSyndicat(){
        return this.syndicat;
    }

    public String getIdImmeuble(){
        return this.idImmeuble;
    }

    public void setNom(String nom){
        try{
            if(estVide(nom)){
                throw new Exception("Le nom ne doit pas etre vide");
            }
            this.nom = nom;
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public void setAdresse(String adresse){
        try {
            if (estVide(adresse)){
                throw new Exception("L'adresse ne doit pas être vide");
            }
            this.adresse = adresse;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setSyndicat(String syndicat){
        try {
            if (estVide(syndicat)){
                throw new Exception("L'id du syndicat ne doit pas être vide");
            }
            this.syndicat = syndicat;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setIdImmeuble(String id){
        try {
            if (estVide(id)){
                throw new Exception("L'id ne doit pas être vide");
            }
            else if (id.length() != 8){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<id.length();i++){
                    char c = id.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXX', avec 0<X<9");
                    }
                }
            }
    
            this.idImmeuble = id;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private boolean estVide(String ch){
        return ch.length() == 0;
    }
}