package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemoriaRodadaRepository implements RodadaRepository {

    private MemoriaRodadaRepository soleInstance;
    private Map<Long, Rodada> rodadaPool;
    private AtomicLong idCounter;

    public MemoriaRodadaRepository getSoleInstance(){

        if(soleInstance == null){

            soleInstance = new MemoriaRodadaRepository();
        }

        return soleInstance;
    }

    private MemoriaRodadaRepository(){

        rodadaPool = new HashMap<>();
    }

    @Override
    public Rodada getPorId(long id) {

        return rodadaPool.get(id);
    } //retorna null se não encontrar

    @Override
    public Rodada[] getPorJogador(Jogador jogador) {

        List<Rodada> rodadasDoJogador = new ArrayList<>();

        for (Rodada rodada : rodadaPool.values()) {
            if (rodada.getJogador().equals(jogador)) {
                rodadasDoJogador.add(rodada);
            }
        }

        return rodadasDoJogador.toArray(new Rodada[0]);
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {

        rodadaPool.put(rodada.getId(), rodada);
    }//

    @Override
    public void atualizar(Rodada rodada) throws RepositoryException {

        rodadaPool.replace(rodada.getId(), rodada);
    } //replace retorna null caso a chave não exista

    @Override
    public void remover(Rodada rodada) throws RepositoryException {

        rodadaPool.remove(rodada.getId());
    }

    @Override
    public long getProximoId() {

        return idCounter.incrementAndGet();
    }
}
