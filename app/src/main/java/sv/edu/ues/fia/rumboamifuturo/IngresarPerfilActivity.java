package sv.edu.ues.fia.rumboamifuturo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bdrumbo.DaoMaster;
import bdrumbo.DaoSession;
import bdrumbo.Usuario;
import bdrumbo.UsuarioDao;

public class IngresarPerfilActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    EditText contra;
    EditText corr;
    EditText nomb;
    DaoMaster.DevOpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_perfil);
        corr = (EditText) findViewById(R.id.correo);
        contra = (EditText) findViewById(R.id.contraseña);
        nomb = (EditText) findViewById(R.id.nombre);
        helper = new DaoMaster.DevOpenHelper(this, "bd01", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    public void ingresar(View v) {
        UsuarioDao usuari = daoSession.getUsuarioDao();
        if(corr.getText().toString().equals("") || contra.getText().toString().equals("")){
                Toast.makeText(this, "Error, campos vacios", Toast.LENGTH_LONG).show();
            }
            else{
            try{
                //Se obtiene un usuario con id = i mediante el método load(long i)
                Usuario usu =usuari.load(corr.getText().toString());
                String contraseñaIn=contra.getText().toString();
                String contraseñaOri=usu.getPassword();
                if(contraseñaOri.equals(contraseñaIn)){
                    setContentView(R.layout.activity_pefil);/*me equivoque y puse pefil XD*/

                }else
                    Toast.makeText(this, "Error,contraseña incorrecta", Toast.LENGTH_LONG).show();

            }catch(Exception e ){
                Toast.makeText(this, "Error, datos incorrectos", Toast.LENGTH_LONG).show();
            }

            }
    }
}
