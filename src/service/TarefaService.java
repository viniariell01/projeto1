package ucb.aplicacao.service;

import java.util.ArrayList;
import java.util.List;
import ucb.aplicacao.model.Tarefas;

public class TarefaService {
    private List<Tarefas> tarefas = new ArrayList<>();
    private Long contadorId = 1L;

    public Tarefas criarTarefa(String titulo, String descricao) {
        Tarefas t = new Tarefas(contadorId++, titulo, descricao, false);
        tarefas.add(t);
        return t;
    }

    public List<Tarefas> listarTarefas() {
        return tarefas;
    }

    public boolean atualizarTarefa(long id, String novoTitulo, String novaDesc, Boolean novoCompleto) {
        for (Tarefas t : tarefas) {
            if (t.getId() == id) {
                if (novoTitulo != null && !novoTitulo.isBlank()) t.setTitulo(novoTitulo);
                if (novaDesc   != null && !novaDesc.isBlank())   t.setDescricao(novaDesc);
                if (novoCompleto != null) t.setCompleta(novoCompleto);
                return true;
            }
        }
        return false;
    }