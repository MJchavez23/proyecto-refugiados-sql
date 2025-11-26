package services;

import model.Individuo;
import model.enums.TipoDocumento;

import java.sql.SQLException;
import java.util.Optional;

public interface IndividuoService {
    Individuo crearIndividuo(Individuo individuo) throws SQLException;
    Optional<Individuo> buscarIndividuoPorTipoDocumentoYNumero(TipoDocumento tipoDocumento, String numeroDocumento) throws SQLException;
    Optional<Individuo> buscarIndividuoPorNumeroDocumento(String numeroDocumento) throws SQLException;
}
