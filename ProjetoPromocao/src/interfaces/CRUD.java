/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public interface CRUD {
    void incluir(Object objeto)throws Exception;
    void excluir(String id)throws Exception;
    void alterar(Object objeto)throws Exception;
    public Object consultar(String id)throws Exception;
    public ArrayList<Object> listar()throws Exception;
}
