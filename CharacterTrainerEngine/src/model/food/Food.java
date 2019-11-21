/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.food;

import model.food.interfaces.IFood;

/**
 *
 * @author aborbon
 */
public class Food implements IFood {

    String name;
    String path;
    int fullnesLevel;
    int liquidsLevel;

    public Food() {
    }

    public Food(String name, String path, int fullnesLevel, int liquidsLevel) {
        this.name = name;
        this.path = path;
        this.fullnesLevel = fullnesLevel;
        this.liquidsLevel = liquidsLevel;
    }
    
    @Override
    public int getFullnesLevel() {
        return fullnesLevel;
    }

    @Override
    public void setFullnesLevel(int fullnesLevel) {
        this.fullnesLevel = fullnesLevel;
    }

    @Override
    public int getLiquidsLevel() {
        return liquidsLevel;
    }

    @Override
    public void setLiquidsLevel(int liquidsLevel) {
        this.liquidsLevel = liquidsLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Food{" + "name=" + name + ", path=" + path + ", fullnesLevel=" + fullnesLevel + ", liquidsLevel=" + liquidsLevel + '}';
    }
    
}
