/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.food;

import model.food.interfaces.IFood;

/**
 *
 * @author alexander
 */
public class FoodFact {
    public static IFood NULL_FOOD = FoodFact.create("", "", 0, 0);
    
    public static IFood create(String name, String path, int fullnesLevel, int liquidsLevel) {
        return new Food(name, path, fullnesLevel, liquidsLevel);
    }
}
