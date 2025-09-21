package com.uca.core;

import com.uca.dao.LocataireDAO;
import com.uca.entity.LocataireEntity;

import java.util.ArrayList;

public class LocataireCore {
    public static ArrayList<LocataireEntity> getAllLocataires() throws Exception{
        try{
            return new LocataireDAO().getAllLocataires();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static LocataireEntity getLocByAppart(String id) throws Exception{
        try{
            return new LocataireDAO().getLocByAppart(id);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

}
