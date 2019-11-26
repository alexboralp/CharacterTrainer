/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author alexander
 */
public class ResourceImage {

    private static ResourceImage resourceImage;
    
    private ResourceImage() {
        
    }
    
    public static ResourceImage getInstace() {
        if (resourceImage == null) {
            resourceImage = new ResourceImage();
        }
        
        return resourceImage;
    }
    
    public ImageIcon read(String resourcePath) {
        try {
            InputStream in = getClass().getResourceAsStream(resourcePath);
            return new ImageIcon(in.readAllBytes());
        } catch (IOException ex) {
            Logger.getLogger(ResourceImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
