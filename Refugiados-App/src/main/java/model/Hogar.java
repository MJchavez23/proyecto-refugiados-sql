package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hogar {
    private Integer id;
    private Refugio refugio;
    private String nombreHogar;
    private LocalDate fechaLlegada;
}
