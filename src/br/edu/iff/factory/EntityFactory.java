package br.edu.iff.factory;

import br.edu.iff.repository.Repository;

public abstract class EntityFactory {

    protected EntityFactory(Repository repository){}

    protected Repository getRepository(){
        return null;
    }

    protected long getProximoId() {
        return 0;
    }
}
