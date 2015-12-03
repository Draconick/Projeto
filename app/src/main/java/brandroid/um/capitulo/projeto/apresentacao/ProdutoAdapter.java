package brandroid.um.capitulo.projeto.apresentacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import brandroid.um.capitulo.projeto.R;
import brandroid.um.capitulo.projeto.modelo.Produto;

/**
 * Created by Katrina on 03/12/2015.
 */
public class ProdutoAdapter extends BaseAdapter {
    private Context context;
    private List<Produto> listaProdutos;
    private LayoutInflater inflater;

    public ProdutoAdapter(Context context, List<Produto> listaProdutos){
        this.context = context;
        this.listaProdutos = listaProdutos;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listaProdutos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProdutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.produto_item_list, null);
        Produto produto = listaProdutos.get(position);
        TextView textNomeProduto = (TextView) view.findViewById(R.id.produto_lista);
        textNomeProduto.setText(produto.getNomeProduto());
        TextView textCategoria = (TextView) view.findViewById(R.id.categoria_lista);
        textCategoria.setText(produto.getCategoria());
        TextView textQntEstoque = (TextView) view.findViewById(R.id.qnt_estoque_lista);
        textQntEstoque.setText("Quantidade em Estoque: "+ produto.getQntEstoque());
        TextView textPreco = (TextView) view.findViewById(R.id.preco_lista);
        textPreco.setText("R$" + produto.getPreco());
        TextView textValorCompra = (TextView) view.findViewById(R.id.valor_produto_lista);
        textValorCompra.setText("R$" + produto.getValordeCompra());

        return view;
    }
}
