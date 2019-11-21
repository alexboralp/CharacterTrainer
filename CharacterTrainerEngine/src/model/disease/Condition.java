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
public class Condition implements ICondition {
    
    private String attribute;
    private int condition;
    private int value;

    public Condition(String attribute, int condition, int value) {
        this.attribute = attribute;
        this.condition = condition;
        this.value = value;
    }

    @Override
    public String getAttribute() {
        return attribute;
    }

    @Override
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public int getCondition() {
        return condition;
    }

    @Override
    public void setCondition(int condition) {
        this.condition = condition;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Condition{" + "attribute=" + attribute + ", condition=" + condition + ", value=" + value + '}';
    }
    
    
}
