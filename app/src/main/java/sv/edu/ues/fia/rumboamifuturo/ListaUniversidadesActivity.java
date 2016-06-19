package sv.edu.ues.fia.rumboamifuturo;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaUniversidadesActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_universidades);
        /*para agregar el menu al activity*/
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
            try {
                Intent inte =  new Intent(this, InformacionUniversidadActivity.class);
                startActivity(inte);//Context contexto = this;
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
