/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import cc.images.ImageIconManager;
import controller.FoodEditorAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import model.utils.MainProperties;

/**
 *
 * @author aborbon
 */
public class FoodBtnImage implements ActionListener {
    
    private final FoodEditorAdmin admin;
    
    private final String completeFoodPath;
    private final String foodPath;

    public FoodBtnImage(FoodEditorAdmin admin) {
        this.admin = admin;
        completeFoodPath = MainProperties.getInstance().getCurrentPath() + MainProperties.getInstance().getSourceFolderPath() + MainProperties.getInstance().getFoodImagesPath();
        foodPath = MainProperties.getInstance().getFoodImagesPath();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(completeFoodPath));
        jfc.showOpenDialog(admin.getFrame());
        File abre=jfc.getSelectedFile();
        if(abre!=null) {
            String completePath = abre.getAbsolutePath();
            int pos = completePath.indexOf(foodPath);
            String path = completePath.substring(pos);
            ImageIconManager.getInstance().addImage(path);
            admin.GUIShowIcon(ImageIconManager.getInstance().getImage(path));
            admin.GUISetPath(path);
        }
    }
    
}
