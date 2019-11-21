/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.food.interfaces;

import cc.interfaces.common.INameable;
import cc.interfaces.common.IPathable;

/**
 *
 * @author aborbon
 */
public interface IFood extends INameable, IPathable {
    public int getFullnesLevel();
    public void setFullnesLevel(int fullnesLevel);
    public int getLiquidsLevel();
    public void setLiquidsLevel(int liquidsLevel);
}
