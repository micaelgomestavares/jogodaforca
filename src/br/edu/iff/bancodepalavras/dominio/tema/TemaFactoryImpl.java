package br.edu.iff.bancodepalavras.dominio.tema;

public class TemaFactoryImpl implements TemaFactory{
    
    private static TemaFactoryImpl soleInstance;
    private TemaRepository repository;

    public void createSoleInstance(TemaRepository repository) {
     
        if(soleInstance==null) {
            soleInstance = new TemaFactoryImpl(repository);
        }
    }

    public static TemaFactoryImpl getSoleInstance(){
            
        if(soleInstance==null) {
            throw new IllegalStateException("A fábrica de temas não foi definida.");
        }

        return soleInstance;
    }

    private TemaFactoryImpl(TemaRepository repository) {
        
        this.repository = repository;

    }

    private TemaRepository getTemaRepository(){

        return repository;
    }

    public Tema getTema(String nome) {
        
        

    }
}
