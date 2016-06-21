package sv.edu.ues.fia.rumboamifuturo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Actividad que muestra la imagen del item extendida
 */
public class DetalleUcaActivity extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = "sv.edu.ues.fia.rumboamifuturo";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private Galeria itemDetallado;
    private Galeria itemDetalladouca;
    private Galeria itemDetalladoutec;

    private ImageView imagenExtendida;
    private ImageView imagenExtendidauca;
    private ImageView imagenExtendidautec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        usarToolbar();

        // Obtener la imagen con el identificador establecido en la actividad principal GaleriaActivity
        itemDetallado = Galeria.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        itemDetalladouca = Galeria.getItemUca(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        itemDetalladoutec = Galeria.getItemUtec(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);
        imagenExtendidauca = (ImageView) findViewById(R.id.imagen_extendida);
        imagenExtendidautec = (ImageView) findViewById(R.id.imagen_extendida);

        cargarImagenExtendidaUca();

    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);
    }
    private void cargarImagenExtendidaUca() {
        Glide.with(imagenExtendidauca.getContext())
                .load(itemDetalladouca.getIdDrawable())
                .into(imagenExtendidauca);
    }
    private void cargarImagenExtendidaUtec() {
        Glide.with(imagenExtendidautec.getContext())
                .load(itemDetalladoutec.getIdDrawable())
                .into(imagenExtendidautec);
    }

    private void usarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
