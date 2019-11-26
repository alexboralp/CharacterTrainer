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
    private String imagesPath;
    private String foodImagesPath;
    private String characterImagesPath;
    private String diseaseImagesPath;
    private String weaponImagesPath;
    private String imagesResourcePath;
    private String foodImagesResourcePath;
    private String characterImagesResourcePath;
    private String diseaseImagesResourcePath;
    private String weaponImagesResourcePath;
    
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
            
            foodImagesPath = prop.getProperty("imagesfolder");
            
            foodImagesPath = prop.getProperty("foodimagesfolder");
            characterImagesPath = prop.getProperty("characterimagesfolder");
            diseaseImagesPath = prop.getProperty("diseaseimagesfolder");
            weaponImagesPath = prop.getProperty("weaponimagesfolder");
            
            imagesResourcePath = prop.getProperty("imagesresourcefolder");
            
            foodImagesResourcePath = prop.getProperty("foodimagesresourcefolder");
            characterImagesResourcePath = prop.getProperty("characterimagesresourcefolder");
            diseaseImagesResourcePath = prop.getProperty("diseaseimagesresourcefolder");
            weaponImagesResourcePath = prop.getProperty("weaponimagesresourcefolder");
            
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

    public String getFoodImagesPath() {
        return foodImagesPath;
    }

    public String getCharacterImagesPath() {
        return characterImagesPath;
    }

    public String getDiseaseImagesPath() {
        return diseaseImagesPath;
    }

    public String getWeaponImagesPath() {
        return weaponImagesPath;
    }

    public String getFoodImagesResourcePath() {
        return foodImagesResourcePath;
    }

    public String getCharacterImagesResourcePath() {
        return characterImagesResourcePath;
    }

    public String getDiseaseImagesResourcePath() {
        return diseaseImagesResourcePath;
    }

    public String getWeaponImagesResourcePath() {
        return weaponImagesResourcePath;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public String getImagesResourcePath() {
        return imagesResourcePath;
    }
    
    
    
}
