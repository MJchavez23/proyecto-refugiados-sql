package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refugio {
    private Integer id;
    private String nombre;
    private String ciudad;
    private String pais;
    private String referenciaUbicacion;
}
