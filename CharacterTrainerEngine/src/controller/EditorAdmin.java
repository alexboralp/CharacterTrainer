/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import fileioutils.FDBFact;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import model.utils.MainProperties;
import vista.Editor;
import vista.listeners.EditorOpenListener;
import vista.listeners.EditorWindowListener;
import vista.listeners.EditorsWindowListener;

/**
 *
 * @author alexander
 */
public class EditorAdmin implements IAdmin {
    Editor frmEditor;

    public EditorAdmin(Editor frmEditor) {
        this.frmEditor = frmEditor;
        _init_();
    }
    
    private void _init_() {
        frmEditor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frmEditor.addWindowListener(new EditorWindowListener(this));
        
        frmEditor.bntOpen.addActionListener(new EditorOpenListener(this));
        
        DefaultListModel<String> model = new DefaultListModel<>();
        frmEditor.lstEditors.setModel(model);
        
        //System.out.println(MainProperties.getInstance().getEditorsPath());
        String[] files = fileioutils.FUtils.getFolderContent(MainProperties.getInstance().getEditorsPath());
        for (String file : files) {
            if (file.contains(".java")) {
                model.addElement(file.substring(0, file.length() - 5));
            }
        }
    }
    
    public void openEditor(String editorName) {
        try {
            JFrame frame = (JFrame)Class.forName("vista.editors." + editorName).getDeclaredConstructor().newInstance();
            IAdmin admin = (IAdmin)Class.forName("controller." + editorName + "Admin").getDeclaredConstructor().newInstance();
            admin.setFrame(frame);
            frame.addWindowListener(new EditorsWindowListener(this, admin));
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setVisible(true);
            frmEditor.setVisible(false);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(FDBFact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeEditor(JFrame jFrame) {
        jFrame.dispose();
        frmEditor.setVisible(true);
    }
    
    public void closeProgram() {
        System.exit(0);
    }
    
    public String getSelectedEditor() {
        return frmEditor.lstEditors.getSelectedValue();
    }

    @Override
    public void setFrame(JFrame jFrame) {
        this.frmEditor = (Editor)jFrame;
    }

    @Override
    public JFrame getFrame() {
        return frmEditor;
    }
    
    
}
