package br.edu.iff.jogodaforca.dominio.boneco.texto;

import br.edu.iff.jogodaforca.dominio.boneco.Boneco;
import br.edu.iff.jogodaforca.dominio.boneco.BonecoFactory;

public class BonecoTextoFactory implements BonecoFactory {
	private static BonecoTextoFactory soleInstance;

	public static BonecoTextoFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BonecoTextoFactory();
			return soleInstance;
		} else {
			return soleInstance;
		}
	}

	private BonecoTextoFactory() {
	}

	@Override
	public Boneco getBoneco() {
		return BonecoTexto.getSoleInstance();
	}

}