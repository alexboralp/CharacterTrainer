/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.disease.interfaces;

/**
 *
 * @author alexander
 */
public interface ICondition {
    public static int LESS = -2;
    public static int LESS_AND_EQUAL = -1;
    public static int EQUAL = 0;
    public static int GREATER_AND_EQUAL = 1;
    public static int GREATER = 2;
    
    public String getAttribute();
    public void setAttribute(String attribute);
    public int getCondition();
    public void setCondition(int condition);
    public int getValue();
    public void setValue(int value);
    
}
