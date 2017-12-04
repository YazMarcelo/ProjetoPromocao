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
public class Comparando implements Comparar {

    @Override
    public int eIgual(String primeira, String segunda) {
        char letra = 'a';
        char letra2 = 'a';
        int c = 0;
        int tamanho = (segunda.toLowerCase()).length();
        if (tamanho > (primeira.toLowerCase()).length()) {
            tamanho = (primeira.toLowerCase()).length();
        }

        while (c < tamanho) {
            if (letra == letra2) {
                letra = primeira.toLowerCase().charAt(c);
                letra2 = (segunda.toLowerCase()).charAt(c);
            } else {
                break;
            }
            c++;
        }
        int result;
        if (letra < letra2) {
            result = -10;
        } else if (letra > letra2) {
            result = 10;
        } else {
            result = 0;
        }

        return result;
    }

}
