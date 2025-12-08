package model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Bitacora {
    private String usuario;
    private LocalDate fecha;
    private String accion;
    private String tabla;
}