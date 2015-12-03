package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

import brandroid.um.capitulo.projeto.apresentacao.ProdutoAdapter;
import brandroid.um.capitulo.projeto.dados.RepositorioProdutos;
import brandroid.um.capitulo.projeto.modelo.Produto;

/**
 * Created by Katrina on 01/12/2015.
 */
public class ListarProduto extends Activity {
    private static final int TELA_EDICAO = 1;

    private RepositorioProdutos repositorioProdutos;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_produto);
        lista = (ListView) findViewById(R.id.lista_produtos_admin);
        registerForContextMenu(this.lista);
        repositorioProdutos = new RepositorioProdutos(this);
        carregarProdutos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarProdutos();
    }

    private void carregarProdutos(){
        List<Produto> listaProdutos = repositorioProdutos.listar();
        ProdutoAdapter adapter = new ProdutoAdapter(this, listaProdutos);
        this.lista.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lista_admin, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Produto produto = (Produto) this.lista.getItemAtPosition(info.position);
        switch (item.getItemId()){
            case R.id.menu_listar_editar:
                editarProduto(produto);
                return true;
            case R.id.menu_listar_excluir:
                excluirProduto(produto);
                return true;
            default:
                return false;
        }
    }

    public void adicionarProduto(View view){
        adicionarProdutoActivity();
    }
    private void adicionarProdutoActivity(){
        Intent it = new Intent(this,CadastroProdutoActivity.class);
        startActivity(it);
    }
    private void excluirProduto(final Produto produto){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmação");
        builder.setMessage(R.string.produto_msg_excluir);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //excluir produto
                repositorioProdutos.deletar(produto);
                carregarProdutos();
                Toast.makeText(getApplicationContext(), R.string.produto_excluir,
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void editarProduto(Produto produto){
        Intent it = new Intent(this, EditarProdutoActivity.class);
        it.putExtra("produto", produto.getNomeProduto());
        startActivityForResult(it, TELA_EDICAO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TELA_EDICAO){
            if(resultCode == RESULT_OK){
                carregarProdutos();
            }
        }
    }
}
