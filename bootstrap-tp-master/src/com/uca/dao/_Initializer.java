package com.uca.dao;

import java.sql.*;

import com.uca.StartServer;
import org.mindrot.jbcrypt.BCrypt;

public class _Initializer {

    public static void Init(){
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;

            /*statement = connection.prepareStatement("DROP TABLE IF EXISTS appartements,immeuble,syndicat;");
            statement.executeUpdate();*/

            //Init articles table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (id VARCHAR(8), firstname VARCHAR(100), lastname VARCHAR(100), username VARCHAR(100), email VARCHAR(100), password VARCHAR(1000), role VARCHAR(2), PRIMARY KEY (id));  ");
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS personne(id_personne VARCHAR(8), nom_personne VARCHAR(50), prenom_personne VARCHAR(50), telephone_personne VARCHAR(10), email_personne VARCHAR(50), PRIMARY KEY(id_personne));"); 
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE  IF NOT EXISTS syndicat(id_syndicat VARCHAR(8), nom_syndicat VARCHAR(50), adresse_syndicat VARCHAR(50), referent_syndicat VARCHAR(50), numero_telephone_syndicat VARCHAR(10), email_syndicat VARCHAR(50), PRIMARY KEY(id_syndicat));");  
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS immeuble(id_immeuble VARCHAR(8), nom_immeuble VARCHAR(50), adresse_immeuble VARCHAR(50), identifiant_syndicat VARCHAR(8) NOT NULL, nb_appartements INT NOT NULL, PRIMARY KEY(id_immeuble), FOREIGN KEY(identifiant_syndicat) REFERENCES Syndicat(id_syndicat));");
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS proprietaire(id_personne VARCHAR(8), PRIMARY KEY(id_personne), FOREIGN KEY(id_personne) REFERENCES Personne(id_personne));");
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS locataire(id_personne VARCHAR(8), PRIMARY KEY(id_personne), FOREIGN KEY(id_personne) REFERENCES Personne(id_personne));");        
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS appartement(id_appartement VARCHAR(8), numero_appartement INT NOT NULL, etage_appartement INT, superficie_appartement INT, id_immeuble VARCHAR(8), id_proprietaire VARCHAR(8), id_locataire VARCHAR(8), PRIMARY KEY(id_appartement), FOREIGN KEY(id_immeuble) REFERENCES Immeuble(id_immeuble), FOREIGN KEY(id_proprietaire) REFERENCES Proprietaire(id_personne), FOREIGN KEY(id_locataire) REFERENCES Locataire(id_personne));");
            statement.executeUpdate();
            
            statement = connection.prepareStatement("INSERT INTO users (id, firstname, lastname, username, email, password, role) SELECT ?, ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = ?);");
            statement.setString(1, "00351352");
            statement.setString(2, "Main");
            statement.setString(3, "Admin");
            statement.setString(4, "admin_super");
            statement.setString(5, "main.admin@gmail.com");
            statement.setString(6, BCrypt.hashpw("AB*093eggTvAlNu8AV4.O", "$2a$10$KRAprlMNgO9LYmog8VV3gu"));
            statement.setString(7, "0");
            statement.setString(8, "admin_super");
            statement.executeUpdate();
            
            /*statement = connection.prepareStatement("INSERT INTO syndicat(id_syndicat, nom_syndicat, adresse_syndicat, referent_syndicat, numero_telephone_syndicat, email_syndicat) VALUES(?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000001");
            statement.setString(2, "CEGADIM");
            statement.setString(3, "2 rue de Clermont");
            statement.setString(4, "DAJOUX");
            statement.setString(5, "0456789834");
            statement.setString(6, "cegadim@syndic.fr");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO syndicat(id_syndicat, nom_syndicat, adresse_syndicat, referent_syndicat, numero_telephone_syndicat, email_syndicat) VALUES(?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000002");
            statement.setString(2, "SIIMMO");
            statement.setString(3, "2 avenue du Chemin");
            statement.setString(4, "MANET");
            statement.setString(5, "0473467322");
            statement.setString(6, "siimmo@syndic.fr");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO syndicat(id_syndicat, nom_syndicat, adresse_syndicat, referent_syndicat, numero_telephone_syndicat, email_syndicat) VALUES(?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000003");
            statement.setString(2, "ABMAISON");
            statement.setString(3, "5 Chemin de la tuile");
            statement.setString(4, "ARTU");
            statement.setString(5, "0456789834");
            statement.setString(6, "abmaison@syndic.fr");
            statement.executeUpdate();





            statement = connection.prepareStatement("INSERT INTO immeuble(id_immeuble, nom_immeuble, adresse_immeuble, identifiant_syndicat, nb_appartements) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, "00000001");
            statement.setString(2, "Province de france");
            statement.setString(3, "40 rue Corot");
            statement.setString(4, "00000002");
            statement.setString(5, "20");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO immeuble(id_immeuble, nom_immeuble, adresse_immeuble, identifiant_syndicat, nb_appartements) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, "00000002");
            statement.setString(2, "Terrasses de la grande ville");
            statement.setString(3, "8 avenue Pasteur");
            statement.setString(4, "00000002");
            statement.setString(5, "50");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO immeuble(id_immeuble, nom_immeuble, adresse_immeuble, identifiant_syndicat, nb_appartements) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, "00000003");
            statement.setString(2, "Résidence de la fontaine");
            statement.setString(3, "20 route de Champeix");
            statement.setString(4, "00000001");
            statement.setString(5, "37");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO personne(id_personne, nom_personne, prenom_personne, telephone_personne, email_personne) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1,"00000001");
            statement.setString(2,"Leboeuf");
            statement.setString(3,"Franck");
            statement.setString(4,"0650478264");
            statement.setString(5,"franck.leboeuf@uca.fr");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO personne(id_personne, nom_personne, prenom_personne, telephone_personne, email_personne) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1,"00000002");
            statement.setString(2,"Latour");
            statement.setString(3,"Jeanne");
            statement.setString(4,"0648627492");
            statement.setString(5,"jeanne2@gmail.com");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO personne(id_personne, nom_personne, prenom_personne, telephone_personne, email_personne) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1,"00000003");
            statement.setString(2,"Servi");
            statement.setString(3,"Clément");
            statement.setString(4,"0648624582");
            statement.setString(5,"serviclem@gmail.com");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO proprietaire(id_personne) VALUES(?);");
            statement.setString(1,"00000001");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO personne(id_personne, nom_personne, prenom_personne, telephone_personne, email_personne) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1,"00000004");
            statement.setString(2,"Rousset");
            statement.setString(3,"David");
            statement.setString(4,"0623658164");
            statement.setString(5,"david.rousset@icloud.fr");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO locataire(id_personne) VALUES(?);");
            statement.setString(1,"00000002");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO locataire(id_personne) VALUES(?);");
            statement.setString(1,"00000003");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO locataire(id_personne) VALUES(?);");
            statement.setString(1,"00000004");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000001");
            statement.setString(2, "4012");
            statement.setString(3, "4");
            statement.setString(4, "30");
            statement.setString(5, "00000001");
            statement.setString(6, "00000001");
            statement.setString(7, "00000002");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000002");
            statement.setString(2, "1012");
            statement.setString(3, "1");
            statement.setString(4, "40");
            statement.setString(5, "00000001");
            statement.setString(6, "00000001");
            statement.setString(7, "00000003");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000003");
            statement.setString(2, "1033");
            statement.setString(3, "1");
            statement.setString(4, "20");
            statement.setString(5, "00000001");
            statement.setString(6, "00000001");
            statement.setString(7, "00000004");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000004");
            statement.setString(2, "1222");
            statement.setString(3, "2");
            statement.setString(4, "30");
            statement.setString(5, "00000002");
            statement.setString(6, "00000001");
            statement.setString(7, "00000002");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000005");
            statement.setString(2, "1016");
            statement.setString(3, "1");
            statement.setString(4, "50");
            statement.setString(5, "00000002");
            statement.setString(6, "00000001");
            statement.setString(7, "00000003");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000006");
            statement.setString(2, "1024");
            statement.setString(3, "1");
            statement.setString(4, "20");
            statement.setString(5, "00000002");
            statement.setString(6, "00000001");
            statement.setString(7, "00000004");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000007");
            statement.setString(2, "4012");
            statement.setString(3, "4");
            statement.setString(4, "30");
            statement.setString(5, "00000003");
            statement.setString(6, "00000001");
            statement.setString(7, "00000002");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000008");
            statement.setString(2, "1012");
            statement.setString(3, "1");
            statement.setString(4, "40");
            statement.setString(5, "00000003");
            statement.setString(6, "00000001");
            statement.setString(7, "00000003");
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "00000009");
            statement.setString(2, "1033");
            statement.setString(3, "1");
            statement.setString(4, "20");
            statement.setString(5, "00000003");
            statement.setString(6, "00000001");
            statement.setString(7, "00000004");
            statement.executeUpdate();
            
            statement = connection.prepareStatement("INSERT INTO appartement(id_appartement, numero_appartement, etage_appartement, superficie_appartement, id_immeuble, id_proprietaire, id_locataire) VALUES(?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, "32132376");
            statement.setString(2, "4113");
            statement.setString(3, "41");
            statement.setString(4, "30");
            statement.setString(5, "12222614");
            statement.setString(6, "15415614");
            statement.setString(7, "19844614");
            statement.executeUpdate();*/

            
        } catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException("could not create database !");
        }
    }
}
