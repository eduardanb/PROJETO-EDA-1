package analise;

import ordenacao.*;
import busca.*;
import models.Estudante;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("     SISTEMA DE ANÁLISE DE ALGORITMOS - EDA");
            System.out.println("=".repeat(50));
            System.out.println("1. Testar Algoritmos de Ordenação");
            System.out.println("2. Testar Algoritmos de Busca");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> menuOrdenacao();
                case 2 -> menuBusca();
                case 0 -> System.out.println("\nEncerrando programa...");
                default -> System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void menuOrdenacao() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         TESTE DE ALGORITMOS DE ORDENAÇÃO");
        System.out.println("=".repeat(50));
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Quick Sort");
        System.out.println("6. Counting Sort");
        System.out.println("7. Testar Todos os Algoritmos");
        System.out.println("8. Voltar ao Menu Principal");
        System.out.print("\nEscolha: ");

        int escolha = scanner.nextInt();

        if (escolha == 8)
            return;

        System.out.print("\nTamanho do array: ");
        int tamanho = scanner.nextInt();

        System.out.println("\nTipo de array:");
        System.out.println("1. Aleatório");
        System.out.println("2. Ordenado");
        System.out.println("3. Inverso");
        System.out.print("Escolha: ");
        int tipo = scanner.nextInt();

        if (escolha >= 1 && escolha <= 6) {
            testarAlgoritmoIndividual(escolha, tamanho, tipo);
        } else if (escolha == 7) {
            testarTodosOrdenacao(tamanho, tipo);
        }
    }

    private static void menuBusca() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         TESTE DE ALGORITMOS DE BUSCA");
        System.out.println("=".repeat(50));
        System.out.println("1. Busca Linear Iterativa");
        System.out.println("2. Busca Linear Recursiva");
        System.out.println("3. Busca Linear Duas Pontas");
        System.out.println("4. Busca Binária Iterativa");
        System.out.println("5. Busca Binária Recursiva");
        System.out.println("6. Testar Todos os Algoritmos de Busca");
        System.out.println("7. Voltar ao Menu Principal");
        System.out.print("\nEscolha: ");

        int escolha = scanner.nextInt();
        if (escolha == 7)
            return;

        System.out.print("\nTamanho do array: ");
        int tamanho = scanner.nextInt();

        int[] array = GeradorDados.gerarArrayOrdenado(tamanho);
        if (array.length == 0) {
            System.err.println("Array gerado vazio. Verifique o tamanho informado.");
            return;
        }

        System.out.println("\nEscolha modo de seleção do alvo:");

        System.out.println("1. Digitar valor para buscar");
        System.out.println("2. Usar valor existente aleatório do array (Recomendado para testes)");
        System.out.print("Escolha: ");
        int modoAlvo = scanner.nextInt();

        int alvo;
        if (modoAlvo == 2) {
            java.util.Random rnd = new java.util.Random();
            alvo = array[rnd.nextInt(array.length)];
            System.out.println("Alvo selecionado aleatoriamente (existe no array): " + alvo);
        } else {
            System.out.print("Valor para buscar: ");
            alvo = scanner.nextInt();
        }

        if (escolha >= 1 && escolha <= 5) {
            testarBuscaIndividual(escolha, array, alvo);
        } else if (escolha == 6) {
            testarTodasBuscas(array, alvo);
        }
    }

    private static void testarAlgoritmoIndividual(int algoritmo, int tamanho, int tipo) {
        int[] array = gerarArray(tamanho, tipo);

        String tipoArray = switch (tipo) {
            case 1 -> "Aleatório";
            case 2 -> "Ordenado";
            case 3 -> "Inverso";
            default -> "Aleatório";
        };

        System.out.println("\n" + "-".repeat(60));
        System.out.println("CONFIGURAÇÃO DO TESTE:");
        System.out.println("- Tamanho do array: " + tamanho + " elementos");
        System.out.println("- Tipo do array: " + tipoArray);
        System.out.println("- Algoritmo: " + getNomeAlgoritmo(algoritmo));
        System.out.println("-".repeat(60));

        System.out.println("\nArray original (primeiros 15 elementos):");
        imprimirArray(array, 15);

        long inicio = System.nanoTime();

        try {
            switch (algoritmo) {
                case 1 -> {
                    System.out.println("\nExecutando Bubble Sort...");
                    new BubbleSort().sort(array);
                }
                case 2 -> {
                    System.out.println("\nExecutando Selection Sort...");
                    new SelectionSort().sort(array);
                }
                case 3 -> {
                    System.out.println("\nExecutando Insertion Sort...");
                    new InsertionSort().sort(array);
                }
                case 4 -> {
                    System.out.println("\nExecutando Merge Sort...");
                    new MergeSort().sort(array);
                }
                case 5 -> {
                    System.out.println("\nExecutando Quick Sort...");
                    new QuickSort().sort(array);
                }
                case 6 -> {
                    System.out.println("\nExecutando Counting Sort...");
                    new CountingSort().sort(array);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao executar algoritmo de ordenação: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        System.out.println("\nArray ordenado (primeiros 15 elementos):");
        imprimirArray(array, 15);

        boolean ordenado = AlgoritmosOrdenacao.isSorted(array);
        System.out.printf("\nRESULTADO:");
        System.out.printf("\n- Tempo de execução: %.2f ms", tempoMs);
        System.out.println("\n- Array ordenado corretamente: " + (ordenado ? "SIM" : "NÃO"));

        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static void testarTodosOrdenacao(int tamanho, int tipo) {
        int[] arrayOriginal = gerarArray(tamanho, tipo);

        String tipoArray = switch (tipo) {
            case 1 -> "Aleatório";
            case 2 -> "Ordenado";
            case 3 -> "Inverso";
            default -> "Aleatório";
        };

        System.out.println("\n" + "=".repeat(70));
        System.out.println("COMPARAÇÃO DE ALGORITMOS DE ORDENAÇÃO");
        System.out.println("=".repeat(70));
        System.out.println("Tamanho do array: " + tamanho + " elementos");
        System.out.println("Tipo do array: " + tipoArray);
        System.out.println("=".repeat(70));

        if (tipo == 1) {
            int min = arrayOriginal[0];
            int max = arrayOriginal[0];
            for (int num : arrayOriginal) {
                if (num < min)
                    min = num;
                if (num > max)
                    max = num;
            }
            System.out.println("Valores no array: de " + min + " a " + max);
        }

        System.out.println("\nAmostra do array (primeiros 15 elementos):");
        imprimirArray(arrayOriginal, 15);
        System.out.println();

        String[] algoritmos = {
                "Bubble Sort",
                "Selection Sort",
                "Insertion Sort",
                "Merge Sort",
                "Quick Sort",
                "Counting Sort"
        };

        System.out.println("RESULTADOS (tempo de execução em milissegundos):");
        System.out.println("-".repeat(60));

        double[] tempos = new double[algoritmos.length];

        for (int i = 0; i < algoritmos.length; i++) {
            String algoritmo = algoritmos[i];
            int[] array = arrayOriginal.clone();
            long inicio = System.nanoTime();

            try {
                switch (algoritmo) {
                    case "Bubble Sort" -> new BubbleSort().sort(array);
                    case "Selection Sort" -> new SelectionSort().sort(array);
                    case "Insertion Sort" -> new InsertionSort().sort(array);
                    case "Merge Sort" -> new MergeSort().sort(array);
                    case "Quick Sort" -> new QuickSort().sort(array);
                    case "Counting Sort" -> new CountingSort().sort(array);
                }
            } catch (Exception e) {
                System.err.println("Erro ao executar " + algoritmo + ": " + e.getMessage());
                e.printStackTrace();
            }

            long fim = System.nanoTime();
            double tempoMs = (fim - inicio) / 1_000_000.0;
            tempos[i] = tempoMs;

            boolean ordenado = AlgoritmosOrdenacao.isSorted(array);
            System.out.printf("%-20s: %8.2f ms %s%n",
                    algoritmo, tempoMs, ordenado ? "" : " [ERRO!]");
        }

        int idxMaisRapido = 0;
        for (int i = 1; i < tempos.length; i++) {
            if (tempos[i] < tempos[idxMaisRapido]) {
                idxMaisRapido = i;
            }
        }

        System.out.println("\n" + "-".repeat(60));
        System.out.println("RESUMO:");
        System.out.printf("Algoritmo mais rápido: %s (%.2f ms)\n",
                algoritmos[idxMaisRapido], tempos[idxMaisRapido]);

        System.out.println("\nArray após ordenação (primeiros 15 elementos):");
        int[] arrayFinal = arrayOriginal.clone();
        Arrays.sort(arrayFinal);
        imprimirArray(arrayFinal, 15);

        boolean ordenadoFinal = AlgoritmosOrdenacao.isSorted(arrayFinal);
        System.out.println("\nArray ordenado corretamente: " + (ordenadoFinal ? "SIM" : "NÃO"));

        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static void testarBuscaIndividual(int algoritmo, int[] array, int alvo) {
        if (array == null) {
            System.err.println("array nulo recebido em testarBuscaIndividual");
            return;
        }
        BuscaLinear buscaLinear = new BuscaLinear();
        BuscaBinaria buscaBinaria = new BuscaBinaria();

        System.out.println("\n" + "-".repeat(60));
        System.out.println("CONFIGURAÇÃO DO TESTE:");
        System.out.println("- Tamanho do array: " + array.length + " elementos");
        System.out.println("- Valor buscado: " + alvo);
        System.out.println("- Algoritmo: " + getNomeBusca(algoritmo));
        System.out.println("-".repeat(60));

        System.out.println("\nArray (primeiros 15 elementos):");
        imprimirArray(array, 15);

        long inicio = System.nanoTime();
        int resultado = -1;

        try {
            switch (algoritmo) {
                case 1 -> resultado = buscaLinear.buscar(array, alvo);
                case 2 -> resultado = buscaLinear.buscarRecursiva(array, alvo);
                case 3 -> resultado = buscaLinear.buscarDuasPontas(array, alvo);
                case 4 -> resultado = buscaBinaria.buscar(array, alvo);
                case 5 -> resultado = buscaBinaria.buscarRecursiva(array, alvo);
            }
        } catch (Exception e) {
            System.err.println("Erro ao executar busca: " + e.getMessage());
            e.printStackTrace();
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        System.out.printf("\nRESULTADO:");
        System.out.printf("\n- Posição encontrada: %s",
                resultado >= 0 ? resultado : "Não encontrado");
        System.out.printf("\n- Tempo de execução: %.6f ms", tempoMs);

        System.out.print("\n\nPressione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static void testarTodasBuscas(int[] array, int alvo) {
        if (array == null) {
            System.err.println("array nulo recebido em testarTodasBuscas");
            return;
        }
        BuscaLinear buscaLinear = new BuscaLinear();
        BuscaBinaria buscaBinaria = new BuscaBinaria();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPARAÇÃO DE ALGORITMOS DE BUSCA");
        System.out.println("=".repeat(60));
        System.out.println("Tamanho do array: " + array.length + " elementos");
        System.out.println("Valor buscado: " + alvo);
        System.out.println("=".repeat(60));

        System.out.println("\nArray (primeiros 15 elementos):");
        imprimirArray(array, 15);
        System.out.println();

        String[] algoritmos = {
                "Busca Linear Iterativa",
                "Busca Linear Recursiva",
                "Busca Linear Duas Pontas",
                "Busca Binária Iterativa",
                "Busca Binária Recursiva"
        };

        System.out.println("RESULTADOS (tempo em milissegundos):");
        System.out.println("-".repeat(60));

        for (String algoritmo : algoritmos) {
            long inicio = System.nanoTime();
            int resultado = -1;

            try {
                switch (algoritmo) {
                    case "Busca Linear Iterativa" -> resultado = buscaLinear.buscar(array, alvo);
                    case "Busca Linear Recursiva" -> resultado = buscaLinear.buscarRecursiva(array, alvo);
                    case "Busca Linear Duas Pontas" -> resultado = buscaLinear.buscarDuasPontas(array, alvo);
                    case "Busca Binária Iterativa" -> resultado = buscaBinaria.buscar(array, alvo);
                    case "Busca Binária Recursiva" -> resultado = buscaBinaria.buscarRecursiva(array, alvo);
                }
            } catch (Exception e) {
                System.err.println("Erro ao executar " + algoritmo + ": " + e.getMessage());
                e.printStackTrace();
            }

            long fim = System.nanoTime();
            double tempoMs = (fim - inicio) / 1_000_000.0;

            System.out.printf("%-25s: %8.4f ms (posição: %d)%n",
                    algoritmo, tempoMs, resultado);
        }

        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static int[] gerarArray(int tamanho, int tipo) {
        return switch (tipo) {
            case 1 -> GeradorDados.gerarArrayInteiros(tamanho);
            case 2 -> GeradorDados.gerarArrayOrdenado(tamanho);
            case 3 -> GeradorDados.gerarArrayInverso(tamanho);
            default -> GeradorDados.gerarArrayInteiros(tamanho);
        };
    }

    private static void imprimirArray(int[] array, int limite) {
        System.out.print("[");
        for (int i = 0; i < Math.min(limite, array.length); i++) {
            System.out.print(array[i]);
            if (i < Math.min(limite, array.length) - 1) {
                System.out.print(", ");
            }
        }
        if (array.length > limite) {
            System.out.print(", ...");
        }
        System.out.println("]");
    }

    private static String getNomeAlgoritmo(int codigo) {
        return switch (codigo) {
            case 1 -> "Bubble Sort";
            case 2 -> "Selection Sort";
            case 3 -> "Insertion Sort";
            case 4 -> "Merge Sort";
            case 5 -> "Quick Sort";
            case 6 -> "Counting Sort";
            default -> "Desconhecido";
        };
    }

    private static String getNomeBusca(int codigo) {
        return switch (codigo) {
            case 1 -> "Busca Linear Iterativa";
            case 2 -> "Busca Linear Recursiva";
            case 3 -> "Busca Linear Duas Pontas";
            case 4 -> "Busca Binária Iterativa";
            case 5 -> "Busca Binária Recursiva";
            default -> "Desconhecido";
        };
    }
}