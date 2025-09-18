package br.com.fiap.models;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private int id_paciente;
    private String nome_paciente;
    private String cpf_paciente;
    private ConsultaOnline consultaOnline;
    private List<ConsultaOnline> listconsultaOnline =new ArrayList<>();

    public List<ConsultaOnline> getListconsultaOnline() {
        return listconsultaOnline;
    }

    public void setListconsultaOnline(List<ConsultaOnline> listconsultaOnline) {
        this.listconsultaOnline = listconsultaOnline;
    }

    @Override
    public String toString() {
        return "Paciente:" +
                "Id: " + id_paciente +
                " | Nome: " + nome_paciente +
                " | CPF: " + cpf_paciente +
                "| Consultas online: " + consultaOnline+

                "\n------------------------------------\n";
    }


    public ConsultaOnline getConsultaOnline() {
        return consultaOnline;
    }

    public void setConsultaOnline(ConsultaOnline consultaOnline) {
        this.consultaOnline = consultaOnline;
    }

    public int getId() {
        return id_paciente;
    }

    public void setId(int id) {
        this.id_paciente = id;
    }

    public String getNome() {
        return nome_paciente;
    }

    public void setNome(String nome) {
        this.nome_paciente = nome;
    }

    public String getCpf() {
        return cpf_paciente;
    }

    public void setCpf(String cpf) {
        this.cpf_paciente = cpf;
    }


    public boolean isCpfValido() {
        return cpf_paciente.length()==11;
    }


}
