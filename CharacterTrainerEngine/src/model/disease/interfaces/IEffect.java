/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.disease.interfaces;

/**
 *
 * @author aborbon
 */
public interface IEffect {
    // Conditions
    public static int PLUS = 0;
    public static int MINUS = 1;
    public static int CHANGE = 2;
    
    // REGULARITY
    public static int ONCE = 0;
    public static int ONCE_A_DAY = 1;
    public static int EACH_TIME = 2;
    
    public String getActionName();
    public void setActionName(String actionName);
    public String getAttribute();
    public void setAttribute(String attribute);
    public int getCondition();
    public void setCondition(int condition);
    public int getRegularity();
    public void setRegularity(int regularity);
    public int getValue();
    public void setValue(int value);
}
