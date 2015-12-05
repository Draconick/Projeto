package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import brandroid.um.capitulo.projeto.apresentacao.ComprarProdutoAdapter;
import brandroid.um.capitulo.projeto.dados.RepositorioPedidos;
import brandroid.um.capitulo.projeto.dados.RepositorioProdutos;
import brandroid.um.capitulo.projeto.modelo.Pedido;
import brandroid.um.capitulo.projeto.modelo.Produto;

/**
 * Created by Katrina on 04/12/2015.
 */
public class ComprarProdutoActivity extends Activity {
    private RepositorioProdutos repositorioProdutos;
    private ListView lista;
    private int unidadesCompradas;
    private List<Produto> produtoList;
    private double lucroPedido,valorPedido;
    private String user;
    private RepositorioPedidos repositorioPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprar_produto);

        user = (String) getIntent().getSerializableExtra("user");
        repositorioProdutos = new RepositorioProdutos(this);
        repositorioPedidos = new RepositorioPedidos(this);
        lista = (ListView) findViewById(R.id.lista_produtos_user);
        registerForContextMenu(lista);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarProdutos();
    }

    private void carregarProdutos(){
        produtoList = repositorioProdutos.listar();
        ComprarProdutoAdapter adapter = new ComprarProdutoAdapter(this,produtoList);
        lista.setAdapter(adapter);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_compra_product, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Produto produto = (Produto) this.lista.getItemAtPosition(info.position);
        switch (item.getItemId()){
            case R.id.menu_comprar_produto:
                comprarProduto(produto);
                return true;
            default:
                return false;
        }
    }

    private void comprarProduto(final Produto produto) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View edtEntryView = inflater.inflate(R.layout.alert_dialog_unidade,null);
        final EditText edtUnidadesCompradas =
                (EditText) edtEntryView.findViewById(R.id.unidades_compradas_produto);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.msg_dialog_unidades);
        builder.setView(edtEntryView);
        builder.setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strUnidadesCompradas = edtUnidadesCompradas.getText().toString();
                unidadesCompradas = Integer.parseInt(strUnidadesCompradas);
                repositorioProdutos.atualizarUnidadesCompradas(produto, unidadesCompradas);
                carregarProdutos();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void finalizarCompra(View view) {
        for(Produto p : produtoList){
            lucroPedido = lucroPedido +
                    (p.getUnidadesCompradas() * (p.getPreco() - p.getValordeCompra()));
            valorPedido = valorPedido + (p.getUnidadesCompradas() * p.getPreco());
        }
        Pedido pedido = new Pedido(user,valorPedido,lucroPedido);
        repositorioPedidos.inserir(pedido);
        for(Produto p : produtoList){
            repositorioProdutos.zerarUnidadesCompradas(p);
        }
        carregarProdutos();
        finish();
    }
}
