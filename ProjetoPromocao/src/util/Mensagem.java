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
    public static void msg01() {
        JOptionPane.showMessageDialog(null, "Registro Efetuado Com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static int msg02() {
        return JOptionPane.showConfirmDialog(null, "Confirmar Alterações?", "Mensagem do Sistema", 0, 3);
    }

    public static int msg03() {
        return JOptionPane.showConfirmDialog(null, "Confirmar Exclusão?", "Mensagem do Sistema", 0, 3);
    }

    public static void msg04() {
        JOptionPane.showMessageDialog(null, "Alteração Efetuada com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static void msg05() {
        JOptionPane.showMessageDialog(null, "Exclusão Efetuada com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static void msg06() {
        JOptionPane.showMessageDialog(null, "Dado Inválido!", "Mensagem do Sistema", 0);
    }

    public static void msg07() {
        JOptionPane.showMessageDialog(null, "Registro Informado está cancelado!", "Mensagem do Sistema", 1);
    }

    public static void msg08() {
        JOptionPane.showMessageDialog(null, "Não foi possível acessar a base de dados, tente novamente.", "Mensagem do Sistema", 0);
    }

    public static void msg09() {
        JOptionPane.showMessageDialog(null, "Registro já existente!", "Mensagem do Sistema", 0);
    }

    public static void msg10() {
        JOptionPane.showMessageDialog(null, "Informe os dados obrigatórios!", "Mensagem do Sistema", 0);
    }

    public static void msg11() {
        JOptionPane.showMessageDialog(null, "Lista de promoção enviada com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static void msg12() {
        JOptionPane.showMessageDialog(null, "Pedido Faturado com Sucesso!", "Mensagem do Sistema", 1);
    }

    public static void msg13() {
        JOptionPane.showMessageDialog(null, "Pedido Cancelado com Sucesso!", "Mensagem do Sistema", 1);
    }
}
