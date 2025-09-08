package br.com.fiap.models;

import java.time.LocalDate;

public class ConfirmacaoConsulta {
    private int id_confirmacao;
    private int codigoConfirmacao;
    private String metodoconfirmacao;

    @Override
    public String toString() {
        return "Confirmacão da consulta:" +
                "Id da confirmação: " + id_confirmacao +
                " | Codigo de confirmação: " + codigoConfirmacao +
                " | Metodo de confirmação: " + metodoconfirmacao +
                "\n------------------------------------\n";
    }

    public int getId_confirmacao() {
        return id_confirmacao;
    }

    public void setId_confirmacao(int id_confirmacao) {
        this.id_confirmacao = id_confirmacao;
    }

    public int getCodigoConfirmacao() {
        return codigoConfirmacao;
    }

    public void setCodigoConfirmacao(int codigoConfirmacao) {
        this.codigoConfirmacao = codigoConfirmacao;
    }

    public String getMetodoconfirmacao() {
        return metodoconfirmacao;
    }

    public void setMetodoconfirmacao(String metodoconfirmacao) {
        this.metodoconfirmacao = metodoconfirmacao;
    }
}
