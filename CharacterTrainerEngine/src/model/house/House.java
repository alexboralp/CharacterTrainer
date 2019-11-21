/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.house;

import java.awt.Point;
import java.util.HashMap;

/**
 *
 * @author aborbon
 */
public class House extends HashMap<String, Point> implements IHouse {

    @Override
    public void addRoom(String name, Point location) {
        this.put(name, location);
    }
    
}
