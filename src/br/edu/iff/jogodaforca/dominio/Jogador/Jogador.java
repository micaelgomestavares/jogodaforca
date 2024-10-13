package br.edu.iff.jogodaforca.dominio.jogador;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;

public class Jogador {

    private String nome;
    private int pontuacao;

    public static Jogador criar(long id, String nome) {

        return new Tema(id, nome);
    }

    public static Jogador reconstituir(long id, String nome, int pontuacao) {

        return new Jogador(id, nome, pontuacao);
    }


    private Jogador(int id, String nome) {

        super(id);
        this.nome = nome;
    }

    private Jogador(int id, String nome, int pontuacao) {

        super(id);
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void atualizarPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
