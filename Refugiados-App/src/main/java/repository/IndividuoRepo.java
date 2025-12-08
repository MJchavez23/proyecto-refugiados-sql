package repository;


import lombok.RequiredArgsConstructor;
import model.Hogar;
import model.Individuo;
import model.Refugio;
import model.enums.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class IndividuoRepo {

    private final Connection connection;

    public void guardarIndividuo(Individuo individuo) throws SQLException {
        String query = "SELECT guardar_individuo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //Preparamos el query

        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement statementListo = llenarStatement(statement, individuo); //Ingresa los valores del individuo dentro de statement

        statementListo.execute(); //Ejecutamos el guardado
    }


    public Optional<Individuo> buscarIndividuoPorNumeroDocumento(String numeroDocumento) throws SQLException {
        String query = "SELECT * FROM buscarIndividuoPorNumeroDeDocumento(?);";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, numeroDocumento);

        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            Individuo individuo = crearIndividuo(rs);
            return Optional.of(individuo);
        }
        return Optional.empty();
    }

    public List<Individuo> buscarTodosIndividuos() throws SQLException {
        List<Individuo> individuos = new ArrayList<>();
        String query = "SELECT * FROM buscarTodosIndividuos();";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            Individuo individuo = crearIndividuo(rs);
            individuos.add(individuo);
        }
        return individuos;
    }

    private Individuo crearIndividuo(ResultSet rs) throws SQLException {
        Refugio refugio = Refugio.builder()
                .id(rs.getInt("ref_id"))
                .nombre(rs.getString("ref_nombre"))
                .ciudad(rs.getString("ciudad"))
                .pais(rs.getString("pais"))
                .referenciaUbicacion(rs.getString("referencia"))

                .build();

        Hogar hogar = Hogar.builder()
                .id(rs.getInt("hog_id"))
                .refugio(refugio)
                .nombreHogar(rs.getString("nombre_hogar"))
                .fechaLlegada(rs.getObject("fecha_llegada_refugio", LocalDate.class))
                .build();

        return Individuo.builder()
                .id(rs.getInt("ind_id"))
                .hogar(hogar)
                .nombre(rs.getString("ind_nombre"))
                .apellido(rs.getString("apellido"))
                .genero(Genero.valueOf(rs.getString("genero")))
                .fechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class)) //Convierte el tipo SQL.Date a util.LocalDate
                .paisOrigen(rs.getString("pais_origen"))
                .idiomaPrincipal(rs.getString("idioma_principal"))
                .nivelEducacion(NivelEducacion.valueOf(rs.getString("nivel_educativo")))
                .telefono(rs.getString("telefono"))
                .estatusLegal(EstatusLegal.valueOf(rs.getString("estatus_legal")))
                .tipoDocumento(TipoDocumento.valueOf(rs.getString("tipo_documento")))
                .numeroDocumento(rs.getString("numero_documento"))
                .discapacidad(rs.getString("discapacidad"))
                .enfermedadCronica(rs.getString("enfermedad_cronica"))
                .embarazada(rs.getBoolean("embarazada"))
                .estadoEmpleo(EstadoEmpleo.valueOf(rs.getString("estado_empleo")))
                .representanteHogar(rs.getBoolean("representante_hogar"))
                .build();
    }

    private PreparedStatement llenarStatement(PreparedStatement statement, Individuo individuo) throws SQLException {
            statement.setInt(1, individuo.getHogar().getId());
            statement.setString(2, individuo.getNombre());
            statement.setString(3, individuo.getApellido());
            statement.setString(4, individuo.getGenero().name());
            statement.setObject(5, Date.valueOf(individuo.getFechaNacimiento()));
            statement.setString(6, individuo.getPaisOrigen());
            statement.setString(7, individuo.getIdiomaPrincipal());
            statement.setString(8, individuo.getNivelEducacion().name());
            statement.setString(9, individuo.getTelefono());
            statement.setString(10, individuo.getEstatusLegal().name());
            statement.setString(11, individuo.getTipoDocumento().name());
            statement.setString(12, individuo.getNumeroDocumento());
            statement.setString(13, individuo.getDiscapacidad());
            statement.setString(14, individuo.getEnfermedadCronica());
            statement.setBoolean(15, individuo.getEmbarazada());
            statement.setString(16, individuo.getEstadoEmpleo().name());
            statement.setBoolean(17, individuo.getRepresentanteHogar());
            return statement;
    }
}
