package analise;

import ordenacao.*;
import busca.*;
import models.Estudante;
import java.util.Arrays;
import java.util.Random;

public class AnalisadorPerformance {

    public static void analisarOrdenacaoInteiros() {
        System.out.println("\n=== ANÁLISE DE PERFORMANCE - ORDENAÇÃO (INTEIROS) ===");

        int[] tamanhos = { 20000, 100000, 500000 };
        String[] cenarios = { "Aleatório", "Ordenado", "Inverso", "Parcial (50%)" };

        for (int tamanho : tamanhos) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("TAMANHO DO VETOR: " + tamanho);
            System.out.println("=".repeat(50));

            for (String cenario : cenarios) {
                System.out.println("\n--- Cenário: " + cenario + " ---");

                int[] dadosOriginais = switch (cenario) {
                    case "Aleatório" -> GeradorDados.gerarArrayInteiros(tamanho);
                    case "Ordenado" -> GeradorDados.gerarArrayOrdenado(tamanho);
                    case "Inverso" -> GeradorDados.gerarArrayInverso(tamanho);
                    case "Parcial (50%)" -> GeradorDados.gerarArrayParcialmenteOrdenado(tamanho, 0.5);
                    default -> new int[0];
                };
                try {
                    testarAlgoritmoInt("BubbleSort (Slide)", dadosOriginais.clone(), "slide");
                    testarAlgoritmoInt("BubbleSort (Otimizado)", dadosOriginais.clone(), "otimizado");
                    testarAlgoritmoInt("SelectionSort (Slide)", dadosOriginais.clone(), "slide");
                    testarAlgoritmoInt("SelectionSort (Estável)", dadosOriginais.clone(), "estavel");
                    testarAlgoritmoInt("InsertionSort", dadosOriginais.clone(), "normal");
                    testarAlgoritmoInt("MergeSort (Clássico)", dadosOriginais.clone(), "classico");
                    testarAlgoritmoInt("QuickSort (Slide)", dadosOriginais.clone(), "slide");
                    testarAlgoritmoInt("QuickSort (+Shuffle)", dadosOriginais.clone(), "shuffle");
                    testarAlgoritmoInt("QuickSort (Mediana3)", dadosOriginais.clone(), "mediana");
                    testarAlgoritmoInt("CountingSort", dadosOriginais.clone(), "normal");

                    testarAlgoritmoJavaInt("Arrays.sort()", dadosOriginais.clone());
                } catch (Exception e) {
                    System.err.println("Erro ao executar testes de ordenação (inteiros): " + e.getMessage());
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    public static void analisarOrdenacaoEstudantes() {
        System.out.println("\n=== ANÁLISE DE PERFORMANCE - ORDENAÇÃO (ESTUDANTES) ===");

        int[] tamanhos = { 20000, 100000, 500000 };

        for (int tamanho : tamanhos) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("TAMANHO DO VETOR: " + tamanho + " estudantes");
            System.out.println("=".repeat(50));

            Estudante[] estudantes = GeradorDados.gerarEstudantes(tamanho);

            System.out.println("\n--- Vetor Aleatório ---");
            try {
                testarAlgoritmoEstudante("BubbleSort", estudantes.clone(), "normal");
                testarAlgoritmoEstudante("SelectionSort", estudantes.clone(), "normal");
                testarAlgoritmoEstudante("InsertionSort", estudantes.clone(), "normal");
                testarAlgoritmoEstudante("MergeSort", estudantes.clone(), "classico");
                testarAlgoritmoEstudante("QuickSort", estudantes.clone(), "normal");
                testarAlgoritmoEstudante("CountingSort", estudantes.clone(), "normal");

                Arrays.sort(estudantes);
                System.out.println("\n--- Vetor Ordenado ---");
                testarAlgoritmoEstudante("BubbleSort (Otimizado)", estudantes.clone(), "otimizado");
                testarAlgoritmoEstudante("InsertionSort", estudantes.clone(), "normal");
                testarAlgoritmoEstudante("QuickSort", estudantes.clone(), "normal");

                testarAlgoritmoJavaEstudante("Arrays.sort()", estudantes.clone());
            } catch (Exception e) {
                System.err.println("Erro ao executar testes de ordenação (estudantes): " + e.getMessage());
                e.printStackTrace();
                continue;
            }
        }
    }

    public static void analisarBusca() {
        System.out.println("\n=== ANÁLISE DE PERFORMANCE - BUSCA ===");

        int[] tamanhos = { 20000, 100000, 500000, 1000000 };

        for (int tamanho : tamanhos) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("TAMANHO DO VETOR: " + tamanho);
            System.out.println("=".repeat(50));

            int[] array = GeradorDados.gerarArrayOrdenado(tamanho);
            int alvo = array[rand.nextInt(array.length)];
            int alvoInexistente = -1;

            System.out.println("\n--- Busca (elemento EXISTENTE) ---");
            try {
                testarBuscaInt("Busca Linear Iterativa", array.clone(), alvo, "iterativa");
                testarBuscaInt("Busca Linear Recursiva", array.clone(), alvo, "recursiva");
                testarBuscaInt("Busca Linear Duas Pontas", array.clone(), alvo, "duaspontas");
                testarBuscaInt("Busca Binária Iterativa", array.clone(), alvo, "iterativa");
                testarBuscaInt("Busca Binária Recursiva", array.clone(), alvo, "recursiva");

                System.out.println("\n--- Busca (elemento INEXISTENTE) ---");
                testarBuscaInt("Busca Linear Iterativa", array.clone(), alvoInexistente, "iterativa");
                testarBuscaInt("Busca Binária Iterativa", array.clone(), alvoInexistente, "iterativa");
            } catch (Exception e) {
                System.err.println("Erro ao executar testes de busca (inteiros): " + e.getMessage());
                e.printStackTrace();
                continue;
            }
        }

        System.out.println("\n\n=== BUSCA EM VETOR DE ESTUDANTES ===");
        int tamanho = 100000;
        Estudante[] estudantes = GeradorDados.gerarEstudantesOrdenados(tamanho);
        Estudante alvoEstudante = estudantes[rand.nextInt(estudantes.length)];

        System.out.println("\n--- Estudantes (elemento EXISTENTE) ---");
        try {
            testarBuscaEstudante("Busca Linear Iterativa", estudantes.clone(), alvoEstudante, "iterativa");
            testarBuscaEstudante("Busca Binária Iterativa", estudantes.clone(), alvoEstudante, "iterativa");
            testarBuscaEstudante("Busca Binária Recursiva", estudantes.clone(), alvoEstudante, "recursiva");
        } catch (Exception e) {
            System.err.println("Erro ao executar testes de busca (estudantes): " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testarAlgoritmoInt(String nomeAlgoritmo, int[] dados, String versao) {

        for (int i = 0; i < 5; i++) {
            int[] copia = dados.clone();
            executarAlgoritmoInt(copia, nomeAlgoritmo, versao);
        }

        long[] tempos = new long[20];
        for (int i = 0; i < 20; i++) {
            int[] copia = dados.clone();
            long inicio = System.nanoTime();

            executarAlgoritmoInt(copia, nomeAlgoritmo, versao);

            long fim = System.nanoTime();
            tempos[i] = fim - inicio;

            if (i == 0 && !AlgoritmosOrdenacao.isSorted(copia)) {
                System.out.println("  ERRO: Vetor não ordenado corretamente!");
            }
        }

        long soma = 0;
        for (int i = 5; i < 20; i++) {
            soma += tempos[i];
        }
        double mediaMs = soma / 15.0 / 1_000_000.0;

        System.out.printf("  %-25s: %8.2f ms%n", nomeAlgoritmo, mediaMs);
    }

    private static void executarAlgoritmoInt(int[] array, String algoritmo, String versao) {
        switch (algoritmo) {
            case "BubbleSort (Slide)" -> new BubbleSort().sort(array);
            case "BubbleSort (Otimizado)" -> new BubbleSort().sortOtimizado(array);
            case "SelectionSort (Slide)" -> new SelectionSort().sort(array);
            case "SelectionSort (Estável)" -> new SelectionSort().sortEstavel(array);
            case "InsertionSort" -> new InsertionSort().sort(array);
            case "MergeSort (Clássico)" -> new MergeSort().sort(array);
            case "QuickSort (Slide)" -> new QuickSort().sort(array);
            case "QuickSort (+Shuffle)" -> new QuickSort().sortComShuffle(array);
            case "QuickSort (Mediana3)" -> new QuickSort().sortMedianaDeTres(array);
            case "CountingSort" -> new CountingSort().sort(array);
        }
    }

    private static void testarAlgoritmoJavaInt(String nomeAlgoritmo, int[] dados) {
        long inicio = System.nanoTime();
        Arrays.sort(dados);
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;
        System.out.printf("  %-25s: %8.2f ms%n", nomeAlgoritmo, tempoMs);
    }

    private static void testarAlgoritmoEstudante(String nomeAlgoritmo, Estudante[] dados, String versao) {

        for (int i = 0; i < 5; i++) {
            Estudante[] copia = dados.clone();
            executarAlgoritmoEstudante(copia, nomeAlgoritmo, versao);
        }

        long[] tempos = new long[10];
        for (int i = 0; i < 10; i++) {
            Estudante[] copia = dados.clone();
            long inicio = System.nanoTime();

            executarAlgoritmoEstudante(copia, nomeAlgoritmo, versao);

            long fim = System.nanoTime();
            tempos[i] = fim - inicio;
        }

        long soma = 0;
        for (int i = 5; i < 10; i++) {
            soma += tempos[i];
        }
        double mediaMs = soma / 5.0 / 1_000_000.0;

        System.out.printf("  %-25s: %8.2f ms%n", nomeAlgoritmo, mediaMs);
    }

    private static void executarAlgoritmoEstudante(Estudante[] array, String algoritmo, String versao) {
        switch (algoritmo) {
            case "BubbleSort" -> new BubbleSort().sort(array);
            case "BubbleSort (Otimizado)" -> new BubbleSort().sortOtimizado(array);
            case "SelectionSort" -> new SelectionSort().sort(array);
            case "InsertionSort" -> new InsertionSort().sort(array);
            case "MergeSort" -> new MergeSort().sortClassico(array);
            case "QuickSort" -> new QuickSort().sort(array);
            case "CountingSort" -> new CountingSort().sort(array);
        }
    }

    private static void testarAlgoritmoJavaEstudante(String nomeAlgoritmo, Estudante[] dados) {
        long inicio = System.nanoTime();
        Arrays.sort(dados);
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;
        System.out.printf("  %-25s: %8.2f ms%n", nomeAlgoritmo, tempoMs);
    }

    private static void testarBuscaInt(String nomeAlgoritmo, int[] array, int alvo, String tipo) {
        BuscaLinear buscaLinear = new BuscaLinear();
        BuscaBinaria buscaBinaria = new BuscaBinaria();

        long inicio = System.nanoTime();
        int resultado = -1;

        for (int i = 0; i < 1000; i++) {
            switch (nomeAlgoritmo) {
                case "Busca Linear Iterativa" -> resultado = buscaLinear.buscar(array, alvo);
                case "Busca Linear Recursiva" -> resultado = buscaLinear.buscarRecursiva(array, alvo);
                case "Busca Linear Duas Pontas" -> resultado = buscaLinear.buscarDuasPontas(array, alvo);
                case "Busca Binária Iterativa" -> resultado = buscaBinaria.buscar(array, alvo);
                case "Busca Binária Recursiva" -> resultado = buscaBinaria.buscarRecursiva(array, alvo);
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0 / 1000.0;

        System.out.printf("  %-25s: %8.4f ms (resultado: %d)%n", nomeAlgoritmo, tempoMs, resultado);
    }

    private static void testarBuscaEstudante(String nomeAlgoritmo, Estudante[] array, Estudante alvo, String tipo) {
        BuscaLinear buscaLinear = new BuscaLinear();
        BuscaBinaria buscaBinaria = new BuscaBinaria();

        long inicio = System.nanoTime();
        int resultado = -1;

        for (int i = 0; i < 1000; i++) {
            switch (nomeAlgoritmo) {
                case "Busca Linear Iterativa" -> resultado = buscaLinear.buscar(array, alvo);
                case "Busca Binária Iterativa" -> resultado = buscaBinaria.buscar(array, alvo);
                case "Busca Binária Recursiva" -> resultado = buscaBinaria.buscarRecursiva(array, alvo);
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0 / 1000.0;

        System.out.printf("  %-25s: %8.4f ms (resultado: %d)%n", nomeAlgoritmo, tempoMs, resultado);
    }

    private static Random rand = new Random();
}