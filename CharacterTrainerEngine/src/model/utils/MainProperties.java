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
import osutils.OS;

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
    private String editorsPath;
    private String pathSeparator;
    
    private MainProperties() {
        try {
            
            if (OS.isWindows()) {
                pathSeparator = "\\";
            } else if (OS.isUnix()) {
                pathSeparator = "/";
            } /*else if (OS.isMac()) {

            } else if (OS.isSolaris()) {

            }*/
            
            Path currentRelativePath = Paths.get("");
            currentPath = currentRelativePath.toAbsolutePath().toString(); //currentRelativePath.toString();
            
            String srcFolder = pathSeparator + "src";
            String propertiesPathExtra = srcFolder + pathSeparator + "model" + pathSeparator + "MainProperties.properties";
            
            propertiesPath = currentPath + propertiesPathExtra;
            
            Properties prop = (Properties)fileioutils.FDBFact.getIntance().create(FDBFact.DB_TYPE.DBProperties).read(propertiesPath);
            
            String foodPropertiesExtra = prop.getProperty("foodfolder");
            String characterPropertiesExtra = prop.getProperty("characterfolder");
            String diseasePropertiesExtra = prop.getProperty("diseasefolder");
            String weaponPropertiesExtra = prop.getProperty("weaponfolder");
            String editorsPropertiesExtra = prop.getProperty("editorsfolder");
            
            if (!"/".equals(pathSeparator)) {
                foodPropertiesExtra = foodPropertiesExtra.replaceAll("/", pathSeparator);
                characterPropertiesExtra = characterPropertiesExtra.replaceAll("/", pathSeparator);
                diseasePropertiesExtra = diseasePropertiesExtra.replaceAll("/", pathSeparator);
                weaponPropertiesExtra = weaponPropertiesExtra.replaceAll("/", pathSeparator);
                editorsPropertiesExtra = editorsPropertiesExtra.replaceAll("/", pathSeparator);
            }
            
            foodPath = currentPath + srcFolder + foodPropertiesExtra;
            characterPath = currentPath + srcFolder + characterPropertiesExtra;
            diseasePath = currentPath + srcFolder + diseasePropertiesExtra;
            weaponPath = currentPath + srcFolder + weaponPropertiesExtra;
            editorsPath = currentPath + srcFolder + editorsPropertiesExtra;
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

    public String getEditorsPath() {
        return editorsPath;
    }

    public void setEditorsPath(String editorsPath) {
        this.editorsPath = editorsPath;
    }
    
    public String getPathSeparator() {
        return pathSeparator;
    }
    
}
