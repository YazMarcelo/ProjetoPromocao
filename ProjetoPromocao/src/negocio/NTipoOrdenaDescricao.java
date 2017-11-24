/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import entidade.Tipo;

/**
 *
 * @author gguui
 */
public class NTipoOrdenaDescricao extends NTipoComparado {

    @Override
    public boolean compara(Tipo tipo1, Tipo tipo2) {
        return tipo1.getDescricao().compareToIgnoreCase(tipo2.getDescricao()) < 0;
    }
    
}
