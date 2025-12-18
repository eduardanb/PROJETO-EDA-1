package ordenacao;

import models.Estudante;
import java.util.LinkedList;

public class CountingSort implements AlgoritmosOrdenacao.OrdenacaoAlgorithm {

    @Override
    public void sort(int[] array) {
        if (array.length == 0) return;

        int max = array[0];
        int min = array[0];
        for (int num : array) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int range = max - min + 1;
        if (range > 1000000) {
            System.out.println("Range muito grande para CountingSort: " + range);
            return;
        }

        int[] count = new int[range];
        int[] output = new int[array.length];

        for (int num : array) {
            count[num - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }

        System.arraycopy(output, 0, array, 0, array.length);
    }

    @Override
    public void sort(Estudante[] array) {
        final int RANGE = 11;

        @SuppressWarnings("unchecked")
        LinkedList<Estudante>[] buckets = new LinkedList[RANGE];


        for (int i = 0; i < RANGE; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (Estudante e : array) {
            int nota = e.getNota();
            if (nota >= 0 && nota < RANGE) {
                buckets[nota].add(e);
            }
        }

        int index = 0;
        for (int i = RANGE - 1; i >= 0; i--) {
            for (Estudante e : buckets[i]) {
                array[index++] = e;
            }
        }
    }

    public void sort(int[] array, int min, int max) {
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[array.length];

        for (int num : array) {
            count[num - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }

        System.arraycopy(output, 0, array, 0, array.length);
    }
}