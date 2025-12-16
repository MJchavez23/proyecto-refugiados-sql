package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.EstadoServicio;
import model.enums.TipoServicio;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servicio {
    private Integer id;
    private TipoServicio nombreServicio;
    private Hogar hogar;
    private String descripcionServicio;
    private EstadoServicio estadoServicio;
}
