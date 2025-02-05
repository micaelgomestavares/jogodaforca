package br.edu.iff.jogoforca.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.texto.LetraTextoFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.texto.BonecoTextoFactory;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory {

    private LetraTextoFactory letraTextoFactory;
    private BonecoTextoFactory bonecoTextoFactory;

    private static ElementoGraficoTextoFactory soleInstance;

    public static ElementoGraficoTextoFactory getSoleInstance() {
        if(soleInstance==null) {
            soleInstance = new ElementoGraficoTextoFactory();
        }
        return soleInstance;
    }

    private ElementoGraficoTextoFactory() {
        this.letraTextoFactory = LetraTextoFactory.getSoleInstance();
        this.bonecoTextoFactory = BonecoTextoFactory.getSoleInstance();
    }

    @Override
    public Boneco getBoneco() {
        return this.bonecoTextoFactory.getBoneco();
    }

    @Override
    public Letra getLetra(char codigo) {
        return this.letraTextoFactory.getLetra(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return this.letraTextoFactory.getLetraEncoberta();
    }

}