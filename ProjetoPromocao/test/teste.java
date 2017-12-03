
public class teste {

    public static void main(String[] args) {
        System.out.println("qtd brinde: " + getQtdBrinde(15, 3, 1));
    }

    static int getQtdBrinde(int qtdTotal, int qtdPaga, int qtdLeva) {
        int i = 0;
        int qtdBrinde = 0;
        while (i < qtdTotal) {
            if (i > qtdBrinde && (i - qtdBrinde) % qtdPaga == 0) {
                qtdBrinde += qtdLeva;
                i += qtdBrinde;
            }
            i++;
        }
        return qtdBrinde;
    }
}
