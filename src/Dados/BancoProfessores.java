package Dados;

import Excecoes.*;
import Modelo.*;
import java.util.HashMap;
import java.util.Map; // Lembre-se de importar o Map
import java.util.List;

public class BancoProfessores {
    private final Map<String, Professor> professores;

    public BancoProfessores() {
        this.professores = new HashMap<>();
    }

    public void adicionarProfessor(Professor professor) {
        if (professor == null || professor.getNome() == null) { // Ajustado para getNome() baseado na sua classe Professor
            throw new ConflitoProfessorException("O professor ou o nome do professor não pode ser nulo.");
        }
        professores.put(professor.getNome(), professor);
    }

    public Professor buscarProfessor(String nome) {
        return professores.get(nome);
    }

    public List<Professor> getTodosProfessores() {
        return new java.util.ArrayList<>(professores.values());
    }
}