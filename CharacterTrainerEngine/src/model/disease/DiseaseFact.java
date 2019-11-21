/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.disease;

import model.disease.interfaces.IDisease;

/**
 *
 * @author alexander
 */
public class DiseaseFact {
    public static IDisease create(String name, int duration) {
        return new Disease(name, duration);
    }
}
