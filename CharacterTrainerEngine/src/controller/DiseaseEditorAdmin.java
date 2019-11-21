/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JFrame;
import vista.editors.DiseaseEditor;

/**
 *
 * @author alexander
 */
public class DiseaseEditorAdmin implements IAdmin {
    DiseaseEditor frmDiseaseEditor;

    public DiseaseEditorAdmin() { }

    public DiseaseEditorAdmin(DiseaseEditor frmDiseaseEditor) {
        this.frmDiseaseEditor = frmDiseaseEditor;
    }

    @Override
    public void setFrame(JFrame jFrame) {
        this.frmDiseaseEditor = (DiseaseEditor)jFrame;
    }

    @Override
    public JFrame getFrame() {
        return frmDiseaseEditor;
    }
    
    
}
