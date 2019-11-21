/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

import java.util.LinkedList;

/**
 *
 * @author aborbon
 * @param <T>
 */
public interface IList<T> {
    public T get(String key);
    public void add(String key, T object);
    public LinkedList<T> getAll();
    public void setAll(LinkedList<T> linkedList);
    //public Collection<T> getValues();
    //public boolean hasKey(String key);
    public int size();
}
