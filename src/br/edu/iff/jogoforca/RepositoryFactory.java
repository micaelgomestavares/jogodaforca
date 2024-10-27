package br.edu.iff.jogoforca;

public interface RepositoryFactory {

	/*
	 * TODO: Importar as classes quando disponíveis.
	 */
	public PalavraRepository getPalavraRepository();

	public TemaRepository getTemaRepository();

	public RodadaRepository getRodadaRepository();

	public JogadorRepository getJogadorRepository();

}
