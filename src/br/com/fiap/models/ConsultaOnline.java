package br.com.fiap.models;

import java.time.LocalDate;

public class ConsultaOnline {
    private int id_consulta;
    private LocalDate dataConsulta;
    private String status;
    private String link;

    @Override
    public String toString() {
        return "Consulta online:" +
                "Id da consulta: " + id_consulta +
                " | Data da consulta: " + dataConsulta +
                " | Status da consulta: " + status +
                " | Link da consulta: " + link +
                "\n------------------------------------\n";
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
