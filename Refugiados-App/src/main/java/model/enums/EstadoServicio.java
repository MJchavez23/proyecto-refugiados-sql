package model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoServicio {
    PENDIENTE("Pendiente"),
    EN_PROGRESO("En Progreso"),
    CANCELADO("Cancelado"),
    COMPLETADO("Completado");

    public final String getString;
}
