/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

/**
 *
 * @author selecta
 */
public class Adapter implements Comparar{
    
    @Override
    public int eIgual(String primeira, String segunda){
        return primeira.compareToIgnoreCase(segunda);
    }
}
