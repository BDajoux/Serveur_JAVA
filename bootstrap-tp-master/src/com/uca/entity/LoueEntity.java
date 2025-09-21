package com.uca.entity;

public class LoueEntity {
    private String idAppartement;
    private String idPersonne;

    public String getIdAppartement() {
        return this.idAppartement;
    }

    public String getIdPersonne() {
        return this.idPersonne;
    }

    public void setIdAppartement(String idAppartement) {
        try {
            if (estVide(idAppartement)){
                throw new Exception("L'd ne doit pas être vide");
            }
            else if (idAppartement.length() != 12){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<idAppartement.length();i++){
                    char c = idAppartement.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXXXXXX', avec 0<X<9");
                    }
                }
            }
    
            this.idAppartement = idAppartement;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setIdPersonne(String idPersonne) {
        try {
            if (estVide(idPersonne)){
                throw new Exception("Le téléphone ne doit pas être vide");
            }
            else if (idPersonne.length() != 12){
                throw new Exception("L'id n'est pas au bon format: 'XXXXXXXXXXXX', avec 0<X<9");
            }
            else{
                for(int i=0;i<idPersonne.length();i++){
                    char c = idPersonne.charAt(i);
                    if(!(Character.isDigit(c))){
                        throw new Exception("L'id n'est pas au bon format: 'XXXXXXXXXXXX', avec 0<X<9");
                    }
                }
            }
            this.idPersonne = idPersonne;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private boolean estVide(String ch){
        return ch.length() == 0;
    }
}
