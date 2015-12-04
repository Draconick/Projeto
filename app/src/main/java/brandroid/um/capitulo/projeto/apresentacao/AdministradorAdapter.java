package brandroid.um.capitulo.projeto.apresentacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import brandroid.um.capitulo.projeto.R;
import brandroid.um.capitulo.projeto.modelo.Administrador;

/**
 * Created by Katrina on 04/12/2015.
 */
public class AdministradorAdapter extends BaseAdapter {
    private Context context;
    private List<Administrador> administradorList;
    private LayoutInflater inflater;
    public AdministradorAdapter(Context context, List<Administrador> administradorList){
        this.context = context;
        this.administradorList = administradorList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return administradorList.size();
    }

    @Override
    public Object getItem(int position) {
        return administradorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.user_item_list,null);
        Administrador administrador = administradorList.get(position);
        TextView textUser = (TextView) view.findViewById(R.id.user_list);
        textUser.setText(administrador.getUser());
        TextView textNome = (TextView) view.findViewById(R.id.list_nome_user);
        textNome.setText(administrador.getNome());
        TextView textEmail = (TextView) view.findViewById(R.id.list_email_user);
        textEmail.setText(administrador.getEmail());
        TextView textDataNasc = (TextView) view.findViewById(R.id.list_datanasc_user);
        textDataNasc.setText(administrador.getDatanasc());
        TextView textTelefone = (TextView) view.findViewById(R.id.list_telefone_user);
        textTelefone.setText(administrador.getTelefone());
        TextView textSenha = (TextView) view.findViewById(R.id.list_senha_user);
        textSenha.setText(administrador.getSenha());
        return view;
    }
}
