package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import brandroid.um.capitulo.projeto.apresentacao.PedidoAdapter;
import brandroid.um.capitulo.projeto.dados.RepositorioPedidos;
import brandroid.um.capitulo.projeto.modelo.Pedido;

/**
 * Created by Katrina on 04/12/2015.
 */
public class VerPedidoActivity extends Activity {
    private ListView lista;
    private RepositorioPedidos repositorioPedidos;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_pedido);
        lista = (ListView) findViewById(R.id.lista_pedido_user);
        user = (String) getIntent().getSerializableExtra("user");
        repositorioPedidos = new RepositorioPedidos(this);
        carregarPedidos();
    }

    private void carregarPedidos(){
        List<Pedido> pedidoList = repositorioPedidos.listarPorUsuario(user);
        PedidoAdapter adapter = new PedidoAdapter(this, pedidoList);
        lista.setAdapter(adapter);
    }

    public void logout(View view){
        finish();
    }
}
