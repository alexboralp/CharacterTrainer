/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import model.character.MainCharacter;
import model.io.ResourceImage;
import vista.MainFrame;

/**
 *
 * @author alexander
 */
public class CharacterTrainerAdmin {
    private final MainFrame frame;

    public CharacterTrainerAdmin(MainFrame frame) {
        this.frame = frame;
        
        ImageIcon icon = ResourceImage.getInstace().read("/vista/images/house.png");
        frame.lblHouse.setIcon(icon);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.frame.setMaximizedBounds(env.getMaximumWindowBounds());
        this.frame.setExtendedState(this.frame.getExtendedState() | this.frame.MAXIMIZED_BOTH);
        
        MainCharacter goku = new MainCharacter("Goku");
        this.frame.setTitle("Character Trainer: Goku");
        
        
    }
    
    
}
