package br.edu.iff.testes;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;

public class LetraSimples extends Letra {

    public LetraSimples(char codigo) {
        super(codigo);
    }

    @Override
    public void exibir(Object contexto) {
        System.out.print(this.getCodigo());
    }
}