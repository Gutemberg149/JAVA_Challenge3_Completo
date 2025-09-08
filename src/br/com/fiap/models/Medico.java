package br.com.fiap.models;

import java.time.LocalDate;

public class Medico {
    private int id_medico;
    private String especialidade;
    private int crm;

    @Override
    public String toString() {
        return "Medico:" +
                "Id do medicop: " + id_medico +
                " | Especialidade do médico: " + especialidade +
                " |Crm do médico: " + crm +
                "\n------------------------------------\n";
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }
}
