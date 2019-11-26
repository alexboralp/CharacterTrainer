/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.DiseaseEditorAdmin;
import model.disease.DiseaseBuilder;
import model.disease.interfaces.ICondition;
import model.disease.interfaces.IEffect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 *
 * @author aborbon
 */
public class DiseaseBtnAdd implements ActionListener {
    
    DiseaseEditorAdmin admin;

    public DiseaseBtnAdd(DiseaseEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiseaseBuilder diseaseBuilder = new DiseaseBuilder();
        diseaseBuilder.setName(admin.GUIGetName());
        diseaseBuilder.setDuration(admin.GUIGetDuration());
        Iterator<ICondition> iterator = admin.GUIGetConditionsList().asIterator();
        while (iterator.hasNext()) {
            diseaseBuilder.addCondition(iterator.next());
        }
        Iterator<String> cures = admin.GUIGetCuresList().asIterator();
        while (cures.hasNext()) {
            diseaseBuilder.addCure(cures.next());
        }
        Iterator<IEffect> effects = admin.GUIGetEffectsList().asIterator();
        while (effects.hasNext()) {
            diseaseBuilder.addEffect(effects.next());
        }
        admin.addDisease(diseaseBuilder.build());
    }
    
}
