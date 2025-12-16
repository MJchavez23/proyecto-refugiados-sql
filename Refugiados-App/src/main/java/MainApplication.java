import controller.*;
import repository.*;
import services.*;
import services.impl.*;
import view.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApplication {
    public static void main(String[] args) throws SQLException {
                String url = "jdbc:postgresql://localhost:5432/refugiados-app";
                String user = "admin";
                String password = "admin";

                Connection connection = DriverManager.getConnection(url, user, password);

                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();

                IndividuoRepo repo = new IndividuoRepo(connection);
                IndividuoService individuoService = new IndividuoServiceImpl(repo);
                HogarRepo hogarRepo = new HogarRepo(connection);
                HogarService hogarService = new HogarServiceImpl(hogarRepo);

                VentanaRegistroIndividuo ventanaRegistroIndividuo = new VentanaRegistroIndividuo(ventanaPrincipal);
                IndividuoController individuoController = new IndividuoController(individuoService, ventanaRegistroIndividuo, hogarService);
                individuoController.configurarListener();

                ventanaPrincipal.agregarPanel(ventanaRegistroIndividuo, VentanaPrincipal.NOMBRE_REGISTRO);
                
                VentanaBusqueda ventanaBusqueda = new VentanaBusqueda(ventanaPrincipal);
                BusquedaController busquedaController = new BusquedaController(ventanaBusqueda, individuoService);
                busquedaController.configurarListener();

                ventanaPrincipal.agregarPanel(ventanaBusqueda, VentanaPrincipal.NOMBRE_BUSQUEDA);

                BitacoraRepo bitacoraRepo = new BitacoraRepo(connection);
                FileManagerService fileManagerService = new FileManagerServiceImpl();
                BitacoraService bitacoraService = new BitacoraServiceImpl(bitacoraRepo);
                BitacoraPanel bitacoraPanel = new BitacoraPanel(ventanaPrincipal);
                BitacoraController bitacoraController = new BitacoraController(fileManagerService, bitacoraPanel, individuoService,  bitacoraService);
                bitacoraController.configurarListener();

                ventanaPrincipal.agregarPanel(bitacoraPanel, VentanaPrincipal.NOMBRE_BITACORA);

                VentanaServicio ventanaServicio = new VentanaServicio(ventanaPrincipal);
                ServicioRepo servicioRepo = new ServicioRepo(connection);
                ServiciosService serviciosService = new ServiciosServiceImpl(servicioRepo);
                ServicioController servicioController = new ServicioController(ventanaServicio, serviciosService, hogarService);
                servicioController.configurarListener();

                ventanaPrincipal.agregarPanel(ventanaServicio, VentanaPrincipal.NOMBRE_SERVICIO);

                VentanaHistorial ventanaHistorial = new VentanaHistorial(ventanaPrincipal);
                HistorialServicioRepo historialServicioRepo = new HistorialServicioRepo(connection);
                HistorialServicioService historialService = new HistorialServicioServiceImpl(historialServicioRepo);
                HistorialController historialController = new HistorialController(serviciosService, historialService, ventanaHistorial);
                historialController.configurarListener();

                ventanaPrincipal.agregarPanel(ventanaHistorial, VentanaPrincipal.NOMBRE_HISTORIAL);


                MenuController menuController = new MenuController(ventanaPrincipal, individuoController, busquedaController, bitacoraController, servicioController, historialController);
                menuController.iniciarApp();
    }
}
