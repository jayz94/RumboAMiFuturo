package sv.edu.ues.fia.rumboamifuturo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import bdrumbo.DaoMaster;
import bdrumbo.DaoSession;
import bdrumbo.Universidad;
import bdrumbo.UniversidadDao;
import bdrumbo.Usuario;

public class InformacionUniversidadActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    DaoMaster.DevOpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    Universidad universidad;
    UniversidadDao universidadDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_universidad);
        helper = new DaoMaster.DevOpenHelper(this, "bd02", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        universidadDao = daoSession.getUniversidadDao();
        universidad =universidadDao.load(getIntent().getExtras().getString("id"));//esto es para extraer el parametro que envie con el activity
    }
}
