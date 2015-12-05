package brandroid.um.capitulo.projeto.modelo;

/**
 * Created by Katrina on 04/12/2015.
 */
public class Pedido {
    private int id;
    private String user;
    private double valorPedido;
    private double lucroPedido;

    public Pedido(String user, double valorPedido, double lucroPedido) {
        id = 0;
        this.user = user;
        this.valorPedido = valorPedido;
        this.lucroPedido = lucroPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido(int id, String user,double valorPedido, double lucroPedido) {
        this.id = id;
        this.valorPedido = valorPedido;
        this.user = user;
        this.lucroPedido = lucroPedido;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(double valorPedido) {
        this.valorPedido = valorPedido;
    }

    public double getLucroPedido() {
        return lucroPedido;
    }

    public void setLucroPedido(double lucroPedido) {
        this.lucroPedido = lucroPedido;
    }
}
