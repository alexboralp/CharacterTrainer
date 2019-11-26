/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.disease;

import model.disease.interfaces.IEffect;

/**
 *
 * @author aborbon
 */
public class EffectFact {
    public static IEffect NULL_EFFECT = EffectFact.create("", "", 0, 0, 0);
    
    public static IEffect create(String actionName, String attribute, int condition, int regularity, int value) {
        return new Effect(actionName, attribute, condition, regularity, value);
    }
}
