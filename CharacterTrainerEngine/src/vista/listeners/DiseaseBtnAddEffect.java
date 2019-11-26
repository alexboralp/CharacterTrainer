/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.DiseaseEditorAdmin;
import model.disease.EffectFact;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author aborbon
 */
public class DiseaseBtnAddEffect implements ActionListener {
    
    DiseaseEditorAdmin admin;

    public DiseaseBtnAddEffect(DiseaseEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionName = admin.GUIGetEffectActionName();
        String atribute = admin.GUIGetEffectAttribute();
        int condition = admin.GUIGetEffectCondition();
        int regularity = admin.GUIGetEffectRegularity();
        int value = Integer.parseInt(admin.GUIGetEffectValue());
        
        admin.addEffect(EffectFact.create(actionName, atribute, condition, regularity, value));
    }
    
}
