package ordenacao;

import models.Estudante;

public class AlgoritmosOrdenacao {
    public interface OrdenacaoAlgorithm {
        void sort(int[] array);
        void sort(Estudante[] array);
    }

    public static void shuffle(int[] array) {
        java.util.Random rand = new java.util.Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) return false;
        }
        return true;
    }

    public static boolean isSorted(Estudante[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) return false;
        }
        return true;
    }
}