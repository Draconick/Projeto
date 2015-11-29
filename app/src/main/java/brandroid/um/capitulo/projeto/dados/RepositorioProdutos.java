package brandroid.um.capitulo.projeto.dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import brandroid.um.capitulo.projeto.modelo.Produto;

/**
 * Created by Katrina on 29/11/2015.
 */
public class RepositorioProdutos {
    private ProdutoOpenHelper produtoOpenHelper;

    public RepositorioProdutos(Context context){
        this.produtoOpenHelper = new ProdutoOpenHelper(context);
    }

    public void inserir(Produto produto){
        SQLiteDatabase db = this.produtoOpenHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome",produto.getNomeProduto());
        valores.put("categoria", produto.getCategoria());
        valores.put("qntestoque",produto.getQntEstoque());
        valores.put("preco", produto.getPreco());
        valores.put("valorCompra", produto.getValordeCompra());
        valores.put("unidadesCompradas", produto.getUnidadesCompradas());
        db.insert(produtoOpenHelper.TABELA_PRODUTO, null, valores);
        db.close();
    }
    public List<Produto> listar(){
        SQLiteDatabase db = this.produtoOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(produtoOpenHelper.TABELA_PRODUTO,
                new String[]{"nome", "categoria", "qntestoque", "preco", "valorCompra",
                        "unidadesCompradas"}, null, null, null, null, null);
        List<Produto> listarProdutos = new ArrayList<Produto>();
        while(cursor.moveToNext()){
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            int qntestoque = cursor.getInt(cursor.getColumnIndex("qntestoque"));
            double preco = cursor.getDouble(cursor.getColumnIndex("preco"));
            double valorCompra = cursor.getDouble(cursor.getColumnIndex("valorCompra"));
            int unidadesCompradas = cursor.getInt(cursor.getColumnIndex("unidadesCompradas"));

            Produto produto = new Produto(nome,categoria,qntestoque,
                    preco,valorCompra,unidadesCompradas);
            listarProdutos.add(produto);
        }
        return listarProdutos;
    }
}