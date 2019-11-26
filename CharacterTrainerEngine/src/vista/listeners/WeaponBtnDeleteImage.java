/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.WeaponEditorAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author aborbon
 */
public class WeaponBtnDeleteImage implements ActionListener {
    WeaponEditorAdmin admin;

    public WeaponBtnDeleteImage(WeaponEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        admin.GUIRemoveImageList();
    }
    
}
