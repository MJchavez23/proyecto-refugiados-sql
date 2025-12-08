package services;

import model.Bitacora;

import java.sql.SQLException;
import java.util.List;

public interface BitacoraService {
    List<Bitacora> buscarTodasBitacoras() throws SQLException;
}
