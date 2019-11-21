/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.EditorAdmin;
import controller.IAdmin;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author alexander
 */
public class EditorsWindowListener implements WindowListener {
    
    EditorAdmin editorAdmin;
    IAdmin admin;

    public EditorsWindowListener(EditorAdmin editorAdmin, IAdmin admin) {
        this.editorAdmin = editorAdmin;
        this.admin = admin;
    }

    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosing(WindowEvent e) {
        editorAdmin.closeEditor(admin.getFrame());
    }

    @Override
    public void windowClosed(WindowEvent e) { }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }

}