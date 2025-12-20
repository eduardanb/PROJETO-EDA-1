package analise;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Iniciando Analisador de Performance...");

        // Executa benchmarks de busca e ordenação.
        // Ajuste ou comente conforme o que deseja rodar.
        AnalisadorPerformance.analisarBusca();
        AnalisadorPerformance.analisarOrdenacaoInteiros();
        AnalisadorPerformance.analisarOrdenacaoEstudantes();

        System.out.println("Execução finalizada.");
    }
}
