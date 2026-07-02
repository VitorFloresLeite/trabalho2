package Modelo;
import java.util.List;

public interface CriterioAlocacao {
    boolean validar (Alocacao nova, List<Alocacao> existentes);
}
