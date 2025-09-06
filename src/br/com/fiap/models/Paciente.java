package br.com.fiap.models;

public class Paciente {
    private int id_paciente;
    private String nome_paciente;
    private int cpf_paciente;

    @Override
    public String toString() {
        return "Paciente:" +
                "Id: " + id_paciente +
                " | Nome: " + nome_paciente +
                " | CPF: " + cpf_paciente +
                "\n=======================================\n";
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

    public int getCpf() {
        return cpf_paciente;
    }

    public void setCpf(int cpf) {
        this.cpf_paciente = cpf;
    }

//    public Paciente(int id, String nome, int cpf) {
//        this.id_paciente = id;
//        this.nome_paciente = nome;
//        this.cpf_paciente = cpf;
//    }
}
