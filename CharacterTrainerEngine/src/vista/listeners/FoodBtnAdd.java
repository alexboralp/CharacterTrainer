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
public class FoodBtnAdd implements ActionListener {
    
    FoodEditorAdmin admin;

    public FoodBtnAdd(FoodEditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FoodEditor frame = (FoodEditor)admin.getFrame();
        String name = frame.txtName.getText();
        String imagePath = frame.txtImage.getText();
        int fullnesLevel = Integer.parseInt(frame.txtFullnesLevel.getText());
        int liquidsLevel = Integer.parseInt(frame.txtLiquidsLevel.getText());
        this.admin.add(FoodFact.create(name, imagePath, fullnesLevel, liquidsLevel));
    }
    
}
