package br.edu.iff;
import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
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

        try {
            temaRepository.inserir(temaFactory.getTema("Carro"));
            temaRepository.inserir(temaFactory.getTema("Nome"));
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        palavraAppService.novaPalavra("fusca", (long)1);
        palavraAppService.novaPalavra("palio", (long)1);
        palavraAppService.novaPalavra("corsa", (long)1);
        palavraAppService.novaPalavra("felipe", (long)2);
        palavraAppService.novaPalavra("ana", (long)2);
        palavraAppService.novaPalavra("jorge", (long) 2);

        System.out.println("Digite seu nome: ");
        String nomeJogador = scanner.next();

        Jogador jogador = aplicacao.getJogadorFactory().getJogador(nomeJogador);

        try {
            aplicacao.getRepositoryFactory().getJogadorRepository().inserir(jogador);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        jogarRodada(jogador);
    }

    private static void jogarRodada(Jogador jogador) {
        RodadaAppService rodadaAppService = RodadaAppService.getSoleInstance();

        Rodada rodada = rodadaAppService.novaRodada(jogador);
        System.out.println("Tema das palavras: " + rodada.getTema());

        do {
            System.out.println("Tentativas restantes: " + rodada.getQtdeTentativasRestantes());
            System.out.println("Tentativas anteriores: ");

            for (Letra letraTentativa : rodada.getTentativas()) {
                letraTentativa.exibir(null);
                System.out.print(" ");
            }

            System.out.println("Palavras:");
            rodada.exibirItens(null);

            System.out.println();
            System.out.println("Corpo: ");

            rodada.exibirBoneco(null);
            System.out.println();


            System.out.println("(1) Tentar letra");
            System.out.println("(2) Arriscar");

            String escolha = scanner.next();

            switch (escolha){
                case "1":
                    System.out.print("Digite a letra: ");
                    rodada.tentar(scanner.next().charAt(0));
                    break;
                case "2":
                    String[] palavrasArriscadas = new String[rodada.getNumPalavras()];
                    for (int i = 0; i < palavrasArriscadas.length; i++) {
                        System.out.print("Chute a palavra " + (i + 1)  + " :");
                        palavrasArriscadas[i] = scanner.next();
                    }
                    rodada.arriscar(palavrasArriscadas);
                    break;
            }

            if (rodada.descobriu()) System.out.println("Descobriu!");


        }  while (!rodada.encerrou());
    }
}
