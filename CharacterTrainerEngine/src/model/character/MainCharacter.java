/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.character;

import model.character.interfaces.IMainCharacter;
import cc.character.Character;

/**
 *
 * @author aborbon
 */
public class MainCharacter extends Character implements IMainCharacter {

    // private String path;
    // private String mood;
    private int fullnesLevel;
    private int liquidsLevel;
    private int sleepLevel;
    private int stoolLevel;
    private int urineLevel;
    private int force;
    private int happiness;
    private int fatness;
    private int trainingLevel;
    private int physicalHealth;
    private int mentalHealth;
    
    //TODO:
    /*Injuries, diseases*/

    public MainCharacter(String name) {
        super(name, 100, 0, 1, 0, 0, Integer.MAX_VALUE);
        fullnesLevel = 100;
        liquidsLevel = 100;
        sleepLevel = 0;
        stoolLevel = 0;
        urineLevel = 0;
        force = 100;
        happiness = 100;
        fatness = 0;
        trainingLevel = 0;
        physicalHealth = 100;
        mentalHealth = 100;
    }
    
    public int getEdad() {
        return level;
    }
    
    public void setEdad(int edad) {
        this.level = edad;
    }

    public int getFullnesLevel() {
        return fullnesLevel;
    }

    public void setFullnesLevel(int fullnesLevel) {
        this.fullnesLevel = fullnesLevel;
    }

    public int getLiquidsLevel() {
        return liquidsLevel;
    }

    public void setLiquidsLevel(int liquidsLevel) {
        this.liquidsLevel = liquidsLevel;
    }
    
    

    public int getSleepLevel() {
        return sleepLevel;
    }

    public void setSleepLevel(int sleepLevel) {
        this.sleepLevel = sleepLevel;
    }

    public int getStoolLevel() {
        return stoolLevel;
    }

    public void setStoolLevel(int stoolLevel) {
        this.stoolLevel = stoolLevel;
    }

    public int getUrineLevel() {
        return urineLevel;
    }

    public void setUrineLevel(int urineLevel) {
        this.urineLevel = urineLevel;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getFatness() {
        return fatness;
    }

    public void setFatness(int fatness) {
        this.fatness = fatness;
    }

    public int getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(int trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public int getPhysicalHealth() {
        return physicalHealth;
    }

    public void setPhysicalHealth(int physicalHealth) {
        this.physicalHealth = physicalHealth;
    }

    public int getMentalHealth() {
        return mentalHealth;
    }

    public void setMentalHealth(int mentalHealth) {
        this.mentalHealth = mentalHealth;
    }

    @Override
    public String toString() {
        return "MainCharacter{" + super.toString() + ", fullnesLevel=" + fullnesLevel + ", liquidsLevel=" + liquidsLevel + ", sleepLevel=" + sleepLevel + ", stoolLevel=" + stoolLevel + ", urineLevel=" + urineLevel + ", force=" + force + ", happiness=" + happiness + ", fatness=" + fatness + ", trainingLevel=" + trainingLevel + ", physicalHealth=" + physicalHealth + ", mentalHealth=" + mentalHealth + '}';
    }
    
}
