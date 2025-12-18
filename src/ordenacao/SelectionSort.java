package ordenacao;

import models.Estudante;

public class SelectionSort implements AlgoritmosOrdenacao.OrdenacaoAlgorithm {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                swap(array, i, minIdx);
            }
        }
    }

    public void sortEstavel(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            int key = array[minIdx];
            while (minIdx > i) {
                array[minIdx] = array[minIdx - 1];
                minIdx--;
            }
            array[i] = key;
        }
    }

    @Override
    public void sort(Estudante[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                swap(array, i, minIdx);
            }
        }
    }

    public void sortEstavel(Estudante[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            Estudante key = array[minIdx];
            while (minIdx > i) {
                array[minIdx] = array[minIdx - 1];
                minIdx--;
            }
            array[i] = key;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void swap(Estudante[] array, int i, int j) {
        Estudante temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}