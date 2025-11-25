package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.EstadoServicio;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialServicios {
    private int id;
    private Servicio servicio;
    private Hogar hogar;
    private Personal personal;
    private LocalDate fechaServicio;
    private EstadoServicio estadoServicio;
    private String descripcion;
    private LocalDate ultimaModificacion;

}
