package Dados;

import Excecoes.*;
import java.util.HashMap;
import java.util.Map;


import Modelo.Turma;
import Modelo.Cursos; // Necessário importar o enum Cursos
import java.util.List;

public class BancoTurmas {
    private final Map<String, Turma> turmas;

    public BancoTurmas() {
        this.turmas = new HashMap<>();
    }

    // Método auxiliar privado para padronizar a criação da chave única
    private String gerarChave(Cursos curso, int semestre) {
        return curso.name() + "-" + semestre;
    }

    public void adicionarTurma(Turma turma) {
        // Validação utilizando os atributos reais da sua classe Turma
        if (turma == null || turma.getCurso() == null) { 
            throw new TurmaNulaException("A turma ou o curso não podem ser nulos.");
        }
        
        // Cria a chave combinando curso e semestre e insere no mapa
        String chave = gerarChave(turma.getCurso(), turma.getSemestre());
        turmas.put(chave, turma);
    }

    // A busca agora exige o curso e o semestre para encontrar a turma exata
    public Turma buscarTurma(Cursos curso, int semestre) {
        String chave = gerarChave(curso, semestre);
        return turmas.get(chave);
    }
    public List<Turma> getTodasTurmas() {
        return new java.util.ArrayList<>(turmas.values());
    }
}