package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;


public class MemoriaTemaRepository implements TemaRepository {

    private static MemoriaTemaRepository soleInstance;
    private List<Tema> poolTema;

    private MemoriaTemaRepository() {
        poolTema = new ArrayList<>();
    }

    public static MemoriaTemaRepository getSoleInstance() {

        if (soleInstance == null) {

            soleInstance = new MemoriaTemaRepository();
        }

        return soleInstance;
    }


    @Override
    public Tema getPorId(long id) {

        for(Tema tema : poolTema){

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
        return poolTema.toArray(new Tema[0]);
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {// erro aqui, diz que metodos overridem não lançam exceção

        if(poolTema.contains(tema)) {
            throw new RepositoryException();
        } else {
            poolTema.add(tema);
        }
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
        int i = poolTema.indexOf(tema);
        if(i != -1){//se tema na lista
            poolTema.set(i, tema); //atualiza o objeto
        }
        else{
            throw new RepositoryException();
        }
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        int i = poolTema.indexOf(tema);

        if(i == -1){
            throw new RepositoryException();
        }
        poolTema.remove(i);
    }

    @Override
    public long getProximoId() {
        return poolTema.size() + 1;
    }
}
