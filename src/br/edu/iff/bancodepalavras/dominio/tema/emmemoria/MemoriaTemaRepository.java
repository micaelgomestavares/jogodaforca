package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MemoriaTemaRepository implements TemaRepository {

    private static MemoriaTemaRepository soleInstance;
    private List<Tema> poolTema;
    private Tema tema;

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
        //percorrer poolTema comparando o valor do id
        return null;
    }

    @Override
    public Tema[] getPorNome(String nome) {
        List<Tema> temas = new ArrayList<>();

        for(Tema tema : poolTema){
            if(tema.getNome().equals(nome)){

                temas.add(tema);
            }
        }
        return temas.toArray(new Tema[0]);
    }

    @Override
    public Tema[] getTodos() {
        //transformar o pool em array, o proprio metodo ajusta o tamanho
        return poolTema.toArray(new Tema[0]);
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {// erro aqui, diz que metodos overridem não lançam exceção

        poolTema.add(tema);

    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
        int i = poolTema.indexOf(tema);

        poolTema.set(i, tema); //atualiza o objeto

    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        int i = poolTema.indexOf(tema);

        poolTema.remove(i);
    }

    @Override
    public long getProximoId() {

        return poolTema.size() + 1; //proximo id disponivel
    }
}
