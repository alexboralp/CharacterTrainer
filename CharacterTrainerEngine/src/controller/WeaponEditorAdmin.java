/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JFrame;
import vista.editors.WeaponEditor;

/**
 *
 * @author alexander
 */
public class WeaponEditorAdmin implements IAdmin {
    WeaponEditor frmWeaponEditor;

    public WeaponEditorAdmin() { }

    public WeaponEditorAdmin(WeaponEditor frmWeaponEditor) {
        this.frmWeaponEditor = frmWeaponEditor;
    }

    @Override
    public void setFrame(JFrame jFrame) {
        this.frmWeaponEditor = (WeaponEditor)jFrame;
    }

    @Override
    public JFrame getFrame() {
        return frmWeaponEditor;
    }
    
    
}
