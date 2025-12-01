package model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstatusLegal {
    REFUGIADO("Refugiado");

    public final String getString;
}
