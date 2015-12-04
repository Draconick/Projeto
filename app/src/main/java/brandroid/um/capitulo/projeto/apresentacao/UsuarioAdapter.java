package brandroid.um.capitulo.projeto.apresentacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import brandroid.um.capitulo.projeto.R;
import brandroid.um.capitulo.projeto.modelo.Usuario;

/**
 * Created by Katrina on 03/12/2015.
 */
public class UsuarioAdapter extends BaseAdapter {
    private Context context;
    private List<Usuario> usuarioList;
    private LayoutInflater inflater;
    public UsuarioAdapter(Context context, List<Usuario> usuarioList){
        this.context = context;
        this.usuarioList = usuarioList;
        this.inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return usuarioList.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.user_item_list, null);
        Usuario usuario = usuarioList.get(position);
        TextView textUser = (TextView) view.findViewById(R.id.user_list);
        textUser.setText(usuario.getUser());
        TextView textNome = (TextView) view.findViewById(R.id.list_nome_user);
        textNome.setText(usuario.getNome());
        TextView textEmail = (TextView) view.findViewById(R.id.list_email_user);
        textEmail.setText(usuario.getEmail());
        TextView textDataNasc = (TextView) view.findViewById(R.id.list_datanasc_user);
        textDataNasc.setText(usuario.getDatanasc());
        TextView textTelefone = (TextView) view.findViewById(R.id.list_telefone_user);
        textTelefone.setText(usuario.getTelefone());
        TextView textSenha = (TextView) view.findViewById(R.id.list_senha_user);
        textSenha.setText(usuario.getSenha());

        return view;
    }
}
