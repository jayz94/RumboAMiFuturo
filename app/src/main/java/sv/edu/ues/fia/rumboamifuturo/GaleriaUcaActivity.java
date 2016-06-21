package sv.edu.ues.fia.rumboamifuturo;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GaleriaUcaActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private AdaptadorImagenesUca adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        usarToolbar();

        gridView = (GridView) findViewById(R.id.grid);
        adaptador = new AdaptadorImagenesUca(this);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(this);
    }

    private void usarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Galeria item = (Galeria) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, DetalleUcaActivity.class);
        intent.putExtra(DetalleUcaActivity.EXTRA_PARAM_ID, item.getId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptionsCompat activityOptions =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this,
                            new Pair<View, String>(view.findViewById(R.id.imagen),
                                    DetalleUcaActivity.VIEW_NAME_HEADER_IMAGE)
                    );

            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        } else
            startActivity(intent);
    }

}