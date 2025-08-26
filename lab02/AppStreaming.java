import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppStreaming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Midia> midias = new ArrayList<>();

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("(1) Adicionar novo Filme.");
            System.out.println("(2) Adicionar nova Série.");
            System.out.println("(3) Listar todas as mídias.");
            System.out.println("(4) Sair.");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do filme: ");
                    String tituloFilme = scanner.nextLine();
                    System.out.print("Digite a duração do filme (em minutos): ");
                    int duracaoFilme = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    Filme filme = new Filme(tituloFilme, duracaoFilme);
                    midias.add(filme);
                    break;

                case 2:
                    System.out.print("Digite o título da série: ");
                    String tituloSerie = scanner.nextLine();
                    Serie serie = new Serie(tituloSerie);

                    // Adicionando 2 temporadas à série
                    for (int i = 1; i <= 2; i++) {
                        System.out.print("Digite o título da Temporada " + i + ": ");
                        String tituloTemporada = scanner.nextLine();
                        Temporada temporada = new Temporada(tituloTemporada);

                        // Adicionando 2 episódios à temporada
                        for (int j = 1; j <= 2; j++) {
                            System.out.print("Digite o título do Episódio " + j + ": ");
                            String tituloEpisodio = scanner.nextLine();
                            System.out.print("Digite a duração do Episódio " + j + " (em minutos): ");
                            int duracaoEpisodio = scanner.nextInt();
                            scanner.nextLine();  // Limpar o buffer
                            Episodio episodio = new Episodio(tituloEpisodio, duracaoEpisodio);
                            temporada.adicionarEpisodio(episodio);
                        }

                        serie.adicionarTemporada(temporada);
                    }

                    midias.add(serie);
                    break;

                case 3:
                    System.out.println("Listando todas as mídias:");
                    for (Midia midia : midias) {
                        midia.mostrarDetalhes();
                    }
                    break;

                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
            }
        }
    }
}