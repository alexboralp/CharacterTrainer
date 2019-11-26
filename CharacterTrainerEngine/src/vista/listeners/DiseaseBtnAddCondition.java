/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import model.disease.ConditionFact;
import controller.DiseaseEditorAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author aborbon
 */
public class DiseaseBtnAddCondition implements ActionListener {
    
    DiseaseEditorAdmin admin;

    public DiseaseBtnAddCondition(DiseaseEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String attribute = admin.GUIGetConditionAttribute();
        int condition = admin.GUIGetCondition();
        int value = Integer.parseInt(admin.GUIGetConditionValue());

        if (!"".equals(attribute) && !"".equals(condition)) {
            admin.addCondition(ConditionFact.create(attribute, condition, value));
        }
    }
}
