package br.com.fiap.models;

public class Exame {
    private int id_exame;
    private String nome_exame;
    private String resultado_exame;

    public int getId_exame() {
        return id_exame;
    }

    public void setId_exame(int id_exame) {
        this.id_exame = id_exame;
    }

    public String getNome_exame() {
        return nome_exame;
    }

    public void setNome_exame(String nome_exame) {
        this.nome_exame = nome_exame;
    }

    public String getResultado_exame() {
        return resultado_exame;
    }

    public void setResultado_exame(String resultado_exame) {
        this.resultado_exame = resultado_exame;
    }

    public String verificarResultado() {
        if (resultado_exame != null && resultado_exame.toLowerCase().contains("normal")) {
            return "Resultado normal";
        } else {
            return "Resultado fora do padrão";
        }
    }

    @Override
    public String toString() {
        return "Exame: " +
                "ID do exame: " + id_exame + " | " +
                "Nome do exame: " + nome_exame + " | " +
                "Resultado do exame: " + resultado_exame + '\n' +
                '}';
    }
}
