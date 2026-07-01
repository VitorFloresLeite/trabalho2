package Modelo;
import java.util.*;

public class Grade {
    private final Map<Dias, Queue<Alocacao>> alocacoesPorDia;
    private final ValidaAlocacaoStrategy validador;

    public Grade() {
        this.alocacoesPorDia = new EnumMap<>(Dias.class);
        for (Dias dia : Dias.values()) {
            alocacoesPorDia.put(dia, new LinkedList<>());
        }
        this.validador = new ValidadorConflitoPadrao();
    }

    public void AdicionarAlocacao(Alocacao novaAlocacao) {
        if (novaAlocacao == null) {
            throw new IllegalStateException("A alocação não pode ser nula.");
        }
        this.validador.validar(novaAlocacao, this.getAlocacoes());
        this.alocacoesPorDia.get(novaAlocacao.getHorario().getDia()).add(novaAlocacao);
    }

    public List<Alocacao> getAlocacoes() {
        List<Alocacao> todas = new ArrayList<>();
        for (Queue<Alocacao> filaDoDia : alocacoesPorDia.values()) {
            todas.addAll(filaDoDia);
        }
        return Collections.unmodifiableList(todas);
    }

    public Queue<Alocacao> getAlocacoesPorDia(Dias dia) {
        return new LinkedList<>(alocacoesPorDia.get(dia));
    }

    public void exibirGradeTeste() {
        System.out.println("\n========== GRADE DE HORÁRIOS ==========");
        List<Alocacao> alocacoes = getAlocacoes();
        if (alocacoes.isEmpty()) {
            System.out.println("A Grade está vazia");
        } else {
            for (Alocacao alocacao : alocacoes) {
                System.out.println(alocacao.toString());
                System.out.println("---------------------------------------");
            }
        }
        System.out.println("=======================================\n");
    }
}