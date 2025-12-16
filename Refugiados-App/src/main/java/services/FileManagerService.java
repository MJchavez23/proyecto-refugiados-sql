package services;


import model.Hogar;
import model.Individuo;
import model.Refugio;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileManagerService {
    List<Individuo> extraerIndividuos(File file) throws IOException;
    List<Refugio> extraerRefugios(File file) throws IOException;
    List<Hogar> extraerHogares(File file) throws IOException;
}
