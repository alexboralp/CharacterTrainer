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
public class Effect implements IEffect {

    private String actionName;
    private String attribute;
    private int condition;
    private int regularity;
    private int value;

    public Effect(String actionName, String attribute, int condition, int regularity, int value) {
        this.actionName = actionName;
        this.attribute = attribute;
        this.condition = condition;
        this.regularity = regularity;
        this.value = value;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public void setActionName(String actionName) {
        this.actionName = actionName;
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
    public int getRegularity() {
        return regularity;
    }

    @Override
    public void setRegularity(int regularity) {
        this.regularity = regularity;
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
        return "Effect{" + "actionName=" + actionName + ", attribute=" + attribute + ", condition=" + condition + ", regularity=" + regularity + ", value=" + value + '}';
    }
    
}
