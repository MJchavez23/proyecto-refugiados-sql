package services.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import model.Hogar;
import repository.HogarRepo;
import services.HogarService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HogarServiceImpl implements HogarService {

    private final HogarRepo repository;

    @Override
    public List<Hogar> buscarTodosHogares() throws SQLException {
        return repository.buscarTodosHogares();
    }

    @Override
    public Optional<Hogar> buscarPorId(Integer id) throws SQLException {
        if(id != null) {
            return repository.buscarHogarPorId(id);
        }
        return Optional.empty();
    }
}
