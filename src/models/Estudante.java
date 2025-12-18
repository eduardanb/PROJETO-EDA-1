package models;

public class Estudante implements Comparable<Estudante> {
    private int matricula;
    private String nome;
    private int nota;

    public Estudante(int matricula, String nome, int nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public int getNota() { return nota; }

    @Override
    public int compareTo(Estudante outro) {
        // Nota decrescente
        if (this.nota != outro.nota) {
            return Integer.compare(outro.nota, this.nota);
        }
        // Nome crescente
        int nomeCompare = this.nome.compareTo(outro.nome);
        if (nomeCompare != 0) {
            return nomeCompare;
        }
        // Matrícula
        return Integer.compare(this.matricula, outro.matricula);
    }

    @Override
    public String toString() {
        return String.format("Mat: %d, Nome: %s, Nota: %d",
                matricula, nome, nota);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estudante estudante = (Estudante) obj;
        return matricula == estudante.matricula;
    }
}