package services;

import model.Individuo;
import model.enums.TipoDocumento;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IndividuoService {
    void crearIndividuo(Individuo individuo) throws SQLException;
    Optional<Individuo> buscarIndividuoPorNumeroDocumento(String numeroDocumento) throws SQLException;
    List<Individuo> buscarTodosIndividuos() throws SQLException;
    void crearVariosIndividuos(List<Individuo> individuos) throws SQLException;
}
