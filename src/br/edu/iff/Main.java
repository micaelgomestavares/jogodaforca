package br.edu.iff;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.Aplicacao;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.repository.RepositoryException;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Aplicacao aplicacao = Aplicacao.getSoleInstance();

    public static void main(String[] args) {
        aplicacao.configurar();

        PalavraAppService palavraAppService = PalavraAppService.getSoleInstance();

        TemaRepository temaRepository = aplicacao.getRepositoryFactory().getTemaRepository();
        TemaFactory temaFactory = aplicacao.getTemaFactory();

        while (true) {
            System.out.print("Deseja cadastrar um novo tema? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("s")) {
                System.out.print("Digite o nome do tema que você quer cadastrar: ");
                String nomeTema = scanner.nextLine();

                Tema tema = temaFactory.getTema(nomeTema);

                try {
                    temaRepository.inserir(tema);
                } catch (RepositoryException e) {
                    System.out.println("Erro ao inserir o tema: " + e.getMessage());
                    continue;
                }

                Long temaId = tema.getId();

                System.out.print("Quantas palavras você quer adicionar para o tema '" + nomeTema + "'? ");
                int numPalavras = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < numPalavras; i++) {
                    System.out.print("Digite a palavra: ");
                    String palavra = scanner.nextLine();

                    try {
                        palavraAppService.novaPalavra(palavra, temaId);
                    } catch (Exception e) {
                        System.out.println("Erro ao adicionar a palavra: " + e.getMessage());
                    }
                }

            } else if (resposta.equals("n")) {
                break;
            } else {
                System.out.println("Resposta inválida. Por favor, digite 's' para sim ou 'n' para não.");
            }
        }

        System.out.print("Deseja iniciar a partida? (s/n): ");
        String iniciarPartida = scanner.nextLine().trim().toLowerCase();

        if (iniciarPartida.equals("s")) {
            System.out.print("Digite seu nome: ");
            String nomeJogador = scanner.nextLine();

            Jogador jogador = aplicacao.getJogadorFactory().getJogador(nomeJogador);

            try {
                aplicacao.getRepositoryFactory().getJogadorRepository().inserir(jogador);
            } catch (RepositoryException e) {
                throw new RuntimeException("Erro ao inserir o jogador: " + e.getMessage());
            }

            jogarRodada(jogador);
        } else {
            System.out.println("Encerrando o jogo. Até a próxima!");
        }
    }

    private static void jogarRodada(Jogador jogador) {
        RodadaAppService rodadaAppService = RodadaAppService.getSoleInstance();

        Rodada rodada = rodadaAppService.novaRodada(jogador);
        System.out.println("Tema das palavras: " + rodada.getTema().getNome());

        do {
            System.out.println("Tentativas restantes: " + rodada.getQtdeTentativasRestantes());
            System.out.print("Tentativas anteriores: ");

            for (Letra letraTentativa : rodada.getTentativas()) {
                letraTentativa.exibir(null);
                System.out.print(" ");
            }

            System.out.println("\nPalavra atual: ");
            rodada.exibirItens(null);

            System.out.println("\nCorpo: ");
            rodada.exibirBoneco(null);
            System.out.println();

            System.out.println("(1) Tentar letra");
            System.out.println("(2) Arriscar");
            System.out.print("Escolha uma opção: ");

            String escolha = scanner.next();

            switch (escolha) {
                case "1":
                    System.out.print("Digite a letra: ");
                    rodada.tentar(scanner.next().charAt(0));
                    break;
                case "2":
                    String[] palavrasArriscadas = new String[rodada.getNumPalavras()];
                    scanner.nextLine();
                    for (int i = 0; i < palavrasArriscadas.length; i++) {
                        System.out.print("Chute a palavra " + (i + 1) + ": ");
                        palavrasArriscadas[i] = scanner.nextLine();
                    }
                    rodada.arriscar(palavrasArriscadas);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            if (rodada.descobriu()) {
                System.out.println("Parabéns! Você descobriu!");
                break;
            }

        } while (!rodada.encerrou());

        if (!rodada.descobriu()) {
            System.out.println("Que pena! Você não conseguiu descobrir as palavras.");
        }
    }
}
