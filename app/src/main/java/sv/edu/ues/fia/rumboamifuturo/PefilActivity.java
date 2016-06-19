package sv.edu.ues.fia.rumboamifuturo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bdrumbo.DaoMaster;
import bdrumbo.DaoSession;
import bdrumbo.Usuario;

public class PefilActivity extends Activity {
    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
    //private File file = new File(ruta_fotos);
    TextView titulo;
    private Button boton;
    final int FOTOGRAFIA = 654;
    ImageView image;
    Uri file;
    DaoMaster.DevOpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    private SQLiteDatabase db;
    private String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pefil);
        boton = (Button) findViewById(R.id.mainbttomarfoto);
        image = (ImageView) findViewById(R.id.image);
        titulo = (TextView) findViewById(R.id.textView2);
        boton.setOnClickListener(onClick);
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                image.setImageURI(Uri.parse(savedInstanceState.getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }/*inicia la extraccion de la base */
        helper = new DaoMaster.DevOpenHelper(this, "bd01", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        //correo=getIntent().getExtras().getString("correo");
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
}

    /*    setContentView(R.layout.activity_pefil);
        //======== codigo nuevo ========
        boton = (Button) findViewById(R.id.btnfoto);
        //Si no existe crea la carpeta donde se guardaran las fotos
        file.mkdirs();
        //accion para el boton
        boton.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
         String file = ruta_fotos + getCode() + ".jpg";
         File mi_foto = new File( file );
         try {
               mi_foto.createNewFile();
               } catch (IOException ex) {
                    Log.e("ERROR ", "Error:" + ex);
                    }
               Uri uri = Uri.fromFile(mi_foto);
               //Abre la camara para tomar la foto
               Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Guarda imagen
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                //Retorna a la actividad
                startActivityForResult(cameraIntent, 0);
                } });
       //====== codigo nuevo:end ======
       }// Metodo privado que genera un codigo unico segun la hora y fecha del sistema @return photoCode
      @SuppressLint("SimpleDateFormat")
    private String getCode()
     {
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date() );
        String photoCode = "foto_" + date;
         return photoCode;
      }
}
*/
