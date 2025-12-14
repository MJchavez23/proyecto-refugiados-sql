package services;


import model.Individuo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileManagerService {
    List<Individuo> extraerIndividuos(File file) throws IOException;
}
