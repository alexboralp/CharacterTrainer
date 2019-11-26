/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.disease;

import java.util.LinkedList;
import model.disease.interfaces.ICondition;
import model.disease.interfaces.IDisease;
import model.disease.interfaces.IEffect;

/**
 *
 * @author aborbon
 */
public class Disease implements IDisease {

    private String name;
    private int duration;
    private final LinkedList<ICondition> conditions;
    private final LinkedList<String> cures;
    private final LinkedList<IEffect> effects;

    public Disease(String name, int duration) {
        this.name = name;
        this.duration = duration;
        conditions = new LinkedList();
        cures = new LinkedList();
        effects = new LinkedList();
    }

    public Disease(String name, int duration, LinkedList<ICondition> conditions, LinkedList<String> cures, LinkedList<IEffect> effects) {
        this.name = name;
        this.duration = duration;
        this.conditions = conditions;
        this.cures = cures;
        this.effects = effects;
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
    public String toString() {
        return "Disease{" + "name=" + name + ", conditions=" + conditions + ", cures=" + cures + '}';
    }

    @Override
    public LinkedList<String> getCures() {
        return cures;
    }

    @Override
    public void addCure(String cure) {
        cures.add(cure);
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void addCondition(ICondition condition) {
        conditions.add(condition);
    }

    @Override
    public LinkedList<ICondition> getConditions() {
        return conditions;
    }

    @Override
    public LinkedList<IEffect> getEffects() {
        return effects;
    }

    @Override
    public void addEffect(IEffect effect) {
        effects.add(effect);
    }
    
    
    
}
