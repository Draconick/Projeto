package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import brandroid.um.capitulo.projeto.dados.RepositorioProdutos;
import brandroid.um.capitulo.projeto.modelo.Produto;

/**
 * Created by Katrina on 03/12/2015.
 */
public class EditarProdutoActivity extends Activity {
    private Produto produto;
    private Spinner spinnerCategoria;
    private EditText editNomeProduto,editQntEstoque,editPreco,editValorDeCompra;
    private RepositorioProdutos repositorioProdutos;
    private String nomeProduto,categoria;
    private int qntEstoque;
    private double preco,valordeCompra;
    String produtoAlterado;
    private Button buttonAlterar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_produto);
        produtoAlterado = (String) getIntent().getSerializableExtra("produto");
        buttonAlterar = (Button) findViewById(R.id.confirmacao_produto_button);
        spinnerCategoria = (Spinner) findViewById(R.id.categoria);
        editNomeProduto = (EditText) findViewById(R.id.nome_produto);
        editQntEstoque = (EditText) findViewById(R.id.qnt_estoque);
        editPreco = (EditText) findViewById(R.id.preco_produto);
        editValorDeCompra = (EditText) findViewById(R.id.valor_produto);
        repositorioProdutos = new RepositorioProdutos(this);

        buttonAlterar.setText("Atualizar");
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.produto_categoria_lista,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);
    }

    public void cadastrarProduto(View v) {
        nomeProduto = editNomeProduto.getText().toString();
        categoria = spinnerCategoria.getSelectedItem().toString();
        String strQntEstoque = editQntEstoque.getText().toString();
        if (strQntEstoque.isEmpty()) {
            qntEstoque = 0;
        } else {
            qntEstoque = Integer.parseInt(strQntEstoque);
        }
        String strPreco = editPreco.getText().toString();
        if (strPreco.isEmpty()) {
            preco = 0;
        } else {
            preco = Double.parseDouble(editPreco.getText().toString());
        }
        String strValorCompra = editValorDeCompra.getText().toString();
        if (strValorCompra.isEmpty()) {
            valordeCompra = 0;
        } else {
            valordeCompra = Double.parseDouble(editValorDeCompra.getText().toString());
        }
        if(nomeProduto.isEmpty() || categoria.isEmpty() ||
                qntEstoque <= 0 || preco <= 0 || valordeCompra <= 0){
            Toast toast = Toast.makeText(this,R.string.cadastro_invalido,Toast.LENGTH_SHORT);
            toast.show();
        } else {
            produto = new Produto(nomeProduto, categoria, qntEstoque, preco, valordeCompra);
            repositorioProdutos.atualizar(produto, produtoAlterado);
            Toast toast = Toast.makeText(this,R.string.produto_sucesso,Toast.LENGTH_SHORT);
            toast.show();
            setResult(RESULT_OK,null);
            finish();
        }
    }
}
