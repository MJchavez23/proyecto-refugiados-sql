package services.impl;

import lombok.RequiredArgsConstructor;
import model.Hogar;
import model.Individuo;
import model.Refugio;
import model.enums.*;
import org.apache.poi.ss.usermodel.*;
import services.FileManagerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FileManagerServiceImpl implements FileManagerService {

    @Override
    public List<Individuo> extraerIndividuos(File file) throws IOException {
        List<Individuo> inds = new ArrayList<>();

        FileInputStream in = new FileInputStream(file);
        //Nos ayuda a detectar si es un .xls(Viejo Formato) o .xlsx(Nuevo Formato)
        //Y da formato que java pueda entender
        Workbook workbook = WorkbookFactory.create(in);

        //Obtenemos la primera hoja
        Sheet sheet = workbook.getSheetAt(0);

        //Vamos por cada fila extrayendo informacion(Pasamos la primera fila que son los encabezados)
        for (Row fila : sheet) {
            if (fila.getRowNum() == 0) {
                continue;
            }
            Individuo ind = crearIndividuo(fila);
            inds.add(ind);
        }

        return inds;
    }

    @Override
    public List<Refugio> extraerRefugios(File file) throws IOException {
        List<Refugio> refugios = new ArrayList<>();

        FileInputStream in = new FileInputStream(file);

        Workbook workbook = WorkbookFactory.create(in);

        Sheet sheet = workbook.getSheetAt(1);

        for (Row fila : sheet) {
            if (fila.getRowNum() == 0) {
                continue;
            }
            Refugio ref = crearRefugio(fila);
            refugios.add(ref);
        }
        return refugios;
    }



    @Override
    public List<Hogar> extraerHogares(File file) throws IOException {
        List<Hogar> hogares = new ArrayList<>();

        FileInputStream in = new FileInputStream(file);

        Workbook workbook = WorkbookFactory.create(in);

        Sheet sheet = workbook.getSheetAt(2);

        for (Row fila : sheet) {
            if (fila.getRowNum() == 0) {
                continue;
            }
            Hogar ref = crearHogar(fila);
            hogares.add(ref);
        }
        return hogares;
    }

    private Hogar crearHogar(Row fila) {
        DataFormatter formatter = new DataFormatter();

        int idRefugio = Integer.parseInt(formatter.formatCellValue(fila.getCell(0)));
        String nombreHogar = formatter.formatCellValue(fila.getCell(1));
        LocalDate fechaLlegada = LocalDate.parse(formatter.formatCellValue(fila.getCell(2)));

        return Hogar.builder()
                .refugio(Refugio.builder().id(idRefugio).build())
                .nombreHogar(nombreHogar)
                .fechaLlegada(fechaLlegada)
                .build();
    }

    private Refugio crearRefugio(Row fila) throws IOException {
        DataFormatter formatter = new DataFormatter();

        String nombre = formatter.formatCellValue(fila.getCell(0));
        String ciudad = formatter.formatCellValue(fila.getCell(1));
        String pais = formatter.formatCellValue(fila.getCell(2));
        String referencia = formatter.formatCellValue(fila.getCell(3));

        return Refugio.builder()
                .nombre(nombre)
                .ciudad(ciudad)
                .pais(pais)
                .referenciaUbicacion(referencia)
                .build();
    }

    private Individuo crearIndividuo(Row fila) {
            //Da formato a cualquier tipo de celda
            DataFormatter formatter = new DataFormatter();
            int id_hogar = Integer.parseInt(formatter.formatCellValue(fila.getCell(0)));
            String nombre  = formatter.formatCellValue(fila.getCell(1));
            String apellido = formatter.formatCellValue(fila.getCell(2));
            Genero genero = Genero.valueOf(formatter.formatCellValue(fila.getCell(3)));
            LocalDate fechaNacimiento = LocalDate.parse(formatter.formatCellValue(fila.getCell(4)));
            String paisOrigen = formatter.formatCellValue(fila.getCell(5));
            String idiomaPrincipal = formatter.formatCellValue(fila.getCell(6));
            NivelEducacion nivelEducacion = NivelEducacion.valueOf(formatter.formatCellValue(fila.getCell(7)));
            String telefono =  formatter.formatCellValue(fila.getCell(8));
            EstatusLegal estatusLegal =  EstatusLegal.valueOf(formatter.formatCellValue(fila.getCell(9)));
            TipoDocumento tipoDocumento = TipoDocumento.valueOf(formatter.formatCellValue(fila.getCell(10)));
            String numeroDocumento = formatter.formatCellValue(fila.getCell(11));
            String discapacidad = formatter.formatCellValue(fila.getCell(12));
            String enfermedadCronica =  formatter.formatCellValue(fila.getCell(13));
            Boolean embarazada =  Boolean.parseBoolean(formatter.formatCellValue(fila.getCell(14)));
            EstadoEmpleo estadoEmpleo =  EstadoEmpleo.valueOf(formatter.formatCellValue(fila.getCell(15)));
            Boolean representanteHogar =   Boolean.parseBoolean(formatter.formatCellValue(fila.getCell(16)));
            return Individuo.builder()
                    .hogar(Hogar.builder().id(id_hogar).build())
                    .nombre(nombre)
                    .apellido(apellido)
                    .genero(genero)
                    .fechaNacimiento(fechaNacimiento)
                    .paisOrigen(paisOrigen)
                    .idiomaPrincipal(idiomaPrincipal)
                    .telefono(telefono)
                    .nivelEducacion(nivelEducacion)
                    .telefono(telefono)
                    .estatusLegal(estatusLegal)
                    .tipoDocumento(tipoDocumento)
                    .numeroDocumento(numeroDocumento)
                    .discapacidad(discapacidad)
                    .enfermedadCronica(enfermedadCronica)
                    .embarazada(embarazada)
                    .estadoEmpleo(estadoEmpleo)
                    .representanteHogar(representanteHogar)
                    .build();
    }




}
