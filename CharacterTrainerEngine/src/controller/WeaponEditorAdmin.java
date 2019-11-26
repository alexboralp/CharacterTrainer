/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cc.factories.WeaponFactory;
import cc.images.IImageFile;
import cc.images.ImageFile;
import cc.images.ImageIconManager;
import cc.weapon.interfaces.IWeapon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import fileioutils.FDBFact;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.utils.MainProperties;
import model.weapon.WeaponList;
import vista.editors.WeaponEditor;
import vista.listeners.WeaponBtnAdd;
import vista.listeners.WeaponBtnAddImage;
import vista.listeners.WeaponBtnDeleteImage;
import vista.listeners.WeaponBtnImage;
import vista.listeners.WeaponBtnNext;
import vista.listeners.WeaponBtnPrevious;
import vista.listeners.WeaponBtnRemove;
import vista.listeners.WeaponBtnSave;
import vista.listeners.WeaponLstImages;

/**
 *
 * @author alexander
 */
public class WeaponEditorAdmin implements IAdmin {
    private WeaponEditor frmWeaponEditor;
    
    private WeaponList weaponList;
    private DefaultListModel<IImageFile> lstImagesModel;
    
    private int actualWeapon;

    public WeaponEditorAdmin() {
        _init_();
    }

    public WeaponEditorAdmin(WeaponEditor frmWeaponEditor) {
        this.frmWeaponEditor = frmWeaponEditor;
        _init_();
        _init_components_();
    }
    
    private void _init_() {
        weaponList = new WeaponList();
        
        loadWeapon();
    }
    
    private void _init_components_() {
        frmWeaponEditor.btnSave.addActionListener(new WeaponBtnSave(this));
        frmWeaponEditor.btnPrevious.addActionListener(new WeaponBtnPrevious(this));
        frmWeaponEditor.btnNext.addActionListener(new WeaponBtnNext(this));
        frmWeaponEditor.btnImage.addActionListener(new WeaponBtnImage(this));
        frmWeaponEditor.btnAdd.addActionListener(new WeaponBtnAdd(this));
        frmWeaponEditor.btnDelete.addActionListener(new WeaponBtnRemove(this));
        frmWeaponEditor.btnAddImage.addActionListener(new WeaponBtnAddImage(this));
        frmWeaponEditor.btnDeleteImage.addActionListener(new WeaponBtnDeleteImage(this));
        frmWeaponEditor.lstImages.addListSelectionListener(new WeaponLstImages(this));
        
        lstImagesModel = new DefaultListModel();
        frmWeaponEditor.lstImages.setModel(lstImagesModel);
        
        if (weaponList.size() > 0) {
            GUIShowWeapon(weaponList.getFirst());
            actualWeapon = 0;
        }
    }
    
    public void GUIShowWeapon(IWeapon weapon) {
        GUISetName(weapon.getName());
        GUISetDamage(weapon.getDamage());
        GUIReplaceImagesList(weapon.getImageValues());
        /*GUISetPath(weapon.getPath());
        GUISetFullnesLevel(weapon.getFullnesLevel());
        GUISetLiquidsLevel(weapon.getLiquidsLevel());
        GUISetWeaponIcon(ImageIconManager.getInstance().getImage(weapon.getPath()));*/
    }
    
    public void GUIAddImagesList(Collection<IImageFile> images) {
        lstImagesModel.addAll(images);
    }
    
    public void GUIReplaceImagesList(Collection<IImageFile> images) {
        GUIRemoveImagesList();
        GUIAddImagesList(images);
    }
    
    public void GUIRemoveImagesList() {
        lstImagesModel.removeAllElements();
    }
    
    public void GUIAddImageList(IImageFile image) {
        lstImagesModel.addElement(image);
    }
    
    public void GUIRemoveImageList() {
        lstImagesModel.remove(frmWeaponEditor.lstImages.getSelectedIndex());
    }
    
    public Enumeration<IImageFile> GUIGetImagesList () {
        return lstImagesModel.elements(); //weaponList.get(actualWeapon).getImageValues();
    }
    
    public void GUIImageSelected() {
        if (frmWeaponEditor.lstImages.getSelectedIndex() != -1) {
            IImageFile image = (IImageFile)lstImagesModel.get(frmWeaponEditor.lstImages.getSelectedIndex());
            GUISetPath(image.getPath());
            GUISetLevel(image.getLevel());
            GUIShowIcon(ImageIconManager.getInstance().getImage(image.getPath()));
        } else {
            GUISetPath("");
            GUISetLevel(1);
            GUIShowIcon(null);
        }
    }
    
    public void GUIShowIcon(ImageIcon weaponIcon) {
        GUISetWeaponIcon(weaponIcon);
    }
    
    public void GUIDeleteWeapon() {
        GUISetName("");
        GUISetPath("");
        frmWeaponEditor.spnDamage.setValue(1);
        frmWeaponEditor.spnLevel.setValue(1);
        GUISetWeaponIcon(null);
    }
    
    public String GUIGetName() {
        return frmWeaponEditor.txtName.getText();
    }
    
    public void GUISetName(String name) {
        frmWeaponEditor.txtName.setText(name);
    }
    
    public int GUIGetDamage() {
        return (int)frmWeaponEditor.spnDamage.getValue();
    }
    
    public void GUISetDamage(int damage) {
        frmWeaponEditor.spnDamage.setValue(damage);
    }
    
    public String GUIGetPath() {
        return frmWeaponEditor.txtPath.getText();
    }
    
    public void GUISetPath(String path) {
        ImageIconManager.getInstance().addImage(path);
        frmWeaponEditor.txtPath.setText(path);
    }
    
    public int GUIGetLevel() {
        return (int)frmWeaponEditor.spnLevel.getValue();
    }
    
    public void GUISetLevel(int level) {
        frmWeaponEditor.spnLevel.setValue(level);
    }
    
    public ImageIcon GUIGetWeaponIcon() {
        return (ImageIcon)frmWeaponEditor.lblImage.getIcon();
    }
    
    public void GUISetWeaponIcon(ImageIcon icon) {
        frmWeaponEditor.lblImage.setIcon(icon);
    }
    
    public void addWeapon(IWeapon weapon) {
        weaponList.add(weapon);
    }
    
    public void nextWeapon() {
        actualWeapon++;
        if (actualWeapon < weaponList.size()) {
            GUIShowWeapon(weaponList.get(actualWeapon));
        } else {
            actualWeapon = weaponList.size() - 1;
            GUIShowWeapon(weaponList.get(actualWeapon));
        }
    }
    
    public void previousWeapon() {
        actualWeapon--;
        if (actualWeapon >= 0) {
            GUIShowWeapon(weaponList.get(actualWeapon));
        } else {
            actualWeapon = 0;
            GUIShowWeapon(weaponList.get(actualWeapon));
        }
    }
    
    public void addFirst(IWeapon weapon) {
        weaponList.addFirst(weapon);
    }
    
    public void addLast(IWeapon weapon) {
        weaponList.addLast(weapon);
    }
    
    public void add(int index, IWeapon weapon) {
        weaponList.add(index, weapon);
    }
    
    public void add(IWeapon weapon) {
        if (weaponList.size() > 0) {
            weaponList.add(actualWeapon, weapon);
        } else {
            addFirst(weapon);
        }
    }
    
    public void remove(int index) {
        weaponList.remove(index);
        if (actualWeapon < weaponList.size()) {
            actualWeapon = weaponList.size() - 1;
        }
        if (actualWeapon != -1) {
            GUIShowWeapon(weaponList.get(actualWeapon));
        } else {
            GUIDeleteWeapon();
        }
    }
    
    public void remove() {
        weaponList.remove(actualWeapon);
    }
    
    public void saveWeapon() {
        try {
            //System.out.println(MainProperties.getInstance().getWeaponPath() + MainProperties.getInstance().getPathSeparator() + "Weapon.json");
            fileioutils.FDBFact.getIntance().create(FDBFact.DB_TYPE.DBJson).write(MainProperties.getInstance().getWeaponPath() + MainProperties.getInstance().getPathSeparator() + "Weapon.json", weaponList);
        } catch (IOException ex) {
            Logger.getLogger(WeaponEditorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadWeapon() {
        try {
            
            File file = new File(MainProperties.getInstance().getWeaponPath() + MainProperties.getInstance().getPathSeparator() + "Weapon.json");
            if (file.exists()) {
                String jsonString = (String) fileioutils.FDBFact.getIntance().create(FDBFact.DB_TYPE.DBJson).read(file.getAbsolutePath());

                Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
                
                LinkedList weapons = gson.fromJson(jsonString, new TypeToken<LinkedList<JsonObject>>() {}.getType());
                
                weapons.forEach(x -> {
                    System.out.println(x.toString());
                    String name = ((JsonObject)x).get("name").getAsString();
                    int reachDistance = ((JsonObject)x).get("reachDistance").getAsInt();
                    int damage = ((JsonObject)x).get("damage").getAsInt();
                    int level = ((JsonObject)x).get("level").getAsInt();
                    int range = ((JsonObject)x).get("range").getAsInt();
                    int cost = ((JsonObject)x).get("cost").getAsInt();
                    int speed = ((JsonObject)x).get("speed").getAsInt();
                    int maxLevel = ((JsonObject)x).get("maxLevel").getAsInt();
                    int availableLevel = ((JsonObject)x).get("availableLevel").getAsInt();
                    
                    IWeapon weapon = WeaponFactory.createWeapon(name, reachDistance, damage, level, range, cost, speed, maxLevel, availableLevel);
                    
                    JsonElement images = ((JsonObject)x).get("images");
                    JsonObject objImages = images.getAsJsonObject();
                    
                    Set<Entry<String, JsonElement>> es = objImages.entrySet();
                    es.forEach(z -> {
                        String key = z.getKey();
                        JsonElement e = z.getValue();
                        ImageFile imageFile1 = gson.fromJson(e.toString(), ImageFile.class);
                        
                        weapon.addAppearance(imageFile1);
                    });
                    
                    weaponList.add(weapon);
                });
            }
            
        } catch (IOException ex) {
            Logger.getLogger(WeaponEditorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setFrame(JFrame jFrame) {
        this.frmWeaponEditor = (WeaponEditor)jFrame;
        _init_components_();
    }

    @Override
    public JFrame getFrame() {
        return frmWeaponEditor;
    }
    
    
}
