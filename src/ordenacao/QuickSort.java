package ordenacao;

import models.Estudante;

public class QuickSort implements AlgoritmosOrdenacao.OrdenacaoAlgorithm {

    //(pivot no final)
    @Override
    public void sort(int[] array) {
        quickSortSlide(array, 0, array.length - 1);
    }

    private void quickSortSlide(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSortSlide(array, low, pi - 1);
            quickSortSlide(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    public void sortComShuffle(int[] array) {
        AlgoritmosOrdenacao.shuffle(array);
        quickSortSlide(array, 0, array.length - 1);
    }

    // (Dual-Pivot QuickSort)
    public void sortJavaVersion(int[] array) {
        java.util.Arrays.sort(array);
    }

    // mediana de três (melhor escolha do pivot)
    public void sortMedianaDeTres(int[] array) {
        quickSortMediana(array, 0, array.length - 1);
    }

    private void quickSortMediana(int[] array, int low, int high) {
        if (low < high) {
            int pi = partitionMediana(array, low, high);
            quickSortMediana(array, low, pi - 1);
            quickSortMediana(array, pi + 1, high);
        }
    }

    private int partitionMediana(int[] array, int low, int high) {
        // Mediana de três
        int mid = low + (high - low) / 2;
        if (array[mid] < array[low]) swap(array, low, mid);
        if (array[high] < array[low]) swap(array, low, high);
        if (array[high] < array[mid]) swap(array, mid, high);

        // Coloca mediana no penúltimo elemento
        swap(array, mid, high - 1);
        return partition(array, low, high);
    }

    @Override
    public void sort(Estudante[] array) {
        quickSortEstudante(array, 0, array.length - 1);
    }

    private void quickSortEstudante(Estudante[] array, int low, int high) {
        if (low < high) {
            int pi = partitionEstudante(array, low, high);
            quickSortEstudante(array, low, pi - 1);
            quickSortEstudante(array, pi + 1, high);
        }
    }

    private int partitionEstudante(Estudante[] array, int low, int high) {
        Estudante pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    public void sortJavaVersion(Estudante[] array) {
        java.util.Arrays.sort(array);
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