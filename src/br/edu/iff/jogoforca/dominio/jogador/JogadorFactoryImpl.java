package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.Repository;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {

    private static JogadorRepository jogadorRepository;
    private static JogadorFactoryImpl soleInstance;
    private Jogador jogador;


    public static void createSoleInstance(JogadorRepository repository) {

        if (soleInstance == null) {

            soleInstance = new JogadorFactoryImpl(repository);

        }
    }

    public static JogadorFactoryImpl getSoleInstance(){

        if(soleInstance != null) {

            return soleInstance;
        } else {

            throw new IllegalStateException("A fábrica de jogador não foi definida.");
        }

    }

    private JogadorFactoryImpl(JogadorRepository jogadorRepository) {
        super(jogadorRepository);

    }

    private JogadorRepository getJogadorRepository() {
        return jogadorRepository;
    }

    public Jogador getJogador(String nome) {

        return jogador.criar(getProximoId(), nome);
    }


}
