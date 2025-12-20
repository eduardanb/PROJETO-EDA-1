package ordenacao;

import models.Estudante;

public class QuickSort implements AlgoritmosOrdenacao.OrdenacaoAlgorithm {

    // (pivot no final)
    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 1)
            return;
        int maxDepth = (int) (2 * Math.floor(Math.log(array.length) / Math.log(2)));
        quickSortIntro(array, 0, array.length - 1, maxDepth);
    }

    private void quickSortIntro(int[] array, int low, int high, int depthLimit) {
        while (low < high) {
            int size = high - low + 1;
            if (size <= 16) { // cutoff para insertion sort
                insertionSortRange(array, low, high);
                break;
            }
            if (depthLimit == 0) { // fallback para evitar stackoverflow (heapsort/Arrays.sort)
                java.util.Arrays.sort(array, low, high + 1);
                break;
            }

            int pi = partition(array, low, high);

            // ordena a partição menor recursivamente e reutiliza loop para a maior
            // (eliminação de cauda)
            if (pi - 1 - low < high - (pi + 1)) {
                quickSortIntro(array, low, pi - 1, depthLimit - 1);
                low = pi + 1;
            } else {
                quickSortIntro(array, pi + 1, high, depthLimit - 1);
                high = pi - 1;
            }
        }
    }

    private void insertionSortRange(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= left && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
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
        sort(array);
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
        if (array[mid] < array[low])
            swap(array, low, mid);
        if (array[high] < array[low])
            swap(array, low, high);
        if (array[high] < array[mid])
            swap(array, mid, high);

        // Coloca mediana no penúltimo elemento
        // Coloca mediana como pivot na posição high
        swap(array, mid, high);
        // Partição clássica com pivot em 'high'
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