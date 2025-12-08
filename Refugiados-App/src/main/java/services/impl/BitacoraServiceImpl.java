package services.impl;

import lombok.RequiredArgsConstructor;
import model.Bitacora;
import repository.BitacoraRepo;
import services.BitacoraService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class BitacoraServiceImpl implements BitacoraService {

    private final BitacoraRepo repository;


    @Override
    public List<Bitacora> buscarTodasBitacoras() throws SQLException {
        return repository.buscarTodosBitacoras();
    }
}
