/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

/**
 *
 * @author HELM
 */
public class Mensagem {
    
    /*
    icone de mensagens
    0 = vermelho
    1 = azul de informação
    2 = Amarelo de Atenção
    3 = cinza de questão
    */
    
    public void msg01(){
        JOptionPane.showMessageDialog(null, "Cadastro Efetuado com Sucesso!", "Mensagem do Sistema", 1);
    }
    public int msg02(){
        return JOptionPane.showConfirmDialog(null, "Confirmar Alteração?", "Mensagem do Sistema", 0, 3);
    }
    public int msg03(){
        return JOptionPane.showConfirmDialog(null, "Confirmar Exclusão?", "Mensagem do Sistema", 0, 3);
    }
    public void msg04(){
        JOptionPane.showMessageDialog(null, "Alteração Efetuada com Sucesso!", "Mensagem do Sistema", 1);
    }
    public void msg05(){
        JOptionPane.showMessageDialog(null, "Exclusão Efetuada com Sucesso!", "Mensagem do Sistema", 1);
    }
    public void msg06(){
        JOptionPane.showMessageDialog(null, "Dado Inválido!", "Mensagem do Sistema", 0);
    }
    public void msg07(){
        JOptionPane.showMessageDialog(null, "Perfil Inválido! Verifique os dados digitados.", "Mensagem do Sistema", 0);
    }
    public void msg08(){
        JOptionPane.showMessageDialog(null, "Registro buscado não está cadastrado no sistema ou os dados estão incorretos! Por favor, verifique!", "Mensagem do Sistema", 0);
    }
    public void msg09(){
        JOptionPane.showMessageDialog(null, "Registro já existente!", "Mensagem do Sistema", 0);
    }
    public void msg10(){
        JOptionPane.showMessageDialog(null, "CPF Inválido!", "Mensagem do Sistema", 0);
    }
    public void msg11(){
        JOptionPane.showMessageDialog(null, "Informe os dados Obrigatórios!", "Mensagem do Sistema", 1);
    }
    public void msg12(){
        JOptionPane.showMessageDialog(null, "Selecione um Registro!", "Mensagem do Sistema", 1);
    }
}
