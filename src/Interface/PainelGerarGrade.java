package Interface;

import Excecoes.*;
import Controle.*;
import Modelo.*;
import java.awt.*;
import javax.swing.*;
import java.util.List;

public class PainelGerarGrade extends Painel{
    private final ControlePaineis controlePaineis;
    private final ControleDados controleDados;
    private GeradorGrade geradorGrade=new GeradorGrade(new SimpleStrategy());
    
    public PainelGerarGrade(String titulo, ControlePaineis controlePaineis, ControleDados controleDados) {
        super("Gerar Grade", controlePaineis);
        this.controlePaineis = controlePaineis;
        this.controleDados = controleDados;
    }
    @Override
    protected void inicializarComponentes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String texto="Escolha qual estratégia deseja usar para criar a grade";
        JLabel instrucao=new JLabel(texto);
        instrucao.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instrucao.setForeground(Color.WHITE);
        instrucao.setAlignmentX(CENTER_ALIGNMENT);
        instrucao.setAlignmentY(TOP_ALIGNMENT);
        add(instrucao, BorderLayout.NORTH);

        Botao simples=new Botao("SimpleStrategy", 10);
        simples.addActionListener(e->{
           geradorGrade.setEstrategia(new SimpleStrategy());
        });
        simples.setAlignmentX(CENTER_ALIGNMENT);
        add(simples);
        Botao balanceado=new Botao("BalanceStrategy", 10);
        balanceado.addActionListener(e->{
            geradorGrade.setEstrategia(new BalanceStrategy());
        });
        balanceado.setAlignmentX(CENTER_ALIGNMENT);
        add(balanceado);





        Botao gerar = new Botao("Gerar Grade", 10);
        gerar.addActionListener(e -> {
            try {
                List<Disciplina> listaDisciplinas = java.util.Arrays.asList(Disciplina.values());
                Grade gradeGerada = geradorGrade.gerar(
                        listaDisciplinas,
                        controleDados.getProfessores(),
                        controleDados.getTurmas()
                );

                controlePaineis.setGradeGerada(gradeGerada);
                JOptionPane.showMessageDialog(this, "Grade gerada com sucesso!");
                controlePaineis.trocarPainel(PaineisDoPrograma.GRADE);

            }catch (ConflitoProfessorException ex){
                JOptionPane.showMessageDialog(this, "Erro de Alocação: " + ex.getMessage(), "Conflito de professor", JOptionPane.WARNING_MESSAGE);

            }catch (ProfessorIncompativelException ex){
                JOptionPane.showMessageDialog(this, "Erro de Competência: " + ex.getMessage(), "Incompatibilidade", JOptionPane.WARNING_MESSAGE);

            }catch (TurmaNulaException ex){
                JOptionPane.showMessageDialog(this, "Erro nos dados da Grade: " + ex.getMessage(), "Não foi possível gerar", JOptionPane.WARNING_MESSAGE);

            }catch (RuntimeException ex){
                //Para algum outro erro não identificado
                JOptionPane.showMessageDialog(this, "Atenção: " + ex.getMessage(), "Erro!", JOptionPane.WARNING_MESSAGE);
            }
        });
        gerar.setAlignmentX(CENTER_ALIGNMENT);
        add(gerar);
        
        add(Box.createVerticalStrut(20));

        Botao voltar = new Botao("Voltar ao painel inicial", 10);
        voltar.addActionListener(e -> controlePaineis.trocarPainel(PaineisDoPrograma.INICIAL));
        voltar.setAlignmentX(CENTER_ALIGNMENT);
        add(voltar);
    }
}
