/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.disease;

import cc.interfaces.patterns.IBuilder;
import java.util.LinkedList;
import model.disease.interfaces.ICondition;
import model.disease.interfaces.IDisease;
import model.disease.interfaces.IEffect;

/**
 *
 * @author aborbon
 */
public class DiseaseBuilder implements IBuilder<IDisease> {
    
    private String name;
    private int duration;
    private final LinkedList<ICondition> conditions;
    private final LinkedList<String> cures;
    private final LinkedList<IEffect> effects;

    public DiseaseBuilder() {
        this.conditions = new LinkedList();
        this.cures = new LinkedList();
        this.effects = new LinkedList();
    }

    public DiseaseBuilder(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.conditions = new LinkedList();
        this.cures = new LinkedList();
        this.effects = new LinkedList();
    }

    public DiseaseBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DiseaseBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }
    
    public DiseaseBuilder addCondition(ICondition condition) {
        conditions.add(condition);
        return this;
    }
    
    public DiseaseBuilder addCure(String cure) {
        cures.add(cure);
        return this;
    }
    
    public DiseaseBuilder addEffect(IEffect effect) {
        effects.add(effect);
        return this;
    }

    @Override
    public IDisease build() {
        return new Disease(name, duration, conditions, cures, effects);
    }
    
}
