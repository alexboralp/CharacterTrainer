/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.disease;

import model.disease.interfaces.ICondition;

/**
 *
 * @author alexander
 */
public class ConditionFact {
    public static ICondition create(String attribute, int condition, int value) {
        return new Condition(attribute, condition, value);
    }
}
