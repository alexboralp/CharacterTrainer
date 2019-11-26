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

    public WeaponBtnImage(WeaponEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(MainProperties.getInstance().getCurrentPath()));
        jfc.showOpenDialog(admin.getFrame());
        File abre=jfc.getSelectedFile();
        if(abre!=null) {
            String path = abre.getAbsolutePath();
            ImageIconManager.getInstance().addImage(path);
            admin.GUIShowIcon(ImageIconManager.getInstance().getImage(path));
            admin.GUISetPath(path);
        }
    }
    
}
