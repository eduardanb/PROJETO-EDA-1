#  PROJETO-EDA-1

**Algoritmos de Ordenação e Busca em Java**

##  Descrição

Projeto didático para **implementação, comparação e análise de desempenho** de algoritmos clássicos de **ordenação e busca** em Java.
Inclui utilitários para geração de dados e rotinas de **benchmark**, permitindo observar diferenças práticas entre algoritmos em cenários distintos (vetores aleatórios, ordenados, inversos e quase ordenados).

O projeto é voltado para **estudo de Estruturas de Dados e Algoritmos (EDA)**, unindo teoria e prática.

---

##  Objetivos

* Implementar algoritmos clássicos de ordenação e busca.
* Comparar **tempo de execução**, **estabilidade** e **uso de memória**.
* Analisar efeitos reais da JVM (JIT, GC, cache, alocação).
* Explorar variações e otimizações (ex.: QuickSort com shuffle, introsort-like).

---

##  Estrutura do Projeto

```
src/
├── analise
│   ├── Main.java                 # Interface interativa (menu)
│   ├── AnalisadorPerformance.java # Execução de benchmarks
│   └── GeradorDados.java         # Geração de vetores (aleatório, ordenado, inverso, parcial)
│
├── busca
│   ├── BuscaLinear.java          # Busca linear (variações)
│   └── BuscaBinaria.java         # Busca binária (iterativa e recursiva)
│
├── ordenacao                     # Algoritmos de ordenação
│   ├── BubbleSort.java
│   ├── SelectionSort.java
│   ├── InsertionSort.java
│   ├── MergeSort.java
│   ├── QuickSort.java
│   └── CountingSort.java
│
└── models
    └── Estudante.java            # Modelo usado em benchmarks com objetos
```

---

##  Requisitos

* **Java 11 ou superior** (recomendado **OpenJDK 17+**)
* Memória suficiente para testes grandes (ex.: `n ≥ 500.000`)

---

##  Compilação

### Linux / macOS

```bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
```

### Windows (cmd)

```cmd
mkdir out
for /r %f in (*.java) do javac -d out "%f"
```

### Windows (PowerShell)

```powershell
mkdir out
Get-ChildItem -Recurse -Filter *.java | ForEach-Object { javac -d out $_.FullName }
```

---

## ▶ Execução — Modo Interativo

```bash
java -cp out analise.Main
```

No menu é possível:

* Escolher **busca** ou **ordenação**
* Selecionar algoritmo
* Definir tamanho do vetor
* Escolher tipo de entrada (aleatória, ordenada, inversa, quase ordenada)

---

##  Execução — Benchmarks Automatizados

### Opção 1 — Pelo menu

Escolha **“Testar Todos os Algoritmos”** e informe o tamanho do vetor.

### Opção 2 — Runner dedicado

Crie um `Runner.java` simples:

```java
package analise;

public class Runner {
    public static void main(String[] args) {
        AnalisadorPerformance.analisarBusca();
        // AnalisadorPerformance.analisarOrdenacaoInteiros();
    }
}
```

---

##  Interpretação dos Resultados

### Buscas

* **Busca Linear**: O(n)
* **Busca Binária**: O(log n), requer vetor ordenado
  Ex.: para `n = 500.000`, ~19 comparações no pior caso.

### Ordenação

* Avalie **tempo médio**, **desvio**, **estabilidade** e **memória**.
* Atenção a entradas adversas (pior caso).

---

##  Conclusões Práticas

* **InsertionSort**: excelente para vetores pequenos ou quase ordenados.
* **QuickSort (bem implementado)**: geralmente o mais rápido para grandes vetores aleatórios.
* **MergeSort**: estável e previsível, com custo de memória extra.
* **TimSort**: ótimo para dados parcialmente ordenados.
* **CountingSort**: desempenho linear quando o domínio dos valores é pequeno (`O(n + k)`).

---

##  Observações Técnicas

* Alocação de memória e cache impactam tanto quanto a complexidade teórica.
* Implementações híbridas (Introsort, TimSort) trazem ganhos reais.
* Recursão profunda pode causar `StackOverflowError`.
* Em produção, `Arrays.sort(int[])` (Dual-Pivot QuickSort) costuma ser a melhor escolha.

---

##  Exportação de Resultados

É possível adaptar o `AnalisadorPerformance` para exportar resultados em **CSV ou JSON**, incluindo:

* algoritmo
* cenário
* tamanho (`n`)
* média (ms)
* desvio padrão
* array ordenado (true/false)

---

##  Licença

Consulte o arquivo `LICENSE` na raiz do repositório.

---

##  Desenvolvido por: 
 *Maria Eduarda da Nóbrega,*
 *Adrielly Carla Ferreira de Melo.*

 *Projeto 1 da disciplina de Estrutura de Dados, 2025.*

