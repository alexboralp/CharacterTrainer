/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.routes;

import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author aborbon
 */
public class Route extends LinkedList<Point> implements IRoute {

    @Override
    public void addPoint(Point point) {
        this.add(point);
    }
    
}
