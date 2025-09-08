package br.com.fiap.testes;
import br.com.fiap.dao.CadastrarDao;
import br.com.fiap.models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Insercao {

    public static void menuCrud() {
        System.out.println("Hc Hospita Crudl");
        System.out.println("=======================================");
        System.out.println("1 -  Menu Cadastrar");
        System.out.println("2 -  Menu Listar");
        System.out.println("3 -  Menu Update Paciente");
        System.out.println("7 -  Finalizar");


    }

    public static void menuCadastrar() {
        System.out.println("-----Hc Hospital - Menu Cadastrar-------");
        System.out.println("1  -  Cadastrar pacientes");
        System.out.println("2  -  Cadastrar Documentos");
        System.out.println("3  -  Cadastrar Consultas Online");
        System.out.println("4  -  Cadastrar Médicos");
        System.out.println("5  -  Cadastrar Confirmaçnao das Consultas");
        System.out.println("6  -  Cadastrar Historico das consultas");
        System.out.println("7  -  Finalizar");


    }

    public static void menuListar() {
        System.out.println("-------Hc Hospital- Menu Listar-------");
        System.out.println("1 - Listar pacientes");
        System.out.println("2 -  Listar Documentos");
        System.out.println("3 -  Listar Consultas online");
        System.out.println("4 -  Listar  Médicos");
        System.out.println("5 -  Listar  Confirmação de Consultas");
        System.out.println("6 -  Listar  Historico das Consultas");

        System.out.println("7 - Finalizar");


    }

    public static void menuUpdate(){
        System.out.println("-------Hc Hospital- Menu Listar-------");
        System.out.println("1  -  UpDate pacientes");
        System.out.println("2  -  UpDate Documentos");
        System.out.println("3  -  UpDate Consultas online");
        System.out.println("4  -  UpDate  Médicos");
        System.out.println("5  -  UpDate  Confirmação de Consultas");
        System.out.println("6  -  UpDate  Historico das Consultas");

        System.out.println("7 - Finalizar");
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


    public static DoumentoPaciente leituraDadosDocumnetos(DoumentoPaciente documentos){
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Id do documento: ");
        documentos.setId_documneto(leitor.nextInt());

        System.out.println("Tipo de documento do paciente: ");
        documentos.setType_documento(leitorNum.nextLine());

        System.out.println("Documneto validado, 1 para ele é valido 0 (zero) não valido: ");
        documentos.setValido(leitorNum.nextInt());

        return documentos;
    };

    public static ConsultaOnline leituraDadosConsultaOnline(ConsultaOnline consultaOnline){
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);
        Scanner leitorDates = new Scanner(System.in);

        System.out.println("Id da consulta: ");
        consultaOnline.setId_consulta(leitorNum.nextInt());

        System.out.print("Digite o dia: ");
        int dia = leitorDates.nextInt();

        System.out.print("Digite o mês: ");
        int mes = leitorDates.nextInt();

        System.out.print("Digite o ano: ");
        int ano = leitorDates.nextInt();
        LocalDate data = LocalDate.of(ano, mes, dia);
        consultaOnline.setDataConsulta(data);



        System.out.println("status da consulta: ");
        consultaOnline.setStatus(leitor.nextLine());

        System.out.println("Link da consulta: ");
        consultaOnline.setLink(leitor.nextLine());

       return consultaOnline;
    };


    public static Medico leituraDadosMedicos(Medico medico){
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Id do medico: ");
        medico.setId_medico(leitorNum.nextInt());

        System.out.println("Especialidade do médico: ");
        medico.setEspecialidade(leitor.nextLine());

        System.out.println("CRM do médico: ");
        medico.setCrm(leitorNum.nextInt());

        return medico;
    };

    public static ConfirmacaoConsulta leituraDadosConfirmacaoConsulta(ConfirmacaoConsulta confirmacaoConsulta){
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Id da confirmação: ");
        confirmacaoConsulta.setId_confirmacao(leitorNum.nextInt());

        System.out.println("Codigo de confirmação: ");
        confirmacaoConsulta.setCodigoConfirmacao(leitorNum.nextInt());

        System.out.println("Metodo de confirmação: ");
        confirmacaoConsulta.setMetodoconfirmacao(leitor.nextLine());

        return confirmacaoConsulta;
    };

    public static Historicoconsulta leituraDadoshistoricosconsultas(Historicoconsulta historicoconsulta){
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Id do Historico: ");
        historicoconsulta.setId_historico(leitorNum.nextInt());

        System.out.println("Sintomas: ");
        historicoconsulta.setSintomas_historico(leitor.nextLine());

        System.out.println("Diagnostico: ");
        historicoconsulta.setDiagnostico(leitor.nextLine());

        System.out.println("Observações: ");
        historicoconsulta.setObservacao(leitor.nextLine());

        return historicoconsulta;
    };


    public static void main(String[] args) {

        Scanner leitorNum = new Scanner(System.in);
        CadastrarDao cadastros = new CadastrarDao();

        int opCrud;
        int opCadastrar;
        int opListar;
        int opUpDate;

//        ------------------- Classes para Listar  informação------------------------

        class ListarPacientes {
            private List<Paciente> pacientesList;

            public ListarPacientes() {
                CadastrarDao dao = new CadastrarDao();
                this.pacientesList = dao.listarPacientes();
            }

            public void imprimirPacientes() {
                for (Paciente p : pacientesList) {
                    System.out.println(p);
                }
            }
        }

        class ListarDocumentos {
            private List<DoumentoPaciente> documentosList;

            public ListarDocumentos() {
                CadastrarDao dao = new CadastrarDao();
                this.documentosList = dao.ListarDocumentos();
            }

            public void imprimirDocs() {
                for (DoumentoPaciente p : documentosList) {
                    System.out.println(p);
                }
            }
        }

        class ListarConsultasOnline {
            private List<ConsultaOnline> consultaOnlinesList;

            public ListarConsultasOnline() {
                CadastrarDao dao = new CadastrarDao();
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
                CadastrarDao dao = new CadastrarDao();
                this.medicosList = dao.ListarMedicos();
            }

            public void imprimirMedicos() {
                for (Medico p : medicosList) {
                    System.out.println(p);
                }
            }
        }

        class ListarConfirmacaoDeconsulta {
            private List<ConfirmacaoConsulta> confirmacaoConsultas;

            public ListarConfirmacaoDeconsulta() {
                CadastrarDao dao = new CadastrarDao();
                this.confirmacaoConsultas = dao.ListarConfirmacaoDeconsulta();
            }

            public void imprimirConfirmacao() {
                for (ConfirmacaoConsulta p : confirmacaoConsultas) {
                    System.out.println(p);
                }
            }
        }

        class ListarhistoricosConsultas {
            private List<Historicoconsulta> historicoconsultas;

            public ListarhistoricosConsultas() {
                CadastrarDao dao = new CadastrarDao();
                this.historicoconsultas = dao.ListarhistoricosConsultas();
            }

            public void imprimirHistoricos() {
                for (Historicoconsulta p : historicoconsultas) {
                    System.out.println(p);
                }
            }
        }

//       ------------------- Classes para upDate de informação------------------------
         class UpdatePaciente {

            public void atualizarPaciente() {
                Scanner scanner = new Scanner(System.in);

                CadastrarDao dao = new CadastrarDao();

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
                        int novoCpf = scanner.nextInt();
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

         class UpdateDocPaciente {

            public void atualizarDocPaciente() {
                Scanner scanner = new Scanner(System.in);

                CadastrarDao dao = new CadastrarDao();

                System.out.println("Digite o id do documento desejado: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                DoumentoPaciente doc = dao.buscarPorIdDocPaciente(id);
                if (doc == null) {
                    System.out.println("Paciente não encontrado!");
                    return;
                }

                System.out.println("Qual campo deseja alterar?");
                System.out.println("1 - Valido");
                System.out.println("2 - tipo de doc");
                int op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        System.out.println("Digite a nova validação:");
                        String novoValidacao = scanner.nextLine();
                        doc.setType_documento(novoValidacao);
                        break;
                    case 2:
                        System.out.println("Digite o novo tipo:");
                        String novoTipo = scanner.nextLine();
                        scanner.nextLine();
                        doc.setType_documento(novoTipo);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        return;
                }

                dao.upDateDocPaciente(doc);
                System.out.println("Documento do paciente atualizado: " + doc);
            }
        }

        class UpdateConsultaOnline {

            public void atualizarConsultaOnline() {
                Scanner scanner = new Scanner(System.in);

                CadastrarDao dao = new CadastrarDao();

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


        do{
            menuCrud();
            opCrud = leitorNum.nextInt();
             switch (opCrud){
                 case 1:{
                    do {
                        menuCadastrar();
                        opCadastrar = leitorNum.nextInt();
                        switch (opCadastrar) {
                            case 1: {
                                System.out.println("CADASTRAR PACIENTE" +
                                        "\n===============================================");
                                Paciente novoPaciente = new Paciente();
                                cadastros.cadastrarPacientefunc(leituraDados(novoPaciente));
                                System.out.println("Paciente cadastrado com sucesso!");
                            }break;
                            case 2: {
                                System.out.println("CADASTRAR DOCUMENTAÇAO DO PACIENTE" +
                                        "\n===============================================");
                                DoumentoPaciente documentoPaciente = new DoumentoPaciente();
                                cadastros.cadastrarDocumentoPaciente(leituraDadosDocumnetos(documentoPaciente));
                                System.out.println("Documento cadastrado com sucesso!");
                            }break;

                            case 3: {
                                System.out.println("CADASTRAR CONSULTA ONLINE" +
                                        "\n===============================================");
                                ConsultaOnline consultaOnline = new ConsultaOnline();
                                cadastros.cadastrarConsultaOnline(leituraDadosConsultaOnline(consultaOnline));


                                System.out.println("Consulta cadastrado com sucesso!");
                            }break;

                            case 4: {
                                System.out.println("CADASTRAR MEDICO" +
                                        "\n===============================================");
                                Medico medico = new Medico();
                                cadastros.cadastrarMedico(leituraDadosMedicos(medico));


                                System.out.println("Médico cadastrado com sucesso!");
                            }break;

                            case 5: {
                                System.out.println("CADASTRAR CONFIRMAÇAO DAS CONSULTAS" +
                                        "\n===============================================");
                                ConfirmacaoConsulta confirmacaoConsulta = new ConfirmacaoConsulta();
                                cadastros.cadastrarConfirmacaoConsulta(leituraDadosConfirmacaoConsulta(confirmacaoConsulta));


                                System.out.println("Médico cadastrado com sucesso!");
                            }break;

                            case 6: {
                                System.out.println("CADASTRAR HISTORICO DAS CONSULTAS" +
                                        "\n===============================================");
                                Historicoconsulta historicoconsulta = new Historicoconsulta();
                                cadastros.cadastrarHistoricoConsulta(leituraDadoshistoricosconsultas(historicoconsulta));


                                System.out.println("Médico cadastrado com sucesso!");
                            }break;

                        }
                    }while (opCadastrar != 7);
                }
                  break;

                 case 2:{
                     menuListar();
                     opListar =leitorNum.nextInt();
                     switch (opListar){
                         case 1:
                             ListarPacientes listaPacients = new ListarPacientes();
                             listaPacients.imprimirPacientes();
                             break;
                         case 2:
                             ListarDocumentos listaDocs = new ListarDocumentos();
                             listaDocs.imprimirDocs();
                             break;
                         case 3:
                             ListarConsultasOnline listarConsulta = new ListarConsultasOnline();
                             listarConsulta.imprimirconsultas();
                             break;
                         case 4:
                             ListarMedicos listarMedicos = new ListarMedicos();
                             listarMedicos.imprimirMedicos();
                             break;
                         case 5:
                             ListarConfirmacaoDeconsulta listarConfirmacaoDeconsulta = new ListarConfirmacaoDeconsulta();
                             listarConfirmacaoDeconsulta.imprimirConfirmacao();
                             break;

                         case 6:
                             ListarhistoricosConsultas listarhistoricosConsultas = new ListarhistoricosConsultas();
                             listarhistoricosConsultas.imprimirHistoricos();
                             break;
                     }
                 }

                 case 3:{
                     menuUpdate();
                     opUpDate = leitorNum.nextInt();
                     switch (opUpDate){
                         case 1:
                             UpdatePaciente updater = new UpdatePaciente();
                             updater.atualizarPaciente();
                             break;
                         case 2:
                             UpdateDocPaciente updateDocPaciente = new UpdateDocPaciente();
                             updateDocPaciente.atualizarDocPaciente();
                             break;
                         case 3:
                             UpdateConsultaOnline consultaOnline = new UpdateConsultaOnline();
                             consultaOnline.atualizarConsultaOnline();
                             break;
                     };
                 }

            }
        }while(opCrud !=7);


    }
}

