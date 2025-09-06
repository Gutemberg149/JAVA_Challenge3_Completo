package br.com.fiap.testes;
import br.com.fiap.dao.CadastarPascienteDao;
import br.com.fiap.models.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Insercao {

    public static void menu() {
        System.out.println("Hc Hospital");
        System.out.println("=======================================");
        System.out.println("1 - Cadastrar");
        System.out.println("0 - Finalizar");

    }
    public static Paciente leituraDados(Paciente paciente){
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Id do paciente: ");
        paciente.setId(leitorNum.nextInt());

        System.out.println("Nome do paciente: ");
        paciente.setNome(leitor.nextLine());

        System.out.println("CPF do paciente: ");
        paciente.setCpf(leitorNum.nextInt());
        System.out.println(paciente);
        return paciente;
    };



    public static void main(String[] args) {

//        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        CadastarPascienteDao cadastarPascienteDao = new CadastarPascienteDao();
       // List<Paciente> pacientes = new ArrayList<>();

        int op;
        do {
            menu();
            op = leitorNum.nextInt();
            switch (op) {
                case 1: {
                    System.out.println("CADASTRAR PACIENTE" +
                            "\n===============================================");
                    Paciente novoPaciente = new Paciente();
                    cadastarPascienteDao.cadastrarPacientefunc(leituraDados(novoPaciente));
                    System.out.println("Paciente cadastrado com sucesso!");
                }break;

            }
        }while (op != 0);

    }
}
