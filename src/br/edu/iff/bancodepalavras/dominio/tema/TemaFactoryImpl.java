package br.edu.iff.bancodepalavras.dominio.tema;

public class TemaFactoryImpl implements TemaFactory{

    private static TemaFactoryImpl soleInstance;
    private static TemaRepository temaRepository;
    private Tema tema;

    public static void createSoleInstance(TemaRepository repository) {

        if(soleInstance==null) {
            soleInstance = new TemaFactoryImpl(repository);
        }
    }

    public static TemaFactoryImpl getSoleInstance(){

        if(soleInstance!=null) {
            return soleInstance;
        }
        throw new IllegalStateException("A fábrica de tema não foi definida.");

    }

    private TemaFactoryImpl(TemaRepository temaRepository) {

        this.temaRepository = temaRepository;

    }

    private TemaRepository getTemaRepository(){

        return temaRepository; // retorna
    }

    public Tema getTema(String nome) {

        return tema.criar(temaRepository.getProximoId(), nome);
    }
}
