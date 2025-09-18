package br.com.fiap.models;

//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Medico {
    private int id_medico;
    private String especialidade;
    private int crm;
    private ConsultaOnline consultaOnline;
    private List<ConsultaOnline> listconsultaOnline =new ArrayList<>();


    @Override
    public String toString() {
        return "Medico:" +
                "Id do medico: " + id_medico +
                " | Especialidade do médico: " + especialidade +
                " | Crm do médico: " + crm +
                " | " + (consultaOnline != null ? consultaOnline : "Nenhuma consulta associada") +
                "\n------------------------------------\n";
    }

    public ConsultaOnline getConsultaOnline() {
        return consultaOnline;
    }

    public void setConsultaOnline(ConsultaOnline consultaOnline) {
        this.consultaOnline = consultaOnline;
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
