package sv.edu.ues.fia.rumboamifuturo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bdrumbo.DaoMaster;
import bdrumbo.DaoSession;
import bdrumbo.Universidad;
import bdrumbo.UniversidadDao;
import bdrumbo.Usuario;
import bdrumbo.UsuarioDao;

public class PefilActivity extends Activity {
    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
    //private File file = new File(ruta_fotos);
    TextView titulo;
    private ImageButton boton;
    final int FOTOGRAFIA = 654;
    ImageView image;
    Uri file;
    DaoMaster.DevOpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    private SQLiteDatabase db;
    private String correo;
    private EditText nombreP;
    private TextView testP;
    List<String> menu;
    Universidad universidadA;
    Universidad universidadB;
    List<Universidad> universidades;
    Spinner spi;
    Usuario usu;
    UsuarioDao usuari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pefil);
        boton = (ImageButton) findViewById(R.id.btnfoto);
        image = (ImageView) findViewById(R.id.image);
        titulo = (TextView) findViewById(R.id.textView2);
        nombreP = (EditText) findViewById(R.id.nombre);
        spi = (Spinner) findViewById(R.id.spinner);
        testP = (TextView) findViewById(R.id.test);
        boton.setOnClickListener(onClick);
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                image.setImageURI(Uri.parse(savedInstanceState.getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }/*inicia la extraccion de la base */
        helper = new DaoMaster.DevOpenHelper(this, "bd02", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        correo=getIntent().getExtras().getString("correo");
        usuari = daoSession.getUsuarioDao();//voy a la base y traigo al usuario
        usu =usuari.load(correo);
        testP.setText(usu.getResultTest());
        nombreP.setText(usu.getNombre());


        UniversidadDao universidad = daoSession.getUniversidadDao();
        menu=new ArrayList<String>();
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
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,menu);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi.setAdapter(adaptador);
    }


    public void onSaveInstanceState(Bundle bundle){
        if (file!=null){
            bundle.putString("Foto", file.toString());
        }
        super.onSaveInstanceState(bundle);
    }
    View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
// TODO Auto-generated method stub
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File photo =new File(Environment.getExternalStorageDirectory(),String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg");
            String val =Environment.getExternalStorageDirectory()+String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg";
            file=Uri.fromFile(photo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            startActivityForResult(intent, FOTOGRAFIA);
            //titulo.setText(correo);
        }
    };
    @Override
    public void onActivityResult(int RequestCode, int ResultCode, Intent intent) {
        if (RequestCode==FOTOGRAFIA){
            if(ResultCode == RESULT_OK){
                image.setImageURI(file);
            }
            else{
                Toast.makeText(getApplicationContext(),"fotografia No tomada",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void regresar(View v){
        Intent intent = new Intent(this,VistaPrincipal.class);
        startActivity(intent);
    }
}
