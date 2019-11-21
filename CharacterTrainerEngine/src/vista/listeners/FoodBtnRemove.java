/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.FoodEditorAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.food.FoodFact;
import vista.editors.FoodEditor;

/**
 *
 * @author aborbon
 */
public class FoodBtnRemove implements ActionListener {
    
    FoodEditorAdmin admin;

    public FoodBtnRemove(FoodEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        admin.remove();
    }
    
}
