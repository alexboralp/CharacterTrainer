/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import cc.WeaponBuilder;
import cc.images.IImageFile;
import controller.WeaponEditorAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 *
 * @author aborbon
 */
public class WeaponBtnAdd implements ActionListener {
    
    WeaponEditorAdmin admin;

    public WeaponBtnAdd(WeaponEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WeaponBuilder weaponBuilder = new WeaponBuilder();
        weaponBuilder.setName(admin.GUIGetName());
        weaponBuilder.setDamage(admin.GUIGetDamage());
        Iterator<IImageFile> iterator = admin.GUIGetImagesList().asIterator();
        while (iterator.hasNext()) {
            weaponBuilder.addAppearance(iterator.next());
        }
        admin.addWeapon(weaponBuilder.build());
    }
    
}
