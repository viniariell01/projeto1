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

    public boolean removerTarefa(long id) {
        boolean removido = false;
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).getId() == id) {
                tarefas.remove(i);
                removido = true;
                break;
            }
        }
        if (!removido) return false;

        long novoId = 1L;
        for (Tarefas t : tarefas) {
            t.setId(novoId++);
        }
        contadorId = novoId;
        return true;
    }


    public List<Tarefas> pesquisarPorTitulo(String termo) {
        List<Tarefas> resultado = new ArrayList<>();
        String q = (termo == null) ? "" : termo.toLowerCase();
        for (Tarefas t : tarefas) {
            String titulo = t.getTitulo();
            if (titulo != null && titulo.toLowerCase().contains(q)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Tarefas> listarConcluidas() {
        List<Tarefas> resultado = new ArrayList<>();
        for (Tarefas t : tarefas) {
            if (t.isCompleta()) resultado.add(t);
        }
        return resultado;
    }
}