package br.edu.iff.jogoforca.embdr;

import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.RepositoryFactory;

public class BDRRepositoryFactory implements RepositoryFactory {

	private static BDRRepositoryFactory soleInstance;

	public static BDRRepositoryFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BDRRepositoryFactory();
		}
		return soleInstance;
	}

	private BDRRepositoryFactory() {

	}

	@Override
	public PalavraRepository getPalavraRepository() {
		return BDRPalavraRepository.getSoleInstance();
	}

	@Override
	public TemaRepository getTemaRepository() {
		return BDRTemaRepository.getSoleInstance();
	}

	@Override
	public RodadaRepository getRodadaRepository() {
		return BDRRodadaRepository.getSoleInstance();
	}

	@Override
	public JogadorRepository getJogadorRepository() {
		return BDRJogadorRepository.getSoleInstance();
	}

}