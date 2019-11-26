/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.DiseaseEditorAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author aborbon
 */
public class DiseaseBtnAddCure implements ActionListener {
    
    DiseaseEditorAdmin admin;

    public DiseaseBtnAddCure(DiseaseEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cure = admin.GUIGetCureName();
        if (!"".equals(cure)) {
            admin.addCure(cure);
        }
    }
    
}
