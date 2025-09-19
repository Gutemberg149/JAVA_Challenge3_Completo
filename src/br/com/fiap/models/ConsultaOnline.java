package br.com.fiap.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaOnline {
    private int id_consulta;
    private LocalDate dataConsulta;
    private String status;
    private String link;
    private Exame exame;
    private List<Exame> listexame=new ArrayList<>();
    

    @Override
    public String toString() {
        return "Consulta online:" +
                "Id: " + id_consulta +
                " | Data: " + dataConsulta +
                " | Status: " + status +
                " | Link: " + link +
                " | " + (exame != null ? exame.toString() : "Nenhum exame associado") +
                "\n------------------------------------\n";
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
