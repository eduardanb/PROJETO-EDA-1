package busca;

import models.Estudante;

public class AlgoritmoBusca {
    public interface BuscaAlgorithm {
        int buscar(int[] array, int alvo);
        int buscar(Estudante[] array, Estudante alvo);
    }
}