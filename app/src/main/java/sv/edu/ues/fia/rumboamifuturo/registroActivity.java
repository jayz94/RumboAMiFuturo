package sv.edu.ues.fia.rumboamifuturo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;

import bdrumbo.DaoMaster;
import bdrumbo.DaoSession;
import bdrumbo.Usuario;
import bdrumbo.UsuarioDao;

public class registroActivity extends Activity {
    private SQLiteDatabase db;
    EditText contra;
    EditText contra2;
    EditText corr;
    EditText nomb;
    DaoMaster.DevOpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        corr = (EditText) findViewById(R.id.correo);
        contra = (EditText) findViewById(R.id.contraseña);
        contra2 = (EditText) findViewById(R.id.contraseña2);
        nomb = (EditText) findViewById(R.id.nombre);
        helper = new DaoMaster.DevOpenHelper(this, "bd01", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void registrar(View v) {
        UsuarioDao usuari = daoSession.getUsuarioDao();
        Context ctx = this;
        if(contra.getText().toString().equals(contra2.getText().toString())){
            if(corr.getText().toString()!=null && contra.getText().toString()!=null && nomb.getText().toString()!=null){
                //String correo, String nombre, String password, Boolean activo, java.util.Date fechaCreacion
                Date fecha = new Date();
                fecha.getDate();
                Usuario usu = new Usuario(corr.getText().toString(), nomb.getText().toString(), contra.getText().toString(), true, fecha);
                try{
                    usuari.insert(usu);
                    Intent inte =  new Intent(this, IngresarPerfilActivity.class);
                    startActivity(inte);//Context contexto = this;
                }catch(Exception e){
                    Toast.makeText(ctx, "Error, correo ya esta en uso", Toast.LENGTH_LONG).show();
                }

            }
            else{
                Toast.makeText(ctx, "Error, campos vacios", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(ctx, "Error, contraseña no coincide", Toast.LENGTH_LONG).show();
        }

    }



    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "registro Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sv.edu.ues.fia.rumboamifuturo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "registro Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://sv.edu.ues.fia.rumboamifuturo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
