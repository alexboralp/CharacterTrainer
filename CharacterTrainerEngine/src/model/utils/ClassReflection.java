/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

import java.lang.reflect.Field;

/**
 *
 * @author alexander
 */
public class ClassReflection {
    
    public static Field[] getClassAttributes(Class myclass) throws IllegalArgumentException, IllegalAccessException {
        // Get the attributes of the class
        return myclass.getDeclaredFields();
    }
    
    public static String[] cleanAttributes(Field[] fs) {
        String attributes[] = new String[fs.length];
        for(int i = 0; i < fs.length; i++) {
            attributes[i] = fs[i].getName();
        }
        return attributes;
    }
}
