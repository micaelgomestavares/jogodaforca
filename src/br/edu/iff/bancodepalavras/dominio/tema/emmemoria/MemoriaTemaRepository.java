package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;


public class MemoriaTemaRepository implements TemaRepository {

    private static MemoriaTemaRepository soleInstance;
    private List<Tema> pool;

    private MemoriaTemaRepository() {
        pool = new ArrayList<>();
    }

    public static MemoriaTemaRepository getSoleInstance() {

        if (soleInstance == null) {

            return new MemoriaTemaRepository();
        }

        return soleInstance;
    }


    @Override
    public Tema getPorId(long id) {

        for(Tema tema : pool){

            if (tema.getId() == id) {

                return tema;
            }

        }
        //percorrer arrya de temas, comparando os ids com o parametro
        return null;
    }

    @Override
    public Tema[] getPorNome(String nome) {

        //retorna uma Array? existe mais de um tema com o mesmo nome??
        return new Tema[0];
    }

    @Override
    public Tema[] getTodos() {
        //transformar o pool em array, o proprio metodo ajusta o tamanho
        return this.pool.toArray(new Tema[0]);
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {// erro aqui, diz que metodos overridem não lançam exceção

        if(this.pool.contains(tema)) {
            throw new RepositoryException();
        }

        this.pool.add(tema);
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
        int i = pool.indexOf(tema);
        if(i != -1){//se tema na lista
            pool.set(i, tema); //atualiza o objeto
        }
        else{
            throw new RepositoryException();
        }
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        int i = pool.indexOf(tema);

        if(i == -1){
            throw new RepositoryException();
        }
        pool.remove(i);
    }
}
