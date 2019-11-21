/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

import fileioutils.FDBFact;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aborbon
 */
public class MainProperties {
    
    private static MainProperties mainProperties;
    
    private String currentPath;
    private String foodPath;
    private String characterPath;
    private String diseasePath;
    private String weaponPath;
    private String propertiesPath;
    
    private MainProperties() {
        try {
            Path currentRelativePath = Paths.get("");
            currentPath = currentRelativePath.toAbsolutePath().toString();
            
            propertiesPath = currentPath + "/src/model/MainProperties.properties";
            
            Properties prop = (Properties)fileioutils.FDBFact.getIntance().create(FDBFact.DB_TYPE.DBProperties).read(propertiesPath);
            
            foodPath = currentPath + "/src" + prop.getProperty("foodfolder");
            characterPath = currentPath + "/src" + prop.getProperty("characterfolder");
            diseasePath = currentPath + "/src" + prop.getProperty("diseasefolder");
            weaponPath = currentPath + "/src" + prop.getProperty("weaponfolder");
        } catch (IOException ex) {
            Logger.getLogger(MainProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MainProperties getInstance() {
        if (mainProperties == null) {
            mainProperties = new MainProperties();
        }
        return mainProperties;
    }
    
    public String getCurrentPath() {
        return currentPath;
    }

    public String getFoodPath() {
        return foodPath;
    }

    public String getCharacterPath() {
        return characterPath;
    }

    public String getDiseasePath() {
        return diseasePath;
    }

    public String getWeaponPath() {
        return weaponPath;
    }
    
}
