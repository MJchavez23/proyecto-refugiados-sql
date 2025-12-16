package services;

import model.Hogar;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HogarService {
    void guardarHogar(Hogar hogar) throws SQLException;
    List<Hogar> buscarTodosHogares() throws SQLException;
    Optional<Hogar> buscarPorId(Integer id) throws SQLException;
    void guardarTodos(List<Hogar> hogares) throws SQLException;
}
