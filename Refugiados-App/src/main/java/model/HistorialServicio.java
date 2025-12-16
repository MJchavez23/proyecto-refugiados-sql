package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.EstadoServicio;
import model.enums.TipoServicio;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialServicio {
    private Integer id;
    private Servicio servicio;
    private Personal personal;
    private LocalDate fechaServicio;
    private String descripcion;
}
