public class CriterioPrecoMinimo implements CriterioBusca {

    @Override
    public boolean testar(Produto p, String valor) {
        try {
            double precoMin = Double.parseDouble(valor);
            return p.getPreco() >= precoMin;
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido para preço mínimo: " + valor);
            return false;
        }
    }
}