package busca;

import models.Estudante;

public class BuscaLinear implements AlgoritmoBusca.BuscaAlgorithm {

    @Override
    public int buscar(int[] array, int alvo) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == alvo) return i;
        }
        return -1;
    }

    public int buscarRecursiva(int[] array, int alvo) {
        return buscarRecursiva(array, alvo, 0);
    }

    private int buscarRecursiva(int[] array, int alvo, int index) {
        if (index >= array.length) return -1;
        if (array[index] == alvo) return index;
        return buscarRecursiva(array, alvo, index + 1);
    }

    public int buscarDuasPontas(int[] array, int alvo) {
        int esquerda = 0;
        int direita = array.length - 1;

        while (esquerda <= direita) {
            if (array[esquerda] == alvo) return esquerda;
            if (array[direita] == alvo) return direita;
            esquerda++;
            direita--;
        }
        return -1;
    }

    @Override
    public int buscar(Estudante[] array, Estudante alvo) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(alvo) == 0) return i;
        }
        return -1;
    }

    public int buscarRecursiva(Estudante[] array, Estudante alvo) {
        return buscarRecursiva(array, alvo, 0);
    }

    private int buscarRecursiva(Estudante[] array, Estudante alvo, int index) {
        if (index >= array.length) return -1;
        if (array[index].compareTo(alvo) == 0) return index;
        return buscarRecursiva(array, alvo, index + 1);
    }
    
    public int buscarDuasPontas(Estudante[] array, Estudante alvo) {
        int esquerda = 0;
        int direita = array.length - 1;

        while (esquerda <= direita) {
            if (array[esquerda].compareTo(alvo) == 0) return esquerda;
            if (array[direita].compareTo(alvo) == 0) return direita;
            esquerda++;
            direita--;
        }
        return -1;
    }
}