package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogodaforca.dominio.palavra.Palavra;

public abstract class Item extends ObjetoDominioImpl {

	private Palavra palavra;

	private boolean[] posicoesDescobertas;

	private String palavraArriscada = null;

	static Item criar(long id, Palavra palavra) {
		return new Item(id, palavra);
	}

	public static Item reconstruir(long id, Palavra palavra, posicoesDescobertas boolean[], palavraArriscada String) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

	public Item(long id, Palavra palavra) {
		super(id);
		this.palavra = palavra;
		this.posicoesDescobertas = new boolean[palavra.getTamanho()];
		for (int i = 0; i < palavra.getTamanho(); i++) {
			this.posicoesDescobertas[i] = false;
		}
	}

	public Item(long id, Palavra palavra, posicoesDescobertas boolean[], palavraArriscada String){
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = posicoesDescobertas;
        this.palavraArriscada = palavraArriscada;
    }

	public Palavra getPalavra() {
		return palavra;
	}

	public Letra[] getLetrasEncobertas() {

	}

	public Letra[] getLetrasDescobertas() {

	}

	public int qtdeLetrasEncobertas() {
		int contador = 0;

		for (boolean posicao : posicoesDescobertas) {
			if (!posicao) {
				contador++;
			}
		}
		return contador;
	}

	public calcularPontosLetrasEncobertas(){

    }

	public boolean descobriu(){
        return qtdeLetras
    }

	public exibir(Object contexto){
        palavra.exibir(contexto, posicoesDescobertas);
    }

	boolean tentar() {

	}

	void arriscar() {
		palavraArriscada = palavra;
	}

	public String getPalavraArriscada() {
		return palavraArriscada;
	}

	public boolean arriscou() {
		return palavraArriscada != null;
	}

	public boolean acertou() {
		return palavraArriscada.equals(palavra.toString());
	}
}