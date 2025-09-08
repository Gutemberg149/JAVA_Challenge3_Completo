package br.com.fiap.enums;

import java.util.Scanner;

public enum PerguntasEnum {
    PERGUNTA_1("O que é o Hospital das Clinias?"),
    PERGUNTA_2("Como cadastrar senha?"),
    PERGUNTA_3("Como criar conta?"),
    PERGUNTA_4("Como acessar a conta?"),
    PERGUNTA_5("Como começar teleconsulta?"),
    PERGUNTA_6("Como entrar na teleconsulta?"),
    PERGUNTA_7("Como habilitar o microfone?"),
    PERGUNTA_8("Como habilitar a câmera?");

    private final String pergunta;

    PerguntasEnum(String pergunta) {
        this.pergunta = pergunta;
    }

    public static void mostrarPerguntas() {
    }

    public static PerguntasEnum selecionarPergunta(Scanner scanner) {
        return null;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getResposta() {
        switch (this) {
            case PERGUNTA_1:
                return "O Hospital das Clínicas é uma importante instituição de saúde universitária reconhecida por oferecer atendimento " +
                        "em uma ampla variedade de especialidades médicas. Além de atuar como um centro de referência para tratamentos complexos, " +
                        "também desempenha um papel fundamental na formação de estudantes de medicina e profissionais de saúde, promovendo " +
                        "atividades de ensino, pesquisa e assistência à comunidade. Sua infraestrutura moderna e equipe altamente qualificada " +
                        "garantem um atendimento de alta qualidade aos pacientes.";
            case PERGUNTA_2:
                return "Para realizar o cadastro de uma nova senha de acesso ao sistema, o usuário deve primeiro clicar no " +
                        "botão identificado como 'Cadastrar Senha' na tela inicial. Em seguida, será apresentado um passo a passo detalhado, " +
                        "no qual deverá informar seus dados pessoais, criar uma nova senha segura e seguir todas as etapas indicadas para concluir o " +
                        "processo de cadastro com sucesso. É importante manter suas informações atualizadas e escolher uma senha forte para garantir a " +
                        "segurança do seu acesso.";
            case PERGUNTA_3:
                return "Na etapa de 'Criar Conta', você deve preencher todos os campos solicitados com suas informações pessoais, como nome completo, " +
                        "CPF, data de nascimento, endereço e telefone. Após inserir esses dados, siga as instruções para confirmar seu cadastro, criando uma " +
                        "senha de acesso e aceitando os termos de uso do sistema. Essa etapa é essencial para garantir que sua conta seja vinculada corretamente " +
                        "aos seus dados e possa facilitar o acesso às funcionalidades disponíveis.";
            case PERGUNTA_4:
                return "Após realizar o cadastro e criar sua conta, você poderá acessar o sistema de login para pacientes. Para isso, digite seu CPF e a " +
                        "senha criada durante o cadastro. Após inserir esses dados corretamente, clique no botão 'Acessar'. Dessa forma, você poderá acessar suas informações de " +
                        "paciente, agendar consultas, consultar resultados e outros serviços disponíveis, garantindo uma experiência personalizada e segura.";
            case PERGUNTA_5:
                return "Depois de realizar o cadastro e acessar sua conta, você pode iniciar uma teleconsulta com seu médico. Para isso, digite " +
                        "seu CPF e senha na tela de login, clique em 'Acessar' e navegue até a opção de iniciar a teleconsulta. Quando estiver na sala virtual, você poderá se comunicar " +
                        "com o profissional de saúde de forma segura, por vídeo, e realizar o acompanhamento necessário sem sair de sua casa, facilitando o acesso ao atendimento médico.";
            case PERGUNTA_6:
                return "Ao clicar na opção de iniciar a teleconsulta, a agenda de compromissos será exibida na tela, onde você poderá visualizar" +
                        " suas próximas consultas agendadas. Para entrar na sessão virtual, clique no botão azul identificado como 'Entrar na Teleconsulta'. Assim, você será direcionado " +
                        "para a sala de vídeo, onde poderá se conectar com seu médico ou profissional de saúde para a consulta online, garantindo um atendimento eficiente e ágil.";
            case PERGUNTA_7:
                return "Para habilitar o microfone durante a teleconsulta, clique no ícone de microfone na interface da sala virtual. " +
                        "Após clicar, um teste de áudio será iniciado automaticamente, permitindo verificar se o som está funcionando corretamente. Essa etapa é importante para garantir " +
                        "uma comunicação clara entre você e o profissional de saúde, evitando problemas durante a consulta. Caso o teste não funcione, verifique a permissão do navegador " +
                        "ou configurações do dispositivo.";
            case PERGUNTA_8:
                return "Antes de iniciar a videoconferência, certifique-se de habilitar sua câmera para que o profissional de saúde possa vê-lo " +
                        "e realizar uma avaliação visual adequada. Para isso, clique na opção de ativar câmera na interface da sala virtual. Caso sua câmera não esteja ativada, a consulta " +
                        "poderá ocorrer apenas por áudio, o que pode limitar a avaliação clínica. Portanto, verifique se sua câmera está conectada corretamente e permissão concedida no navegador" +
                        " ou dispositivo.";
            default:
                return "Resposta não encontrada. Por favor, verifique a pergunta e tente novamente.";
        }
    }

    public String getDescricao() {
        return "";
    }

}
