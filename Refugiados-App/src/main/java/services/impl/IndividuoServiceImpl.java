package services.impl;

import lombok.RequiredArgsConstructor;
import model.Individuo;
import model.enums.TipoDocumento;
import repository.IndividuoRepo;
import services.IndividuoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class IndividuoServiceImpl implements IndividuoService {

    private final IndividuoRepo individuoRepo;

    @Override
    public void crearIndividuo(Individuo individuo) throws SQLException {
        if(!verificarNoVacios(individuo)){
            throw new IllegalArgumentException("Campos del individuo no deben ser vacios");
        }
        validarUnico(individuo.getNumeroDocumento());
        individuoRepo.guardarIndividuo(individuo);
    }

    @Override
    public Optional<Individuo> buscarIndividuoPorNumeroDocumento(String numeroDocumento) throws SQLException {
        if(numeroDocumento.isBlank()){
            return Optional.empty();
        }
        return individuoRepo.buscarIndividuoPorNumeroDocumento(numeroDocumento);
    }

    @Override
    public List<Individuo> buscarTodosIndividuos() throws SQLException {
        return individuoRepo.buscarTodosIndividuos();
    }

    @Override
    public void crearVariosIndividuos(List<Individuo> individuos) throws SQLException {
        for (Individuo individuo : individuos) {
            if(!verificarNoVacios(individuo)){
                throw new IllegalArgumentException("Campos del individuo no deben ser vacios");
            }
            verificarNoVacios(individuo);
            individuoRepo.guardarIndividuo(individuo);
        }
    }


    private void validarUnico(String numeroDocumento) throws SQLException {
        Optional<Individuo> ind = individuoRepo.buscarIndividuoPorNumeroDocumento(numeroDocumento);
        if(ind.isPresent()){
            throw new SQLException("Individuo ya existe");
        }
    }

    private boolean verificarNoVacios(Individuo individuo)  {

        String nombre = individuo.getNombre();
        String apellido = individuo.getApellido();
        String paisOrigen = individuo.getPaisOrigen();
        String idiomaPrincipal = individuo.getIdiomaPrincipal();
        String telefono = individuo.getTelefono();
        String numeroDocumento = individuo.getNumeroDocumento();
        String discapacidad = individuo.getDiscapacidad();
        String enfermedadCronica = individuo.getEnfermedadCronica();

        if(nombre == null || nombre.isBlank()){
            return false;
        }
        if(apellido == null || apellido.isBlank()){
            return false;
        }
        if(individuo.getGenero() == null){
            return false;
        }
        if(individuo.getFechaNacimiento() == null){
            return false;
        }

        if(paisOrigen == null || paisOrigen.isBlank()){
            return false;
        }
        if(idiomaPrincipal == null || idiomaPrincipal.isBlank()){
            return false;
        }
        if (individuo.getNivelEducacion() == null) {
            return false;
        }
        if(telefono == null || telefono.isBlank()){
            return false;
        }
        if (individuo.getEstatusLegal() == null) {
            return false;
        }
        if (individuo.getTipoDocumento() == null) {
            return false;
        }
        if (numeroDocumento == null || numeroDocumento.isBlank()) {
            return false;
        }
        if (discapacidad == null || discapacidad.isBlank()) {
            return false;
        }
        if (enfermedadCronica == null || enfermedadCronica.isBlank()) {
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
