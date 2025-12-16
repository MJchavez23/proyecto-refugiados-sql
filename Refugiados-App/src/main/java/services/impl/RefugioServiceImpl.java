package services.impl;

import lombok.RequiredArgsConstructor;
import model.Refugio;
import repository.RefugioRepo;
import services.RefugioService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class RefugioServiceImpl implements RefugioService {

    private final RefugioRepo repo;

    @Override
    public void guardarRefugio(Refugio refugio) throws SQLException {
        repo.guardarRefugio(refugio);
    }

    @Override
    public void guardarRefugios(List<Refugio> refugios) throws SQLException {
        for (Refugio refugio : refugios) {
            repo.guardarRefugio(refugio);
        }
    }
}
