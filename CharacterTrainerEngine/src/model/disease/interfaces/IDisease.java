/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.disease.interfaces;

import cc.interfaces.common.INameable;
import java.util.LinkedList;

/**
 *
 * @author aborbon
 */
public interface IDisease extends INameable {
    
    public LinkedList<String> getCures();
    public void addCure(String cure);
    public int getDuration();
    public void setDuration(int duration);
    public LinkedList<ICondition> getConditions();
    public void addCondition(ICondition condition);
    public LinkedList<IEffect> getEffects();
    public void addEffect(IEffect effect);
}
