package com.uca.core;

import com.uca.dao.SyndicatDAO;
import com.uca.entity.SyndicatEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class SyndicatCore {

    public static ArrayList<SyndicatEntity> getAllSyndicats() throws Exception{
        try{
            return new SyndicatDAO().getAllSyndicats();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static SyndicatEntity getSyndicatById(String id) throws Exception{
        try{
            return new SyndicatDAO().getSyndicatById(id);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void createSyndicat(String nom, String adresse, String referent, String telephone, String email) throws Exception {
        try {
            SyndicatEntity newSyndicat = new SyndicatEntity();
            newSyndicat.setIdSyndicat(generateNewSyndicatId());
            newSyndicat.setNom(nom);
            newSyndicat.setAdresse(adresse);
            newSyndicat.setReferent(referent);
            newSyndicat.setTelephone(telephone);
            newSyndicat.setEmail(email);
            new SyndicatDAO().create(newSyndicat);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String generateNewSyndicatId() {
        try {
            // Récupérer l'ID du dernier syndicat enregistré
            int lastSyndicatId = SyndicatDAO.getLastSyndicatId();

            // Générer le nouvel ID de syndicat en incrémentant l'ID du dernier syndicat
            int newSyndicatId = lastSyndicatId + 1;

            // Formater le nouvel ID de syndicat en une chaîne de 8 chiffres (avec des zéros à gauche si nécessaire)
            return String.format("%08d", newSyndicatId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la génération du nouvel ID de syndicat", e);
        }
    }

    public static void deleteSyndicat(String idSyndicat) throws Exception{
        try{
            new SyndicatDAO().delete(idSyndicat);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode pour vérifier si le syndicat existe déjà
public static boolean isSyndicatExists(String syndicat) {
    try {
        return new SyndicatDAO().isSyndicatExists(syndicat);
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}
