package brandroid.um.capitulo.projeto.apresentacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import brandroid.um.capitulo.projeto.R;
import brandroid.um.capitulo.projeto.modelo.Pedido;

/**
 * Created by Katrina on 04/12/2015.
 */
public class PedidoAdapter extends BaseAdapter {
    private Context context;
    private List<Pedido> pedidoList;
    private LayoutInflater inflater;

    public PedidoAdapter(Context context, List<Pedido> pedidoList) {
        this.context = context;
        this.pedidoList = pedidoList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pedidoList.size();
    }

    @Override
    public Object getItem(int position) {
        return pedidoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.pedido_item_list, null);
        Pedido pedido = pedidoList.get(position);
        TextView txtUser = (TextView) view.findViewById(R.id.usuario_pedido_user);
        txtUser.setText(pedido.getUser());
        TextView txtID = (TextView) view.findViewById(R.id.id_pedido_user);
        txtID.setText("Numero do Pedido:" + pedido.getId());
        TextView txtValorPedido = (TextView) view.findViewById(R.id.valor_pedido_user);
        txtValorPedido.setText("" + pedido.getValorPedido());
        return view;
    }
}
