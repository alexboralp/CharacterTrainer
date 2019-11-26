/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import cc.images.ImageIconManager;
import controller.WeaponEditorAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import model.utils.MainProperties;

/**
 *
 * @author aborbon
 */
public class WeaponBtnImage implements ActionListener {
    
    WeaponEditorAdmin admin;
    
    private final String completeWeaponPath;
    private final String weaponPath;

    public WeaponBtnImage(WeaponEditorAdmin admin) {
        this.admin = admin;
        
        completeWeaponPath = MainProperties.getInstance().getCurrentPath() + MainProperties.getInstance().getSourceFolderPath() + MainProperties.getInstance().getWeaponImagesPath();
        weaponPath = MainProperties.getInstance().getWeaponImagesPath();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(completeWeaponPath));
        jfc.showOpenDialog(admin.getFrame());
        File abre=jfc.getSelectedFile();
        if(abre!=null) {
            String completePath = abre.getAbsolutePath();
            int pos = completePath.indexOf(weaponPath);
            String path = completePath.substring(pos);
            ImageIconManager.getInstance().addImage(path);
            admin.GUIShowIcon(ImageIconManager.getInstance().getImage(path));
            admin.GUISetPath(path);
        }
    }
    
}
