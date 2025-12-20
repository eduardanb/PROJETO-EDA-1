package analise;

import models.Estudante;
import java.util.Random;
import java.util.Arrays;

public class GeradorDados {
    private static final String[] NOMES = {
            "Ana", "Carlos", "Maria", "João", "Pedro",
            "Lucia", "Fernando", "Julia", "Roberto", "Camila",
            "Mariana", "Rafael", "Beatriz", "Lucas", "Gabriel",
            "Isabela", "Thiago", "Amanda", "Vinícius", "Letícia"
    };

    private static final String[] SOBRENOMES = {
            "Silva", "Santos", "Oliveira", "Souza", "Rodrigues",
            "Ferreira", "Alves", "Pereira", "Lima", "Costa",
            "Gomes", "Martins", "Rocha", "Ribeiro", "Carvalho"
    };

    private static Random rand = new Random();

    public static Estudante[] gerarEstudantes(int tamanho) {
        if (tamanho < 0)
            throw new IllegalArgumentException("tamanho não pode ser negativo");
        Estudante[] estudantes = new Estudante[tamanho];
        for (int i = 0; i < tamanho; i++) {
            int matricula = 10000 + i;
            String nome = NOMES[rand.nextInt(NOMES.length)] + " " +
                    SOBRENOMES[rand.nextInt(SOBRENOMES.length)];
            int nota = rand.nextInt(11); // 0-10
            estudantes[i] = new Estudante(matricula, nome, nota);
        }
        return estudantes;
    }

    public static int[] gerarArrayInteiros(int tamanho) {
        if (tamanho < 0)
            throw new IllegalArgumentException("tamanho não pode ser negativo");
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = rand.nextInt(10000);
        }
        return array;
    }

    public static int[] gerarArrayOrdenado(int tamanho) {
        if (tamanho < 0)
            throw new IllegalArgumentException("tamanho não pode ser negativo");
        int[] array = gerarArrayInteiros(tamanho);
        Arrays.sort(array);
        return array;
    }

    public static int[] gerarArrayInverso(int tamanho) {
        if (tamanho < 0)
            throw new IllegalArgumentException("tamanho não pode ser negativo");
        int[] array = gerarArrayInteiros(tamanho);
        Arrays.sort(array);
        // inverter
        for (int i = 0; i < array.length / 2; i++) {
            int j = array.length - 1 - i;
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        return array;
    }

    public static int[] gerarArrayParcialmenteOrdenado(int tamanho, double percentual) {
        if (tamanho < 0)
            throw new IllegalArgumentException("tamanho não pode ser negativo");
        if (percentual < 0.0 || percentual > 1.0)
            throw new IllegalArgumentException("percentual deve estar entre 0.0 e 1.0");
        int[] array = gerarArrayOrdenado(tamanho);
        // Embaralhar parcialmente
        int embaralhar = (int) (tamanho * percentual);
        for (int i = 0; i < embaralhar; i++) {
            int a = rand.nextInt(tamanho);
            int b = rand.nextInt(tamanho);
            int temp = array[a];
            array[a] = array[b];
            array[b] = temp;
        }
        return array;
    }

    public static Estudante[] gerarEstudantesOrdenados(int tamanho) {
        if (tamanho < 0)
            throw new IllegalArgumentException("tamanho não pode ser negativo");
        Estudante[] estudantes = gerarEstudantes(tamanho);
        java.util.Arrays.sort(estudantes);
        return estudantes;
    }
}