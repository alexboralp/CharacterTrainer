/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.character;

import model.character.interfaces.IMainCharacter;


/**
 *
 * @author alexander
 */
public class CharacterFact {
    public static IMainCharacter create(String name) {
        return new MainCharacter(name);
    }
}
