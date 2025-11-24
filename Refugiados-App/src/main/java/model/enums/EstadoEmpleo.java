package model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoEmpleo {
    DEPENDENCIA("Dependencia"),
    DESEMPLEADO("Desempleado"),
    AUTONOMO("Autonomo");

    public final String toString;
}
