import model.Individuo;
import repository.IndividuoRepo;

import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloWorld {
    public static void main(String[] args) {
        String url =  "jdbc:mysql://localhost:5432/";
        String user = "postgres";
        String password = "admin";

        try {
            IndividuoRepo ind = new IndividuoRepo(DriverManager.getConnection(url, user, password));
            ind.guardarIndividuo(Individuo.builder()

                    .build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
