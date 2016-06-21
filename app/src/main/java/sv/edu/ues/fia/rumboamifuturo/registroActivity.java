package sv.edu.ues.fia.rumboamifuturo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import java.util.Date;

import bdrumbo.DaoMaster;
import bdrumbo.DaoSession;

import bdrumbo.Usuario;
import bdrumbo.UsuarioDao;

public class registroActivity extends Activity implements Validator.ValidationListener{

    private ActionProcessButton btnregistro;
    Validator validator;

    private SQLiteDatabase db;
    //Se asegura que el campo no esté vacio
    @Required(order=1)
    EditText nomb;
    @Required(order=2)
    @Email(order=3,message = "Introduzca un correo válido")
    EditText corr;
    @Password(order=4)
    @TextRule(order=5, minLength =6,maxLength = 15, message = "Use al menos 6 caracteres.")
    EditText contra;
    @ConfirmPassword(order = 6)
    EditText contra2;


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
        helper = new DaoMaster.DevOpenHelper(this, "bd02", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        btnregistro = (ActionProcessButton) findViewById(R.id.button);
        btnregistro.setMode(ActionProcessButton.Mode.ENDLESS);

        validator = new Validator(this);
        //Llama el método listener de validator
        validator.setValidationListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    @Override
    public void onValidationFailed(View view, Rule<?> rule) {

        final String failureMessage = rule.getFailureMessage();
        if (view instanceof EditText) {
            EditText failed = (EditText) view;
            failed.requestFocus();
            failed.setError(failureMessage);
        } else {
            Toast.makeText(getApplicationContext(), failureMessage, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Datos válidos!", Toast.LENGTH_SHORT).show();
    }



    public void registrar(View v) {
        validator.validate();
        UsuarioDao usuari = daoSession.getUsuarioDao();
        Context ctx = this;
        if (contra.getText().toString().equals(contra2.getText().toString())) {
            if (corr.getText().toString() != null && contra.getText().toString() != null && nomb.getText().toString() != null) {
                //String correo, String nombre, String password, Boolean activo, String urlFoto, String resultTest, java.util.Date fechaCreacion
                Date fecha = new Date();
                fecha.getDate();
                Usuario usu = new Usuario(corr.getText().toString(), nomb.getText().toString(), contra.getText().toString(), true, null, null, fecha);
                try {
                    usuari.insert(usu);
                    Intent inte = new Intent(this, IngresarPerfilActivity.class);
                    startActivity(inte);//Context contexto = this;
                } catch (Exception e) {
                    Toast.makeText(ctx, "Error, correo ya esta en uso", Toast.LENGTH_LONG).show();
                }

                btnregistro.setProgress(1);
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        registroActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                UsuarioDao usuari = daoSession.getUsuarioDao();
                                Context ctx = getApplicationContext();
                                if (contra.getText().toString().equals(contra2.getText().toString())) {
                                    if (corr.getText().toString() != null && contra.getText().toString() != null && nomb.getText().toString() != null) {
                                        //String correo, String nombre, String password, Boolean activo, java.util.Date fechaCreacion
                                        Date fecha = new Date();
                                        fecha.getDate();
                                        Usuario usu = new Usuario();
                                        //(corr.getText().toString(), null, null, true, fecha);
                                        usu.setCorreo(corr.getText().toString());
                                        usu.setNombre(nomb.getText().toString());
                                        usu.setPassword(contra.getText().toString());
                                        usu.setActivo(true);
                                        usu.setUrlFoto(null);
                                        usu.setFechaCreacion(fecha);
                                        try {
                                            usuari.insert(usu);
                                            Intent inte = new Intent(getApplicationContext(), IngresarPerfilActivity.class);
                                            startActivity(inte);//Context contexto = this;
                                        } catch (Exception e) {
                                            Toast.makeText(ctx, "Error, correo ya esta en uso", Toast.LENGTH_LONG).show();
                                        }

                                    } else {
                                        Toast.makeText(ctx, "Error, campos vacios", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(ctx, "Error, contraseña no coincide", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                }).start();


            }
        }
    }}