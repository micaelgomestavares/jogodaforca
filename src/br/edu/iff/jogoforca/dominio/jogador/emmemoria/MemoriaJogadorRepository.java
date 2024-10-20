package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;

public class MemoriaJogadorRepository implements JogadorRepository {

    private List<Jogador> poolJogador;
    private static MemoriaJogadorRepository soleInstance;


    public static MemoriaJogadorRepository getSoleInstance(){

        if(soleInstance == null){

            soleInstance = new MemoriaJogadorRepository();
        }

        return soleInstance;
    }

    private MemoriaJogadorRepository() {

        poolJogador = new ArrayList<>();
    }

    @Override
    public Jogador getPorid(long id) {

        for (Jogador jogador : poolJogador){

            if(jogador.getId() == id){

                return jogador;
            }
        }
        return null;
    }

    @Override
    public Jogador getPorNome(String nome) {

        for (Jogador jogador : poolJogador){

            if(jogador.getNome().equals(nome)){

                return jogador;
            }
        }
        return null;
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {

        if (poolJogador.contains(jogador)) {

            throw new RepositoryException();
        }

        poolJogador.add(jogador);
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {

        int i = poolJogador.indexOf(jogador);

        if(i != -1){//se tema na lista
            poolJogador.set(i, jogador); //atualiza o objeto
        }
        else{
            throw new RepositoryException();
        }
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
        int i = poolJogador.indexOf(jogador);

        if(i == -1){
            throw new RepositoryException();
        }
        poolJogador.remove(i);
    }

    @Override
    public long getProximoId() {
        return poolJogador.size() + 1;
    }
}
