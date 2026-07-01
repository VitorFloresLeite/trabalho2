package Controle; // Ou em um pacote chamado 'Servicos'

import Dados.*;
import Modelo.*;

public class ControleDados {
    private final BancoProfessores bancoProfessores;
    private final BancoTurmas bancoTurmas;

    public ControleDados() {
        this.bancoProfessores = new BancoProfessores();
        this.bancoTurmas = new BancoTurmas();
    }

    // Métodos de delegação para o Banco de Professores
    public void cadastrarProfessor(Professor professor) {
        bancoProfessores.adicionarProfessor(professor);
    }

    public Professor buscarProfessor(String nome) {
        return bancoProfessores.buscarProfessor(nome);
    }
    // Métodos de delegação para o Banco de Turmas
    public void cadastrarTurma(Turma turma) {
        bancoTurmas.adicionarTurma(turma);
    }
    public Turma buscarTurma(Cursos curso, int semestre) {
        return bancoTurmas.buscarTurma(curso, semestre);
    }
}