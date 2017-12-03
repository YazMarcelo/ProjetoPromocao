/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
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
    public static void msg01(Component parent) {
        JOptionPane.showMessageDialog(parent, "Registro Efetuado Com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static int msg02(Component parent) {
        return JOptionPane.showConfirmDialog(parent, "Confirmar Alterações?", "Mensagem do Sistema", 0, 3);
    }

    public static int msg03(Component parent) {
        return JOptionPane.showConfirmDialog(parent, "Confirmar Exclusão?", "Mensagem do Sistema", 0, 3);
    }

    public static void msg04(Component parent) {
        JOptionPane.showMessageDialog(parent, "Alteração Efetuada com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static void msg05(Component parent) {
        JOptionPane.showMessageDialog(parent, "Exclusão Efetuada com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static void msg06(Component parent) {
        JOptionPane.showMessageDialog(parent, "Dado Inválido!", "Mensagem do Sistema", 0);
    }

    public static void msg07(Component parent) {
        JOptionPane.showMessageDialog(parent, "Registro Informado está cancelado!", "Mensagem do Sistema", 1);
    }

    public static void msg08(Component parent) {
        JOptionPane.showMessageDialog(parent, "Não foi possível acessar a base de dados, tente novamente.", "Mensagem do Sistema", 0);
    }

    public static void msg09(Component parent) {
        JOptionPane.showMessageDialog(parent, "Registro já existente!", "Mensagem do Sistema", 0);
    }

    public static void msg10(Component parent) {
        JOptionPane.showMessageDialog(parent, "Informe os dados obrigatórios!", "Mensagem do Sistema", 0);
    }

    public static void msg11(Component parent) {
        JOptionPane.showMessageDialog(parent, "Lista de promoção enviada com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static void msg12(Component parent) {
        JOptionPane.showMessageDialog(parent, "Venda Faturada com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static void msg13(Component parent) {
        JOptionPane.showMessageDialog(parent, "Venda Cancelada com Sucesso!", "Mensagem do Sistema", 1);
    }
    
    public static int msg14(Component parent) {
        return JOptionPane.showConfirmDialog(parent, "Confirmar Cancelamento?", "Mensagem do Sistema", 0, 3);
    }
    
    public static int msg15(Component parent) {
        return JOptionPane.showConfirmDialog(parent, "Confirmar Venda?", "Mensagem do Sistema", 0, 3);
    }
    
    public static void msg16(){
        JOptionPane.showMessageDialog(null, "Selecione um Registro!", "Mensagem do Sistema", 1);
    }
}
