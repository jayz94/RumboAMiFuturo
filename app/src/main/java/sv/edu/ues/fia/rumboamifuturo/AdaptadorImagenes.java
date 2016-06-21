package sv.edu.ues.fia.rumboamifuturo;

/**
 * Created by Serpas on 19/6/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * {@link BaseAdapter} para poblar imagenes en un grid view
 */

public class AdaptadorImagenes extends BaseAdapter {
    private Context context;

    public AdaptadorImagenes(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Galeria.ItemsUes().length;
    } //modificado solo para Ues con ItemsUes()

    @Override
    public Galeria getItem(int position) {
        return Galeria.ItemsUes()[position];
    } //modificado solo para Ues con ItemsUes()


    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenCoche = (ImageView) view.findViewById(R.id.imagen);
        TextView nombreCoche = (TextView) view.findViewById(R.id.nombre_imag);

        final Galeria item = getItem(position);
        Glide.with(imagenCoche.getContext())
                .load(item.getIdDrawable())
                .into(imagenCoche);

        nombreCoche.setText(item.getNombre());

        return view;
    }

}