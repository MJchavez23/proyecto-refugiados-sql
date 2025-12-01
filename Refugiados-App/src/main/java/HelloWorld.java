import model.Hogar;
import model.Individuo;
import model.Refugio;
import model.enums.EstadoEmpleo;
import model.enums.EstatusLegal;
import model.enums.NivelEducacion;
import model.enums.TipoDocumento;
import repository.IndividuoRepo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloWorld {
    public static void main(String[] args) {
        String url =  "jdbc:postgresql://localhost:5432/postgres";
        String user = "admin";
        String password = "admin";

        try {

            Refugio refugio = Refugio.builder()
                    .id(1)
                    .nombre("Refugio")
                    .pais("pais")
                    .ciudad("ciudad")
                    .referenciaUbicacion("ubicacion")
                    .build();

            Hogar hogar = Hogar.builder()
                    .id(1)
                    .nombreHogar("Familia Chavez Castillo")
                    .fechaLlegada(LocalDate.now())
                    .refugio(refugio)
                    .build();

            IndividuoRepo ind = new IndividuoRepo(DriverManager.getConnection(url, user, password));
            ind.guardarIndividuo(Individuo.builder()
                            .hogar(hogar)
                            .nombre("Nombre")
                            .apellido("Apellido")
                            .genero("Hombre")
                            .fechaNacimiento(LocalDate.now())
                            .paisOrigen("Origen")
                            .idiomaPrincipal("Español")
                            .nivelEducacion(NivelEducacion.SUPERIOR)
                            .telefono("099999")
                            .estatusLegal(EstatusLegal.REFUGIADO)
                            .tipoDocumento(TipoDocumento.CEDULA_IDENTIDAD)
                            .numeroDocumento("0999999")
                            .discapacidad("ninguna")
                            .enfermedadCronica("Ninguna")
                            .embarazada(false)
                            .estadoEmpleo(EstadoEmpleo.DESEMPLEADO)
                            .representanteHogar(true)
                    .build());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
