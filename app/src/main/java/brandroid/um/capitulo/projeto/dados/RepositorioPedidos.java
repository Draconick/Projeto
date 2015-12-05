package brandroid.um.capitulo.projeto.dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import brandroid.um.capitulo.projeto.modelo.Pedido;

/**
 * Created by Katrina on 04/12/2015.
 */
public class RepositorioPedidos {
    private PedidoOpenHelper pedidoOpenHelper;

    public RepositorioPedidos(Context contexto) {
        this.pedidoOpenHelper = new PedidoOpenHelper(contexto);
    }

    public void inserir(Pedido pedido){
        SQLiteDatabase db = pedidoOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", pedido.getUser());
        values.put("valorPedido", pedido.getValorPedido());
        values.put("lucropedido", pedido.getLucroPedido());
        db.insert(pedidoOpenHelper.TABELA_PEDIDO, null, values);
        db.close();
    }

    public List<Pedido> listarPorUsuario(String user){
        SQLiteDatabase db = pedidoOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(pedidoOpenHelper.TABELA_PEDIDO,
                new String[]{"id", "usuario", "valorPedido", "lucropedido"},
                "usuario = '" + user + "'", null, null, null, null);
        List<Pedido> pedidoList = new ArrayList<Pedido>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String usuario = cursor.getString(cursor.getColumnIndex("usuario"));
            double valorPedido = cursor.getDouble(cursor.getColumnIndex("valorpedido"));
            double lucropedido = cursor.getDouble(cursor.getColumnIndex("lucropedido"));

            Pedido pedido = new Pedido(id,usuario,valorPedido,lucropedido);
            pedidoList.add(pedido);
        }
        return pedidoList;
    }
    public List<Pedido> listar(){
        SQLiteDatabase db = pedidoOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(pedidoOpenHelper.TABELA_PEDIDO,
                new String[]{"id", "usuario", "valorPedido", "lucropedido"},
                null, null, null, null, null);
        List<Pedido> pedidoList = new ArrayList<Pedido>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String usuario = cursor.getString(cursor.getColumnIndex("usuario"));
            double valorPedido = cursor.getDouble(cursor.getColumnIndex("valorpedido"));
            double lucropedido = cursor.getDouble(cursor.getColumnIndex("lucropedido"));

            Pedido pedido = new Pedido(id,usuario,valorPedido,lucropedido);
            pedidoList.add(pedido);
        }
        return pedidoList;
    }
    public void deletar(){
        SQLiteDatabase db = pedidoOpenHelper.getWritableDatabase();
        db.execSQL("DELETE FROM "+ pedidoOpenHelper.TABELA_PEDIDO);
    }
}
