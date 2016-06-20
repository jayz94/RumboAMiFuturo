package sv.edu.ues.fia.rumboamifuturo;

import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bdrumbo.DaoMaster;
import bdrumbo.DaoSession;
import bdrumbo.Universidad;
import bdrumbo.UniversidadDao;
import bdrumbo.UsuarioDao;

public class ListaUniversidadesActivity extends ListActivity {
    private SQLiteDatabase db;
    int cont=1;
    DaoMaster.DevOpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    List<Universidad> universidades;
    Universidad universidadA;
    Universidad universidadB;
    List<String> menu;
    List<String> menuId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DaoMaster.DevOpenHelper(this, "bd02", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        UniversidadDao universidad = daoSession.getUniversidadDao();
        menu=new ArrayList<String>();
        menuId=new ArrayList<String>();
        /*ingreso datos a la base*/
        //String abreviatura, String nombreUni, String descripcion, String ubicacion, String universidadAbrev
        try{
            universidadA=new Universidad("UES","Universidad de El Salvador","Universidad nacional de el salvador: hacia la libertad por la cultura","San Salvador","");
            universidad.insert(universidadA);
            universidadB=new Universidad("UCA","Universidad CentroAmericana","La universidad con valores morales y civicos","San Salvador","");
            universidad.insert(universidadB);
        }catch(SQLException e){}
        menu.add("<< Universidades >>");
        universidades=universidad.loadAll();
       for (Universidad u: universidades){
            menu.add(u.getNombreUni());
           menuId.add(u.getAbreviatura());
       }
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        /*para agregar el menu al activity*/
        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
            try {
                Intent inte =  new Intent(this, InformacionUniversidadActivity.class);
                inte.putExtra("id",menuId.get(position-1));//envio parametro correo
                startActivity(inte);//Context contexto = this;
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
