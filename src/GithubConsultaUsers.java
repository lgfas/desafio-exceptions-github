import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class GithubConsultaUsers {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite o usuário do GitHub: ");
        String user = leitura.nextLine();

        String endereco = "https://api.github.com/users/" + user;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int responseCode = response.statusCode();

            if (responseCode != 200) {
                throw new UsuarioNaoEncontradoException("Usuário não encontrado no GitHub.");
            }

            System.out.println(response.body());

        } catch (UsuarioNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}