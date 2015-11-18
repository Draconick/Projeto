package brandroid.um.capitulo.projeto;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Katrina on 17/11/2015.
 */
public class CadastroUsuarioActivity extends Activity {

    private int ano, mes, dia;
    private Button dataCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_cadastro_layout);

        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        dataCadastro = (Button) findViewById(R.id.cadastro_data);
        dataCadastro.setText(dia + "/" + (mes+1) + "/" + ano);
    }

    public void selecionarData(View view){
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(R.id.cadastro_data == id) {
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth){
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataCadastro.setText(dia + "/" + (mes+1) + "/" + ano);
        }
    };
}
