package brandroid.um.capitulo.projeto.modelo;

import java.util.List;

/**
 * Created by Katrina on 28/11/2015.
 */
public class Produto {
    private String nomeProduto;
    private String categoria;
    private int qntEstoque;
    private double preco;
    private double valordeCompra;
    private int unidadesCompradas;

    public Produto(String nomeProduto, String categoria, int qntEstoque,
                   double preco, double valordeCompra) {
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.qntEstoque = qntEstoque;
        this.preco = preco;
        this.valordeCompra = valordeCompra;
        this.unidadesCompradas = 0;
    }

    public Produto(String nomeProduto, String categoria, int qntEstoque,
                   double preco, double valordeCompra, int unidadesCompradas) {
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.qntEstoque = qntEstoque;
        this.preco = preco;
        this.valordeCompra = valordeCompra;
        this.unidadesCompradas = unidadesCompradas;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQntEstoque() {
        return qntEstoque;
    }

    public void setQntEstoque(int qntEstoque) {
        this.qntEstoque = qntEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getValordeCompra() {
        return valordeCompra;
    }

    public void setValordeCompra(double valordeCompra) {
        this.valordeCompra = valordeCompra;
    }

    public int getUnidadesCompradas() {
        return unidadesCompradas;
    }

    public void setUnidadesCompradas(int unidadesCompradas) {
        this.unidadesCompradas = unidadesCompradas;
    }
    public double FecharCaixa(List<Produto> listaProduto){
        double lucrodoDia = 0;
        for(Produto p : listaProduto){
            lucrodoDia = lucrodoDia +
                    (p.getUnidadesCompradas() * (p.getPreco() - p.getValordeCompra()));
        }
        return lucrodoDia;
    }
}
