import java.io.Serializable;

public class Unidade implements Serializable  {
    private String nome;
    private int puntos;
    private String cod; // Opcional, pero útil para a consulta

    public Unidade(String cod, String nome, int puntos) {
        this.cod = cod;
        this.nome = nome;
        this.puntos = puntos;
    }

    public int getCod() {
        return Integer.parseInt(cod);
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getPuntos() {
        return puntos;
    }

    // Método para o formato de saída
    @Override
    public String toString() {
        return cod + " - " + nome + " - " + puntos;
    }
}