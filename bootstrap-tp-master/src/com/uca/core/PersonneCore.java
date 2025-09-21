package com.uca.core;

import com.uca.dao.PersonneDAO;
import com.uca.entity.PersonneEntity;

import java.util.ArrayList;

public class PersonneCore {
    public static ArrayList<PersonneEntity> getAllPersonnes() throws Exception{
        try{
            return new PersonneDAO().getAllPersonnes();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static String generateNewPersonneId() {
        try {
            // Récupérer l'ID du dernier Proprietaire enregistré
            int lastPersonneId = new PersonneDAO().getLastPersonneId();
    
            // Générer le nouvel ID d'Proprietaire en incrémentant l'ID du dernier Proprietaire
            int newPersonneId = lastPersonneId + 1;
    
            // Formater le nouvel ID d'Proprietaire en une chaîne de 8 chiffres (avec des zéros à gauche si nécessaire)
            String formattedPersonneId = String.format("%08d", newPersonneId);
    
            return formattedPersonneId;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

