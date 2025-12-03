package services;

import model.Hogar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HogarService {
    List<Hogar> buscarTodosHogares() throws SQLException;
    Optional<Hogar> buscarPorId(Integer id) throws SQLException;
}
