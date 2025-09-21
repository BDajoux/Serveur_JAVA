package com.uca.core;

import com.uca.dao.AppartementDAO;
import com.uca.entity.AppartementEntity;


import java.util.ArrayList;

public class AppartementCore {
    public static ArrayList<AppartementEntity> getAllAppartements() throws Exception{
        try{
            return new AppartementDAO().getAllAppartements();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<AppartementEntity> getAppartByImm(String id) throws Exception{
        try{
            return new AppartementDAO().getAppartByImm(id);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static AppartementEntity getAppartById(String id) throws Exception{
        try{
            return new AppartementDAO().getAppartById(id);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void createAppartement(String numeroAppartement, String etageAppartement, int superficieAppartement, String idImmeuble) throws Exception {
        try {
            AppartementEntity newAppartement = new AppartementEntity();
            newAppartement.setIdAppartement(AppartementCore.generateNewAppartementId());
            newAppartement.setNumeroAppartement(numeroAppartement);
            newAppartement.setEtage(etageAppartement);
            newAppartement.setSuperficie(superficieAppartement);
            newAppartement.setIdImmeuble(idImmeuble);
            new AppartementDAO().create(newAppartement);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void deleteAppartement(String idAppartement) throws Exception {
        try {
            new AppartementDAO().delete(idAppartement);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String generateNewAppartementId() {
        try {
            // Récupérer l'ID du dernier appartement enregistré
            int lastAppartementId = new AppartementDAO().getLastAppartementId();
    
            // Générer le nouvel ID d'appartement en incrémentant l'ID du dernier appartement
            int newAppartementId = lastAppartementId + 1;
    
            // Formater le nouvel ID d'appartement en une chaîne de 8 chiffres (avec des zéros à gauche si nécessaire)
            String formattedAppartementId = String.format("%08d", newAppartementId);
    
            return formattedAppartementId;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    // Méthode pour vérifier si l'appartement existe déjà
public static boolean isAppartementExists(String appartement) {
    try {
        return new AppartementDAO().isAppartementExists(appartement);
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}
