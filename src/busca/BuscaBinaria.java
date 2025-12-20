package busca;

import models.Estudante;

public class BuscaBinaria implements AlgoritmoBusca.BuscaAlgorithm {

    @Override
    public int buscar(int[] array, int alvo) {
        if (array == null)
            throw new IllegalArgumentException("array não pode ser nulo");
        int esquerda = 0;
        int direita = array.length - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;

            if (array[meio] == alvo)
                return meio;
            if (array[meio] < alvo)
                esquerda = meio + 1;
            else
                direita = meio - 1;
        }
        return -1;
    }

    public int buscarRecursiva(int[] array, int alvo) {
        if (array == null)
            throw new IllegalArgumentException("array não pode ser nulo");
        return buscarRecursiva(array, alvo, 0, array.length - 1);
    }

    private int buscarRecursiva(int[] array, int alvo, int esquerda, int direita) {
        if (esquerda > direita)
            return -1;

        int meio = esquerda + (direita - esquerda) / 2;

        if (array[meio] == alvo)
            return meio;
        if (array[meio] < alvo)
            return buscarRecursiva(array, alvo, meio + 1, direita);
        else
            return buscarRecursiva(array, alvo, esquerda, meio - 1);
    }

    @Override
    public int buscar(Estudante[] array, Estudante alvo) {
        if (array == null)
            throw new IllegalArgumentException("array de estudantes não pode ser nulo");
        if (alvo == null)
            throw new IllegalArgumentException("alvo não pode ser nulo");
        int esquerda = 0;
        int direita = array.length - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            int comparacao = array[meio].compareTo(alvo);

            if (comparacao == 0)
                return meio;
            if (comparacao < 0)
                esquerda = meio + 1;
            else
                direita = meio - 1;
        }
        return -1;
    }

    public int buscarRecursiva(Estudante[] array, Estudante alvo) {
        if (array == null)
            throw new IllegalArgumentException("array de estudantes não pode ser nulo");
        if (alvo == null)
            throw new IllegalArgumentException("alvo não pode ser nulo");
        return buscarRecursiva(array, alvo, 0, array.length - 1);
    }

    private int buscarRecursiva(Estudante[] array, Estudante alvo, int esquerda, int direita) {
        if (esquerda > direita)
            return -1;

        int meio = esquerda + (direita - esquerda) / 2;
        int comparacao = array[meio].compareTo(alvo);

        if (comparacao == 0)
            return meio;
        if (comparacao < 0)
            return buscarRecursiva(array, alvo, meio + 1, direita);
        else
            return buscarRecursiva(array, alvo, esquerda, meio - 1);
    }

    public int buscarPrimeiraOcorrencia(int[] array, int alvo) {
        if (array == null)
            throw new IllegalArgumentException("array não pode ser nulo");
        int esquerda = 0;
        int direita = array.length - 1;
        int resultado = -1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;

            if (array[meio] == alvo) {
                resultado = meio;
                direita = meio - 1; // Continua procurando à esquerda
            } else if (array[meio] < alvo) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return resultado;
    }

    public int buscarUltimaOcorrencia(int[] array, int alvo) {
        if (array == null)
            throw new IllegalArgumentException("array não pode ser nulo");
        int esquerda = 0;
        int direita = array.length - 1;
        int resultado = -1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;

            if (array[meio] == alvo) {
                resultado = meio;
                esquerda = meio + 1; // Continua procurando à direita
            } else if (array[meio] < alvo) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return resultado;
    }
}