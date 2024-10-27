package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;


public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {
    
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
    public Palavra criarPalavra(String palavra, Tema tema) {
        
        return Palavra.criar(getProximoId(), palavra, tema);  
    }  
}
