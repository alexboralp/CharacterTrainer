/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.listeners;

import controller.EditorAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alexander
 */
public class EditorOpenListener implements ActionListener {
    
    EditorAdmin admin;

    public EditorOpenListener(EditorAdmin admin) {
        this.admin = admin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String editor = admin.getSelectedEditor();
        if (editor != null && !"".equals(editor))
        admin.openEditor(editor);
    }
    
}
