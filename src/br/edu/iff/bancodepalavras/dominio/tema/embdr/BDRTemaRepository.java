package br.edu.iff.bancodepalavras.dominio.tema.embdr;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class BDRTemaRepository implements TemaRepository {


    @Override
    public Tema getPorId(long id) {
        return null;
    }

    @Override
    public Tema[] getPorNome(String nome) {
        return new Tema[0];
    }

    @Override
    public Tema[] getTodos() {
        return new Tema[0];
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {

    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {

    }

    @Override
    public void remover(Tema tema) throws RepositoryException {

    }

    @Override
    public long getProximoId() {
        return 0;
    }
}
