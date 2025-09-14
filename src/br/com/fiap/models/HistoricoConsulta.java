package br.com.fiap.models;

public class HistoricoConsulta {
    private int id_historico;
    private String sintomas_historico;
    private String diagnostico;
    private String observacao;

    public boolean isDiagnosticoCritical() {
        if (diagnostico != null && diagnostico.toLowerCase().contains("grave")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Historico de consulta:" +
                "Id historico: " + id_historico +
                " | sintomas historico: " + sintomas_historico +
                " | diagnostico: " + diagnostico +
                " | Observações: " + observacao +
                "\n------------------------------------\n";
    }

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public String getSintomas_historico() {
        return sintomas_historico;
    }

    public void setSintomas_historico(String sintomas_historico) {
        this.sintomas_historico = sintomas_historico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
