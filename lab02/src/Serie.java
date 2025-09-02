import java.util.ArrayList;
import java.util.List;

class Serie extends Midia {
    private List<Temporada> temporadas;

    public Serie(String titulo) {
        super(titulo); // Chama o construtor da classe Midia
        this.temporadas = new ArrayList<>();
    }

    public void adicionarTemporada(Temporada temporada) {
        this.temporadas.add(temporada);
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Série: " + getTitulo());
        for (Temporada temporada : temporadas) {
            temporada.mostrarDetalhes();
        }
    }
}