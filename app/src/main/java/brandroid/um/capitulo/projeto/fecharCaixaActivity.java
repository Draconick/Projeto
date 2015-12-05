package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import brandroid.um.capitulo.projeto.apresentacao.PedidoAdminAdapter;
import brandroid.um.capitulo.projeto.dados.RepositorioPedidos;
import brandroid.um.capitulo.projeto.modelo.Pedido;

/**
 * Created by Katrina on 04/12/2015.
 */
public class fecharCaixaActivity extends Activity {
    private ListView lista;
    private RepositorioPedidos repositorioPedidos;
    private double lucro;
    private List<Pedido> pedidoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fechar_caixa);
        lista = (ListView) findViewById(R.id.lista_pedido_admin);
        repositorioPedidos = new RepositorioPedidos(this);
        carregarPedidos();
    }

    private void carregarPedidos(){
        pedidoList = repositorioPedidos.listar();
        PedidoAdminAdapter adapter = new PedidoAdminAdapter(this,pedidoList);
        lista.setAdapter(adapter);
    }

    public void fecharDia(View view){
        lucro();
    }
    private void lucro(){
        for (Pedido p : pedidoList){
            lucro = lucro + p.getLucroPedido();
        }
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View txtEntryView = inflater.inflate(R.layout.alert_dialog_lucro,null);
        final TextView txtMensagemLucro = (TextView) txtEntryView.findViewById(R.id.msg_lucro_dia);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(R.string.alert_dialog_fechar_caixa);
        builder1.setView(txtEntryView);
        txtMensagemLucro.setText(String.format("O Lucro do dia foi: R$%.2f",lucro));
        builder1.setTitle("Confirmação");
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Deleta TODOS OS PEDIDOS
                repositorioPedidos.deletar();
                carregarPedidos();
            }
        });
        builder1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder1.show();
        lucro = 0;
    }
}
