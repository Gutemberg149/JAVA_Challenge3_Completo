package br.com.fiap.testes;
import br.com.fiap.dao.*;
import br.com.fiap.enums.PerguntasEnum;
import br.com.fiap.models.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Insercao {

    public static void menuCrud() {
        System.out.println("Hc Hospita CRUD");
        System.out.println("=======================================");
        System.out.println("1 -  Menu Cadastrar");
        System.out.println("2 -  Menu Listar");
        System.out.println("3 -  Menu Update");
        System.out.println("4 -  Menu Excluir");
        System.out.println("5 -  Perguntas Frequentes");

        System.out.println("0 -  FINALIZAR");
    }

    public static void menuCadastrar() {
        System.out.println("-----Hc Hospital - Menu Cadastrar-------");
        System.out.println("1  -  Cadastrar pacientes");
        System.out.println("2  -  Cadastrar Consultas Online");
        System.out.println("3  -  Cadastrar Médicos");
        System.out.println("4  -  Cadastrar Exames  (normal ou nao)");
        System.out.println("5  -  Cadastrar Historico das consultas");

        System.out.println("0  -  PARA SAIR DE CADASTRAR");


    }

    public static void menuListar() {
        System.out.println("-------Hc Hospital- Menu Listar-------");
        System.out.println("1 -  Listar  pacientes");
        System.out.println("2 -  Listar  Consultas online");
        System.out.println("3 -  Listar  Médicos");
        System.out.println("4 -  Listar  Exames");
        System.out.println("5 -  Listar  Historico das Consultas");

        System.out.println("0 - PARA SAIR DE LISTAR");


    }

    public static void menuUpdate(){
        System.out.println("-------Hc Hospital- Menu Update-------");
        System.out.println("1  -  UpDate  pacientes");
        System.out.println("2  -  UpDate  Consultas online");
        System.out.println("3  -  UpDate  Médicos");
        System.out.println("4  -  UpDate  Exames");
        System.out.println("5  -  UpDate  Historico das Consultas");

        System.out.println("0 - PARA SAIR DO UPDATE");
    }

    public static void menuExcluir(){
        System.out.println("-------Hc Hospital- Menu Excluir-------");
        System.out.println("1  -  Excluir  pacientes");
        System.out.println("2  -  Excluir  Consultas online");
        System.out.println("3  -  Excluir  Médicos");
        System.out.println("4  -  Excluir  Exames");
        System.out.println("5  -  Excluir  Historico das Consultas");

        System.out.println("0 - PARA SAIR DE EXCLUIR");
    }

    private static void mostrarFAQMenu() {
        int escolha = -1;
        PerguntasEnum[] perguntas = PerguntasEnum.values();
        while (escolha != 0) {
            System.out.println("\n========== PERGUNTAS FREQUENTES ==========\nEscolha uma pergunta (1-8) ou 0 para sair\"");

            for (int i = 0; i < perguntas.length; i++) {
                System.out.println((i + 1) + ". " + perguntas[i].getPergunta());
            }

            try {
                Scanner scanner = new Scanner(System.in);
                escolha = Integer.parseInt(scanner.nextLine());
                if (escolha < 0 || escolha > perguntas.length) {
                    System.out.println("Opção inválida!");
                    continue;
                }

                if (escolha >= 1 && escolha <= perguntas.length) {
                    PerguntasEnum pergunta = perguntas[escolha - 1];
                    System.out.println("\nPergunta: " + pergunta.getPergunta());
                    System.out.println("Resposta: " + pergunta.getResposta());
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    public static Paciente leituraDados(Paciente paciente) {
    Scanner scanner = new Scanner(System.in);
    ConsultaOnlineDao consultaDao = new ConsultaOnlineDao();


    boolean idValido = false;
    while (!idValido) {
        try {
            System.out.println("Id do paciente: ");
            paciente.setId(scanner.nextInt());
            scanner.nextLine();
            idValido = true;
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número válido para o ID!");
            scanner.nextLine();
        }
    }


    System.out.println("Nome do paciente: ");
    paciente.setNome(scanner.nextLine());


    boolean cpfValido = false;
    while (!cpfValido) {
        System.out.println("CPF do paciente (apenas números - 11 dígitos): ");
        String cpfInput = scanner.nextLine();
        if (cpfInput.matches("\\d{11}")) {
            paciente.setCpf(cpfInput);
            cpfValido = true;
        } else {
            System.out.println("Erro: CPF deve ter exatamente 11 dígitos numéricos.");
        }
    }


    System.out.println("Deseja associar uma consulta online? (s/n): ");
    String resposta = scanner.nextLine().trim().toLowerCase();
    if (resposta.equals("s")) {
        boolean idConsultaValido = false;
        int idConsulta = 0;
        while (!idConsultaValido) {
            try {
                System.out.println("ID da consulta online: ");
                idConsulta = scanner.nextInt();
                scanner.nextLine();
                idConsultaValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido para o ID da consulta!");
                scanner.nextLine();
            }
        }


        ConsultaOnline consulta = consultaDao.buscarPorIdConsultaOnline(idConsulta);

        if (consulta == null) {
            System.out.println("Consulta online não encontrada. Por favor, informe os dados da nova consulta.");

            consulta = new ConsultaOnline();
            consulta.setId_consulta(idConsulta);


            boolean dataValida = false;
            while (!dataValida) {
                try {
                    System.out.print("Digite o dia da consulta (1-31): ");
                    int dia = scanner.nextInt();
                    System.out.print("Digite o mês da consulta (1-12): ");
                    int mes = scanner.nextInt();
                    System.out.print("Digite o ano da consulta (ex: 2024): ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();

                    consulta.setDataConsulta(LocalDate.of(ano, mes, dia));
                    dataValida = true;
                } catch (Exception e) {
                    System.out.println("Data inválida. Tente novamente.");
                    scanner.nextLine();
                }
            }


            System.out.println("Status da consulta: ");
            consulta.setStatus(scanner.nextLine());


            System.out.println("Link da consulta: ");
            consulta.setLink(scanner.nextLine());


            consultaDao.cadastrarConsultaOnline(consulta);
            System.out.println("Nova consulta online cadastrada com sucesso.");
        } else {
            System.out.println("Consulta online encontrada e associada ao paciente.");
        }

        paciente.setConsultaOnline(consulta);
    } else {
        paciente.setConsultaOnline(null);
    }

    System.out.println(paciente);
    return paciente;
}


public static ConsultaOnline leituraDadosConsultaOnline(ConsultaOnline consultaOnline) {
    Scanner scanner = new Scanner(System.in);
    ExameDao exameDao = new ExameDao();


    boolean idValido = false;
    while (!idValido) {
        try {
            System.out.println("Id da consulta: ");
            consultaOnline.setId_consulta(scanner.nextInt());
            scanner.nextLine();
            idValido = true;
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número válido para o ID da consulta!");
            scanner.nextLine();
        }
    }


    boolean dataValida = false;
    while (!dataValida) {
        try {
            System.out.print("Digite o dia da consulta (1-31): ");
            int dia = scanner.nextInt();
            System.out.print("Digite o mês da consulta (1-12): ");
            int mes = scanner.nextInt();
            System.out.print("Digite o ano da consulta (ex: 2024): ");
            int ano = scanner.nextInt();
            scanner.nextLine();

            consultaOnline.setDataConsulta(LocalDate.of(ano, mes, dia));
            dataValida = true;
        } catch (Exception e) {
            System.out.println("Data inválida. Tente novamente.");
            scanner.nextLine();
        }
    }

    System.out.println("Status da consulta: ");
    consultaOnline.setStatus(scanner.nextLine());

    System.out.println("Link da consulta: ");
    consultaOnline.setLink(scanner.nextLine());


    System.out.println("Deseja associar um exame? (s/n): ");
    String resposta = scanner.nextLine().trim().toLowerCase();
    if (resposta.equals("s")) {
        boolean idExameValido = false;
        int idExame = 0;
        while (!idExameValido) {
            try {
                System.out.println("ID do exame: ");
                idExame = scanner.nextInt();
                scanner.nextLine();
                idExameValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido para o ID do exame!");
                scanner.nextLine();
            }
        }

        Exame exame = exameDao.buscarPorIdExame(idExame);

        if (exame == null) {
            System.out.println("Exame não encontrado. Por favor, informe os dados do novo exame.");

            exame = new Exame();
            exame.setId_exame(idExame);

            System.out.println("Nome do exame: ");
            exame.setNome_exame(scanner.nextLine());

            System.out.println("Resultado do exame: ");
            exame.setResultado_exame(scanner.nextLine());

            exameDao.cadastrarExame(exame);
            System.out.println("Novo exame cadastrado com sucesso.");
        } else {
            System.out.println("Exame encontrado: " + exame);
            System.out.println("Deseja usar este exame existente? (s/n): ");
            String usarExistente = scanner.nextLine().trim().toLowerCase();

            if (!usarExistente.equals("s")) {

                boolean novoIdValido = false;
                while (!novoIdValido) {
                    try {
                        System.out.println("Digite um novo ID para o exame: ");
                        int novoId = scanner.nextInt();
                        scanner.nextLine();


                        Exame exameExistente = exameDao.buscarPorIdExame(novoId);
                        if (exameExistente != null) {
                            System.out.println("Este ID já existe. Escolha outro ID.");
                            continue;
                        }

                        exame = new Exame();
                        exame.setId_exame(novoId);

                        System.out.println("Nome do exame: ");
                        exame.setNome_exame(scanner.nextLine());

                        System.out.println("Resultado do exame: ");
                        exame.setResultado_exame(scanner.nextLine());

                        exameDao.cadastrarExame(exame);
                        System.out.println("Novo exame cadastrado com sucesso.");
                        novoIdValido = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Erro: Digite um número válido para o ID!");
                        scanner.nextLine();
                    }
                }
            }
        }

        consultaOnline.setExame(exame);
    } else {
        consultaOnline.setExame(null);
    }

    System.out.println(consultaOnline);
    return consultaOnline;
}

    public static Medico leituraDadosMedicos(Medico medico) {
        Scanner scanner = new Scanner(System.in);
        ConsultaOnlineDao consultaDao = new ConsultaOnlineDao();

        boolean idValido = false;
        while (!idValido) {
            try {
                System.out.println("Id do médico: ");
                medico.setId_medico(scanner.nextInt());
                scanner.nextLine();
                idValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido para o ID!");
                scanner.nextLine();
            }
        }

        System.out.println("Especialidade do médico: ");
        medico.setEspecialidade(scanner.nextLine());


        boolean crmValido = false;
        while (!crmValido) {
            try {
                System.out.println("CRM do médico: ");
                medico.setCrm(scanner.nextInt());
                scanner.nextLine();
                crmValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido para o CRM!");
                scanner.nextLine();
            }
        }

        System.out.println("Deseja associar uma consulta online? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        if (resposta.equals("s")) {
            boolean idConsultaValido = false;
            int idConsulta = 0;
            while (!idConsultaValido) {
                try {
                    System.out.println("ID da consulta online: ");
                    idConsulta = scanner.nextInt();
                    scanner.nextLine();
                    idConsultaValido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Digite um número válido para o ID da consulta!");
                    scanner.nextLine();
                }
            }

            ConsultaOnline consulta = consultaDao.buscarPorIdConsultaOnline(idConsulta);

            if (consulta == null) {
                System.out.println("Consulta online não encontrada. Por favor, informe os dados da nova consulta.");

                consulta = new ConsultaOnline();
                consulta.setId_consulta(idConsulta);

                boolean dataValida = false;
                while (!dataValida) {
                    try {
                        System.out.print("Digite o dia da consulta (1-31): ");
                        int dia = scanner.nextInt();
                        System.out.print("Digite o mês da consulta (1-12): ");
                        int mes = scanner.nextInt();
                        System.out.print("Digite o ano da consulta (ex: 2024): ");
                        int ano = scanner.nextInt();
                        scanner.nextLine();

                        consulta.setDataConsulta(LocalDate.of(ano, mes, dia));
                        dataValida = true;
                    } catch (Exception e) {
                        System.out.println("Data inválida. Tente novamente.");
                        scanner.nextLine();
                    }
                }

                System.out.println("Status da consulta: ");
                consulta.setStatus(scanner.nextLine());

                System.out.println("Link da consulta: ");
                consulta.setLink(scanner.nextLine());

                consultaDao.cadastrarConsultaOnline(consulta);
                System.out.println("Nova consulta online cadastrada com sucesso.");
            } else {
                System.out.println("Consulta online encontrada e associada ao médico.");
            }

            medico.setConsultaOnline(consulta);
        } else {
            medico.setConsultaOnline(null);
        }

        System.out.println(medico);
        return medico;
    }

    public static Exame leituraExames(Exame exame){
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Id do exame: ");
        exame.setId_exame(leitorNum.nextInt());

        System.out.println("Nome do exame: ");
        exame.setNome_exame(leitor.nextLine());

        System.out.println("Resultados do exame: ");
        exame.setResultado_exame(leitor.nextLine());

        return exame;
    };

    public static HistoricoConsulta leituraDadoshistoricosconsultas(HistoricoConsulta historicoconsulta){
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Id do Historico: ");
        historicoconsulta.setId_historico(leitorNum.nextInt());

        System.out.println("Sintomas: ");
        historicoconsulta.setSintomas_historico(leitor.nextLine());

        System.out.println("Diagnostico (grave | leve): ");
        historicoconsulta.setDiagnostico(leitor.nextLine());

        System.out.println("Observações: ");
        historicoconsulta.setObservacao(leitor.nextLine());

        return historicoconsulta;
    };


    public static void main(String[] args) {

        Scanner leitorNum = new Scanner(System.in);
        PacienteDao cadastros = new PacienteDao();
        ConsultaOnlineDao consultaOnlineDao = new ConsultaOnlineDao();
        MedicoDao cadastroMedicoDao = new MedicoDao();
        ExameDao exameDao = new ExameDao();
        HistoricoConsultaDao historicoConsultaDao = new HistoricoConsultaDao();

        int opCrud;
        int opCadastrar;
        int opListar;
        int opUpDate;
        int opExcluir;

//        ------------------- Classes para Listar  informação------------------------

        class ListarPacientes {
            private List<Paciente> pacientesList;

            public ListarPacientes() {
                PacienteDao dao = new PacienteDao();
                this.pacientesList = dao.listarPacientes();
            }

            public void imprimirPacientes() {
                for (Paciente p : pacientesList) {
                    System.out.println(p);
                }
            }
        }

        class ListarConsultasOnline {
            private List<ConsultaOnline> consultaOnlinesList;

            public ListarConsultasOnline() {
                ConsultaOnlineDao dao = new ConsultaOnlineDao();
                this.consultaOnlinesList = dao.ListarConsultasOnline();
            }

            public void imprimirconsultas() {
                for (ConsultaOnline p : consultaOnlinesList) {
                    System.out.println(p);
                }
            }
        }

        class ListarMedicos {
            private List<Medico> medicosList;

            public ListarMedicos() {
                MedicoDao dao = new MedicoDao();
                this.medicosList = dao.ListarMedicos();
            }

            public void imprimirMedicos() {
                for (Medico p : medicosList) {
                    System.out.println(p);
                }
            }
        }

        class ListarExames{
            private List<String> exameList;

            public ListarExames() {
                ExameDao dao = new ExameDao();
                this.exameList = dao.ListarExamesComResultado();
            }

            public void imprimirExames() {
                for (String p : exameList) {
                    System.out.println(p);
                }
            }
        }

        class ListarhistoricosConsultas {
            private List<String> historicoconsultas;

            public ListarhistoricosConsultas() {
                HistoricoConsultaDao dao = new HistoricoConsultaDao();
                this.historicoconsultas = dao.ListarHistoricosConsultasComDiagnosticoCritico();
            }

            public void imprimirHistoricos() {
                for (String p : historicoconsultas) {
                    System.out.println(p);
                }
            }
        }

//      ------------------- Classes para upDate de informação------------------------
         class UpdatePaciente {

            public void atualizarPaciente() {
                Scanner scanner = new Scanner(System.in);

                PacienteDao dao = new PacienteDao();

                System.out.println("Digite o id do paciente desejado: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Paciente paciente = dao.buscarPorIdPaciente(id);
                if (paciente == null) {
                    System.out.println("Paciente não encontrado!");
                    return;
                }

                System.out.println("Qual campo deseja alterar?");
                System.out.println("1 - nome");
                System.out.println("2 - cpf");
                int op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        System.out.println("Digite o novo nome:");
                        String novoNome = scanner.nextLine();
                        paciente.setNome(novoNome);
                        break;
                    case 2:
                        System.out.println("Digite o novo CPF:");
                        String novoCpf = scanner.nextLine();
                        scanner.nextLine();
                        paciente.setCpf(novoCpf);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        return;
                }

                dao.upDatePaciente(paciente);
                System.out.println("Paciente atualizado: " + paciente);
            }
        }

         class UpdateConsultaOnline {

            public void atualizarConsultaOnline() {
                Scanner scanner = new Scanner(System.in);

                ConsultaOnlineDao dao = new ConsultaOnlineDao();

                System.out.println("Digite o id da consulta desejada: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                ConsultaOnline consulta = dao.buscarPorIdConsultaOnline(id);
                if (consulta == null) {
                    System.out.println("Paciente não encontrado!");
                    return;
                }

                System.out.println("Qual campo deseja alterar?");
                System.out.println("1 - Data");
                System.out.println("2 - Status");
                System.out.println("3 - Link");
                int op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        Scanner leitorDates = new Scanner(System.in);
                        System.out.print("Digite o dia: ");
                        int dia = leitorDates.nextInt();

                        System.out.print("Digite o mês: ");
                        int mes = leitorDates.nextInt();

                        System.out.print("Digite o ano: ");
                        int ano = leitorDates.nextInt();

                        LocalDate data = LocalDate.of(ano, mes, dia);
                        consulta.setDataConsulta(data);
                        break;
                    case 2:
                        System.out.println("Digite o novo status:");
                        String novostatus = scanner.nextLine();
                        scanner.nextLine();
                        consulta.setStatus(novostatus);
                        break;
                    case 3:
                        System.out.println("Digite o novo link:");
                        String novoLink = scanner.nextLine();
                        scanner.nextLine();
                        consulta.setLink(novoLink);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        return;
                }

                dao.upDateConsultaOnline(consulta);
                System.out.println("Documento do paciente atualizado: " + consulta);
            }
        }

         class UpdateMedico {

            public void atualizarMedico() {
                Scanner scanner = new Scanner(System.in);

                MedicoDao dao = new MedicoDao();

                System.out.println("Digite o id do medico desejado: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Medico medico = dao.buscarPorIdMedico(id);
                if (medico == null) {
                    System.out.println("Médico não encontrado!");
                    return;
                }

                System.out.println("Qual campo deseja alterar?");
                System.out.println("1 - Especialidade");
                System.out.println("2 - CRM");
                int op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        System.out.println("Digite a nova especialidade:");
                        String novaEspecializacao = scanner.nextLine();
                        medico.setEspecialidade(novaEspecializacao);
                        break;
                    case 2:
                        System.out.println("Digite o novo CRM:");
                        int novoCRM= scanner.nextInt();
                        scanner.nextLine();
                        medico.setCrm(novoCRM);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        return;
                }

                dao.upDateMedico(medico);
                System.out.println("Documento do paciente atualizado: " + medico);
            }
        }

         class UpdateExame{

            public void atualizarExame() {
                Scanner scanner = new Scanner(System.in);

                ExameDao dao = new ExameDao();

                System.out.println("Digite o id do exame desejado: ");
                int id = scanner.nextInt();
                scanner.nextLine();



                Exame exame = dao.buscarPorIdExame(id);
                if (exame == null) {
                    System.out.println("Exame não encontrado!");
                    return;
                }

                System.out.println("Qual campo deseja alterar?");
                System.out.println("1 -  Nome do exame");
                System.out.println("2 -  Resultado do exame");
                int op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        System.out.println("Digite o novo nome:");
                        String novoNome = scanner.nextLine();
                        exame.setNome_exame(novoNome);
                        break;
                    case 2:
                        System.out.println("Digite novo resultado:");
                        String novoResultado= scanner.nextLine();
                        scanner.nextLine();
                        exame.setResultado_exame(novoResultado);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        return;
                }

                dao.upDateExame(exame);
                System.out.println("Exame do paciente atualizado: " + exame);
            }
        }

         class UpdateHistoricoconsultas {

            public void atualizarHistorico() {
                Scanner scanner = new Scanner(System.in);

                HistoricoConsultaDao dao = new HistoricoConsultaDao();

                System.out.println("Digite o id do Historico desejado: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                HistoricoConsulta historico = dao.buscarPorIdhistorico(id);
                if (historico == null) {
                    System.out.println("Consulta não encontrado!");
                    return;
                }

                System.out.println("Qual campo deseja alterar?");
                System.out.println("1 - Sintomas");
                System.out.println("2 - Diagnostico");
                System.out.println("2 - Observações");
                int op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        System.out.println("Digite o novo sintoma:");
                        String novoSintoma = scanner.nextLine();
                        historico.setSintomas_historico(novoSintoma);
                        break;
                    case 2:
                        System.out.println("Digite o novo diagnosticop:");
                        String novoDiagnosticop= scanner.nextLine();
                        scanner.nextLine();
                        historico.setDiagnostico(novoDiagnosticop);
                        break;
                    case 3:
                        System.out.println("Digite as novas observações:");
                        String novoObservacao= scanner.nextLine();
                        scanner.nextLine();
                        historico.setObservacao(novoObservacao);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        return;
                }

                dao.upDateHistorico(historico);
                System.out.println("Documento do paciente atualizado: " + historico);
            }
        }

//      ------------------- Classes para exclusão de dados------------------------
        class ExcluirPacienteData{
            public void excluir(){
                Scanner leitor = new Scanner(System.in);
                PacienteDao dao = new PacienteDao();

                System.out.println("Digite o id do Paciente: ");
                int id = leitor.nextInt();
                dao.excluirPaciente(id);
                System.out.println("Registro excluído com sucesso!");
            }
        }

        class ExcluirconsultaData{
            public void excluir(){
                Scanner leitor = new Scanner(System.in);
                ConsultaOnlineDao dao = new ConsultaOnlineDao();

                System.out.println("Digite o id do agendamento da consulta: ");
                int id = leitor.nextInt();
                dao.excluirConsultaOnline(id);
                System.out.println("Registro excluído com sucesso!");
            }
        }

        class ExcluirMedicoData{
            public void excluir(){
                Scanner leitor = new Scanner(System.in);
                MedicoDao dao = new MedicoDao();

                System.out.println("Digite o id do médico: ");
                int id = leitor.nextInt();
                dao.excluirMedico(id);
                System.out.println("Registro excluído com sucesso!");
            }
        }

        class ExcluirExame{
            public void excluir(){
                Scanner leitor = new Scanner(System.in);
                ExameDao dao = new ExameDao();

                System.out.println("Digite o id do exame: ");
                int id = leitor.nextInt();
                dao.excluirExame(id);
                System.out.println("Registro excluído com sucesso!");
            }
        }

        class ExcluirHistoricoConsultaData{
            public void excluir(){
                Scanner leitor = new Scanner(System.in);
                HistoricoConsultaDao dao = new HistoricoConsultaDao();

                System.out.println("Digite o id do historico: ");
                int id = leitor.nextInt();
                dao.excluiHistoricoConsulta(id);
                System.out.println("Registro excluído com sucesso!");
            }
        }

        do{
            menuCrud();
            opCrud = leitorNum.nextInt();

             switch (opCrud){
                 case 1:{
                    do {
                        menuCadastrar();
                        opCadastrar = leitorNum.nextInt();
                        switch (opCadastrar) {
                            case 1:
                                System.out.println("CADASTRAR PACIENTE" +
                                        "\n===============================================");
                                Paciente novoPaciente = new Paciente();
                                novoPaciente = leituraDados(novoPaciente);
                                if (!novoPaciente.isCpfValido()) {
                                    System.out.println("CPF inválido! O CPF deve ter exatamente 11 dígitos.");
                                } else {
                                    cadastros.cadastrarPacientefunc(novoPaciente);
                                    System.out.println("Paciente cadastrado com sucesso!");
                                }
                                break;

                            case 2:
                                System.out.println("CADASTRAR CONSULTA ONLINE" +
                                        "\n===============================================");
                                ConsultaOnline consultaOnline = new ConsultaOnline();
                                consultaOnlineDao.cadastrarConsultaOnline(leituraDadosConsultaOnline(consultaOnline));
                                System.out.println("Consulta cadastrado com sucesso!");
                                break;

                            case 3:
                                System.out.println("CADASTRAR MEDICO" +
                                        "\n===============================================");
                                Medico medico = new Medico();
                                cadastroMedicoDao.cadastrarMedico(leituraDadosMedicos(medico));
                                System.out.println("Médico cadastrado com sucesso!");
                                break;

                            case 4:
                                System.out.println("CADASTRAR EXAMES" +
                                        "\n===============================================");
                                Exame exame = new Exame();
                                exameDao.cadastrarExame(leituraExames(exame));
                                System.out.println("Exame cadastrado com sucesso!");
                                break;

                            case 5:
                                System.out.println("CADASTRAR HISTORICO DAS CONSULTAS" +
                                        "\n===============================================");
                                HistoricoConsulta historicoconsulta = new HistoricoConsulta();
                                historicoConsultaDao.cadastrarHistoricoConsulta(leituraDadoshistoricosconsultas(historicoconsulta));
                                System.out.println("Hitorico de consulta cadastrado com sucesso!");
                                break;

                        }
                    }while (opCadastrar != 0);
                }
                  break;

                 case 2:{
                     do {
                        menuListar();
                        opListar =leitorNum.nextInt();
                        switch (opListar){
                         case 1:
                             ListarPacientes listarPacientes = new ListarPacientes();
                             listarPacientes.imprimirPacientes();
                             break;
                         case 2:
                             ListarConsultasOnline listarConsulta = new ListarConsultasOnline();
                             listarConsulta.imprimirconsultas();
                             break;
                         case 3:
                             ListarMedicos listarMedicos = new ListarMedicos();
                             listarMedicos.imprimirMedicos();
                             break;
                         case 4:
                             ListarExames listarExames = new ListarExames();
                             listarExames.imprimirExames();
                             break;

                         case 5:
                             ListarhistoricosConsultas listarhistoricosConsultas = new ListarhistoricosConsultas();
                             listarhistoricosConsultas.imprimirHistoricos();
                             break;
                     }
                     }while(opListar != 0);
                 }
                 break;

                 case 3:{
                     do{
                     menuUpdate();
                     opUpDate = leitorNum.nextInt();
                     switch (opUpDate){
                         case 1:
                             UpdatePaciente updater = new UpdatePaciente();
                             updater.atualizarPaciente();
                             break;
                         case 2:
                             UpdateConsultaOnline consultaOnline = new UpdateConsultaOnline();
                             consultaOnline.atualizarConsultaOnline();
                             break;
                         case 3:
                             UpdateMedico updateMedico = new UpdateMedico();
                             updateMedico.atualizarMedico();
                             break;

                         case 4:
                             UpdateExame updateExame = new UpdateExame();
                             updateExame.atualizarExame();
                             break;
                         case 5:
                             UpdateHistoricoconsultas updateHistoricoconsultas = new UpdateHistoricoconsultas();
                             updateHistoricoconsultas.atualizarHistorico();
                             break;
                     };
                     }while(opUpDate !=0);
                 }
                 break;

                 case 4:{
                     do{
                     menuExcluir();
                     opExcluir = leitorNum.nextInt();
                     switch (opExcluir){
                         case 1:
                             ExcluirPacienteData excluirPacienteData = new ExcluirPacienteData();
                             excluirPacienteData.excluir();
                             break;

                         case 2:
                             ExcluirconsultaData excluirconsultaData = new ExcluirconsultaData();
                             excluirconsultaData.excluir();
                             break;
                         case 3:
                             ExcluirMedicoData excluirMedicoData = new ExcluirMedicoData();
                             excluirMedicoData.excluir();
                             break;
                         case 4:
                             ExcluirExame excluirExame = new ExcluirExame();
                             excluirExame.excluir();
                             break;
                         case 5:
                             ExcluirHistoricoConsultaData excluirHistoricoConsultaData= new ExcluirHistoricoConsultaData();
                             excluirHistoricoConsultaData.excluir();
                             break;
                     }
                     }while(opExcluir!=0);
                 }
                 break;

                 case 5:
                     mostrarFAQMenu();

            }
        }while(opCrud !=0);

    }
}

