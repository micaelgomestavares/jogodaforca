package br.edu.iff.bancodepalavras.dominio.letra;

public abstract class LetraFactoryImpl implements LetraFactory {

	private Letra[] pool;
	private Letra encoberta;
	private static final int NUMERO_DE_LETRAS = 26;

	protected LetraFactoryImpl() {
		this.pool = new Letra[NUMERO_DE_LETRAS];
		this.encoberta = null;
	}

	@Override
	public final Letra getLetra(char codigo) {
		if (!Character.isLowerCase(codigo)) {
			throw new IllegalArgumentException("Este caractere não é aceito. Use letras minúsculas.");
		}

		/*
		 * Serve para calcular o índice de uma letra minúscula (a até z) no alfabeto c/
		 * códigos ASCII, de forma que ela possa ser mapeada para uma posição específica
		 * no array Letra[] pool.
		 */
		int indiceLetra = codigo - 'a';

		if (pool[indiceLetra] == null) {
			pool[indiceLetra] = criarLetra(codigo);
		}

		return pool[indiceLetra];
	}

	@Override
	public final Letra getLetraEncoberta() {
		if (encoberta == null)
			this.encoberta = criarLetra('*');

		return this.encoberta;
	}

	protected abstract Letra criarLetra(char Codigo);

}