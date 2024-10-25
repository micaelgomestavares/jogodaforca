package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class PalavraAppService {
    
    private static PalavraAppService soleInstance;
    private PalavraFactory palavraFactory;
    private PalavraRepository palavraRepository;
    private TemaRepository temaRepository;


    private PalavraAppService(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory factory) {
        
        this.palavraFactory = factory;
        this.palavraRepository = palavraRepository;
        this.temaRepository = temaRepository;
    }


    public static void createSoleInstance(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory factory) {
      
        soleInstance = new PalavraAppService(temaRepository, palavraRepository, factory);
    }

    public static PalavraAppService getSoleInstance() {

        if(soleInstance == null) {

            throw new IllegalStateException("A instância de PalavraAppService não foi criada ainda.");
        }

        return soleInstance;
    }

    public boolean novaPalavra(String palavra, Long idTema) {
        
        if(temaRepository.getPorId(idTema) == null) {

            throw new RuntimeException("Tema não existe no repositório.");
        }

        if(palavraRepository.getPalavra(palavra) != null) {

            return true;
        }

        try {
            
            palavraRepository.inserir(palavraFactory.criarPalavra(palavra, temaRepository.getPorId(idTema)));
           
            return true;

        } catch (RepositoryException e) {
            
            System.out.println(e.getMessage() + "Erro ao inserir palavra.");

            return false;
        }
    }
}



