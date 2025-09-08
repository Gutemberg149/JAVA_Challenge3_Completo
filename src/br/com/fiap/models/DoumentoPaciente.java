package br.com.fiap.models;

public class DoumentoPaciente {

    private int id_documneto;
    private String type_documento;
    private int isValido;

    @Override
    public String toString() {
        return "Documento do paciente:" +
                "Id: " + id_documneto +
                " | Tipo de documento: " + type_documento +
                " | Valido: " + (isValido == 1 ? "Documento Válido" : "Documento não Válido") +
                "\n--------------------------------------------\n";
    }

    public int getId_documneto() {
        return id_documneto;
    }

    public void setId_documneto(int id_documneto) {
        this.id_documneto = id_documneto;
    }

    public String getType_documento() {
        return type_documento;
    }

    public void setType_documento(String type_documento) {
        this.type_documento = type_documento;
    }

    public int isValido() {
        return isValido;
    }

    public void setValido(int valido) {
        this.isValido = valido;
    }
}
