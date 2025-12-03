package services.impl;

import lombok.RequiredArgsConstructor;
import model.Individuo;
import model.enums.TipoDocumento;
import repository.IndividuoRepo;
import services.IndividuoService;

import java.sql.SQLException;
import java.util.Optional;


@RequiredArgsConstructor
public class IndividuoServiceImpl implements IndividuoService {

    private final IndividuoRepo individuoRepo;

    @Override
    public Individuo crearIndividuo(Individuo individuo) throws SQLException {
        if(verificarNoVacios(individuo)){
            throw new IllegalArgumentException("Campos del individuo no deben ser vacios");
        }
        return individuoRepo.guardarIndividuo(individuo);
    }

    @Override
    public Optional<Individuo> buscarIndividuoPorTipoDocumentoYNumero(TipoDocumento tipoDocumento, String numeroDocumento) throws SQLException {
        if(tipoDocumento == null && numeroDocumento.isBlank()){
            return Optional.empty();
        }
        return individuoRepo.buscarIndividuoPorTipoDocumentYNumero(tipoDocumento.name(), numeroDocumento);
    }

    @Override
    public Optional<Individuo> buscarIndividuoPorNumeroDocumento(String numeroDocumento) throws SQLException {
        if(numeroDocumento.isBlank()){
            return Optional.empty();
        }
        return individuoRepo.buscarIndividuoPorNumeroDocumento(numeroDocumento);
    }

    private boolean verificarNoVacios(Individuo individuo)  {
        if(individuo.getNombre().isBlank()){
            return false;
        }
        if(individuo.getApellido().isBlank()){
            return false;
        }
        if(individuo.getGenero().isBlank()){
            return false;
        }
        if(individuo.getFechaNacimiento() == null){
            return false;
        }
        if(individuo.getPaisOrigen().isBlank()){
            return false;
        }
        if(individuo.getIdiomaPrincipal().isBlank()){
            return false;
        }
        if (individuo.getNivelEducacion() == null) {
            return false;
        }
        if(individuo.getTelefono().isBlank()){
            return false;
        }
        if (individuo.getEstatusLegal() == null) {
            return false;
        }
        if (individuo.getTipoDocumento() == null) {
            return false;
        }
        if (individuo.getNumeroDocumento().isBlank()) {
            return false;
        }
        if (individuo.getDiscapacidad().isBlank()) {
            return false;
        }
        if (individuo.getEnfermedadCronica().isBlank()) {
            return false;
        }
        if (individuo.getEmbarazada() == null) {
            return false;
        }
        if (individuo.getEstadoEmpleo() == null) {
            return false;
        }
        if (individuo.getRepresentanteHogar() == null){
            return false;
        }
        return true;
    }
}
