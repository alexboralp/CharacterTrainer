/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.DiseaseEditorAdmin;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author aborbon
 */
public class DiseaseLstEffects implements ListSelectionListener {
    
    DiseaseEditorAdmin admin;

    public DiseaseLstEffects(DiseaseEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.admin.GUIShowEffectSelected();
    }
    
}
