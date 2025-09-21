package com.uca.core;

import com.uca.dao.ImmeubleDAO;
import com.uca.entity.ImmeubleEntity;

import java.util.ArrayList;

public class ImmeubleCore {

    public static ArrayList<ImmeubleEntity> getAllImmeubles() throws Exception{
        try{
            return new ImmeubleDAO().getAllImmeubles();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static ArrayList<ImmeubleEntity> getImmeublesBySyndic(String id) throws Exception{
        try{
            return new ImmeubleDAO().getImmeublesBySyndic(id);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static ImmeubleEntity getImmById(String id) throws Exception{
        try{
            return new ImmeubleDAO().getImmById(id);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void createImmeuble(String nomImmeuble, String adresseImmeuble, String idSyndicat, int nbAppart) throws Exception {
        try {
            System.out.println("alle");
            ImmeubleEntity newImmeuble = new ImmeubleEntity();
            newImmeuble.setIdImmeuble(ImmeubleCore.generateNewImmeubleId());
            newImmeuble.setNom(nomImmeuble);
            newImmeuble.setAdresse(adresseImmeuble);
            newImmeuble.setSyndicat(idSyndicat);
            newImmeuble.setNbAppart(nbAppart);
            new ImmeubleDAO().create(newImmeuble);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void deleteImmeuble(String idImmeuble) throws Exception{
        try{
            new ImmeubleDAO().delete(idImmeuble);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static String generateNewImmeubleId() {
        try {
            // Récupérer l'ID du dernier immeuble enregistré
            int lastImmeubleId = new ImmeubleDAO().getLastImmeubleId();
    
            // Générer le nouvel ID d'immeuble en incrémentant l'ID du dernier immeuble
            int newImmeubleId = lastImmeubleId + 1;
    
            // Formater le nouvel ID d'immeuble en une chaîne de 8 chiffres (avec des zéros à gauche si nécessaire)
            String formattedImmeubleId = String.format("%08d", newImmeubleId);
    
            return formattedImmeubleId;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    // Méthode pour vérifier si l'immeuble existe déjà
public static boolean isImmeubleExists(String immeuble) {
    try {
        return new ImmeubleDAO().isImmeubleExists(immeuble);
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}
