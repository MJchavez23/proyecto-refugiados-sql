package model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {
    CEDULA_IDENTIDAD("Cedula De Identidad"),
    PASAPORTE("Pasaporte"),
    LICENCIA_CONDUCIR("Licencia de Conducir");

    public final String toString;
}
