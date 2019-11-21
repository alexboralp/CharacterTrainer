/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JFrame;
import vista.editors.CharacterEditor;

/**
 *
 * @author alexander
 */
public class CharacterEditorAdmin implements IAdmin {
    CharacterEditor frmCharacterEditor;

    public CharacterEditorAdmin() { }

    public CharacterEditorAdmin(CharacterEditor frmCharacterEditor) {
        this.frmCharacterEditor = frmCharacterEditor;
    }

    @Override
    public void setFrame(JFrame jFrame) {
        this.frmCharacterEditor = (CharacterEditor)jFrame;
    }

    @Override
    public JFrame getFrame() {
        return frmCharacterEditor;
    }
    
    
}
