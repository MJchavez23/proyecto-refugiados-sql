package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {
    private Integer id;
    private Refugio refugio;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String genero;
}
