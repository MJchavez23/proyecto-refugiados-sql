package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Individuo {
    private int id;
    private Hogar hogar;
    private String nombre;
    private String apellido;
    private Genero genero;
    private LocalDate fechaNacimiento;
    private String paisOrigen;
    private String idiomaPrincipal;
    private NivelEducacion nivelEducacion;
    private String telefono;
    private EstatusLegal estatusLegal;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String discapacidad;
    private String enfermedadCronica;
    private Boolean embarazada;
    private EstadoEmpleo estadoEmpleo;
    private Boolean representanteHogar;
}
