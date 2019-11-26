/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.DiseaseEditorAdmin;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author aborbon
 */
public class DiseaseSldDuration implements ChangeListener {
    
    DiseaseEditorAdmin admin;

    public DiseaseSldDuration(DiseaseEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!((JSlider)e.getSource()).getValueIsAdjusting()) {
            admin.GUISetDurationLabel(Integer.toString(admin.GUIGetDuration()));
        }
    }
    
}
