package br.com.fiap.models;



import br.com.fiap.enums.PerguntasEnum;

import java.util.Scanner;

public class Perguntas {

    public static void mostrarPerguntaEResposta(Scanner scanner) {
        PerguntasEnum.mostrarPerguntas();
        PerguntasEnum perguntaSelecionada = PerguntasEnum.selecionarPergunta(scanner);

        System.out.println("\n=== PERGUNTA SELECIONADA ===");
        assert perguntaSelecionada != null;
        System.out.println("Pergunta: " + perguntaSelecionada.getDescricao());
        System.out.println("Resposta: " + perguntaSelecionada.getResposta());
    }
}