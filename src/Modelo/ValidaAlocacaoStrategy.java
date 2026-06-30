package Modelo;

import java.util.List;

public interface ValidaAlocacaoStrategy{
    void validar(Alocacao novaAlocacao, List<Alocacao> alocacaoExistentes) throws IllegalStateException;
}
