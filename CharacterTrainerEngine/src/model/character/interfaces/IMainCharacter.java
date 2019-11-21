/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.character.interfaces;

import cc.character.interfaces.ICharacter;

/**
 *
 * @author alexander
 */
public interface IMainCharacter extends ICharacter {
    public static int NORMAL = 0;
    public static int HAPPY = 1;
    public static int BATHROOM = 2;
    public static int EXERCISING = 3;
    public static int MEDITATING = 4;
    public static int EATING = 5;
    public static int ATTACKING = 6;
    public static int ILL = 7;
}
