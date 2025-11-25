import model.Individuo;

public class HelloWorld {
    public static void main(String[] args) {
        Individuo individuo = Individuo.builder()
                .nombre("Luis")
                .apellido("Apellido")
                .build();

        System.out.println(individuo);
    }
}
