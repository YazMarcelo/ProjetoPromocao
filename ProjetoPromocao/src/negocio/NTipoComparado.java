package negocio;

import entidade.Tipo;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public abstract class NTipoComparado extends NTipo {

    public abstract boolean compara(Tipo docente1, Tipo docente2);

    public ArrayList<Tipo> listarComparando() throws Exception {
        ArrayList<Tipo> array = listar();
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j < array.size(); j++) {
                if (!compara(array.get(i), array.get(j))) {
                    Tipo temp = array.get(j);
                    array.set(j, array.get(i));
                    array.set(i, temp);
                }
            }
        }
        return array;
    }
}
