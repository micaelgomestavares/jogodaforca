package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;

public class PalavraFactoryImpl implements PalavraFactory extends EntityFactory{
    
    private static PalavraFactoryImpl soleInstance;


    private PalavraFactoryImpl(PalavraRepository repository) {
        
        super(repository);
    }


    public static void createSoleInstance(PalavraRepository repository) {
      
        soleInstance = new PalavraFactoryImpl(repository);
    }

    public static PalavraFactoryImpl getSoleInstance() {

        if(soleInstance == null) {

            throw new IllegalStateException("A instância de PalavraFactoryImpl não foi criada ainda.");
        }

        return soleInstance;
    }

    private PalavraRepository getPalavraRepository(){
            
        return (PalavraRepository) getRepository();
    }


    @Override
    public Palavra getPalavra(String palavra, Tema tema) {
        
        Palavra novaPalavra = Palavra.criar(getProximoId(),palavra, tema);

        try {

            getPalavraRepository().inserir(novaPalavra);
            
            return novaPalavra;

        } catch (RepositoryException e) {
            
            throw new IllegalStateException("Erro ao inserir a palavra no repositório.", e);
        }       
    }  
}
