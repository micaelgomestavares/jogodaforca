package br.edu.iff.jogoforca.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.emmemoria.MemoriaTemaRepository;
import br.edu.iff.jogoforca.RepositoryFactory;
import br.edu.iff.jogoforca.dominio.jogador.emmemoria.MemoriaJogadorRepository;

public class MemoriaRepositoryFactory implements RepositoryFactory {

	private static MemoriaRepositoryFactory soleInstance;

	public static MemoriaRepositoryFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new MemoriaRepositoryFactory();
		}

		return soleInstance;
	}

	private MemoriaRepositoryFactory() {

	}

	@Override
	public PalavraRepository getPalavraRepository() {
		return MemoriaPalavraRepository.getSoleInstance();
	}

	@Override
	public TemaRepository getTemaRepository() {
		return MemoriaTemaRepository.getSoleInstance();
	}

	@Override
	public RodadaRepository getRodadaRepository() {
		return MemoriaRodadaRepository.getSoleInstance();
	}

	@Override
	public JogadorRepository getJogadorRepository() {

		return MemoriaJogadorRepository.getSoleInstance();
	}

}