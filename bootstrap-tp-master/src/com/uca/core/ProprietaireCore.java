package com.uca.core;

import com.uca.dao.AppartementDAO;
import com.uca.dao.ProprietaireDAO;
import com.uca.dao.PersonneDAO;
import com.uca.entity.ProprietaireEntity;
import com.uca.entity.PersonneEntity;

import java.util.ArrayList;

public class ProprietaireCore {

    public static ArrayList<ProprietaireEntity> getAllProprietaires() {
        return new ProprietaireDAO().getAllProprietaires();
    }

    public void createProprietaireForAppartement(String idAppartement, String nomProprietaire, String prenomProprietaire, String numeroProprietaire, String emailProprietaire) throws Exception {
        try {
            String idProprietaire = PersonneCore.generateNewPersonneId();
            ProprietaireEntity newProprietaire = new ProprietaireEntity();
            newProprietaire.setIdPersonne(idProprietaire);
            newProprietaire.setNom(nomProprietaire);
            newProprietaire.setPrenom(prenomProprietaire);
            newProprietaire.setTelephone(numeroProprietaire);
            newProprietaire.setEmail(emailProprietaire);
            new ProprietaireDAO().create(newProprietaire);
            new AppartementDAO().modifyProprietaire(idProprietaire, idAppartement);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void assignProprietaireForAppartement(String idAppartement, String idProprietaire) throws Exception {
        try {
            new AppartementDAO().modifyProprietaire(idProprietaire, idAppartement);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean isProprietaireExists(String proprietaire) throws Exception{
        try{
            return new ProprietaireDAO().isProprietaireExists(proprietaire);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public boolean isProprietaireExists2(String proprietaireName, String proprietaireSurname) throws Exception{
        try{
            return new ProprietaireDAO().isProprietaireExists2(proprietaireName, proprietaireSurname);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteProprietaireForAppartement(String idAppartement, String idProprietaire) throws Exception {
        try {
            new AppartementDAO().modifyProprietaire(idAppartement, "12345678");
            boolean otherProprio = new ProprietaireDAO().isProprietaireUsefull(idProprietaire);
            System.out.println(getAllProprietaires());
            System.out.println("test" + otherProprio);
            if (!otherProprio){
                ProprietaireEntity newProprio = new ProprietaireEntity();
                newProprio.setIdPersonne(idProprietaire);
                System.out.println("e1");
                new PersonneDAO().delete(idProprietaire);
            System.out.println("e2");
                new ProprietaireDAO().delete(newProprio);
            }
        } catch (Exception e) {
            System.out.println("erreur");
            e.printStackTrace();
            throw e;
        }
    }

    public static ProprietaireEntity getPropByAppart(String id) throws Exception{
        try{
            return new ProprietaireDAO().getPropByAppart(id);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}

