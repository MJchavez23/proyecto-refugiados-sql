package model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NivelEducacion {
    INICIAL("Inicial"),
    EGB("Educacion General Basica"),
    BACHILLERATO("Bachillerato"),
    SUPERIOR("Educacion Superior");

    public final String getString;
}
