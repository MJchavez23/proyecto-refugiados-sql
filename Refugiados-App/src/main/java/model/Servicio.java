package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.TipoServicio;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servicio {
    private int id;
    private TipoServicio tipoServicio;
    private String areaServicio;
}
