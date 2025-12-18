package ordenacao;

import models.Estudante;

public class InsertionSort implements AlgoritmosOrdenacao.OrdenacaoAlgorithm {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    @Override
    public void sort(Estudante[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            Estudante key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public void sortBinario(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int pos = binarySearch(array, key, 0, i - 1);


            for (int j = i; j > pos; j--) {
                array[j] = array[j - 1];
            }
            array[pos] = key;
        }
    }

    private int binarySearch(int[] array, int key, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == key) {
                return mid + 1;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}