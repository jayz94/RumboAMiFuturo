package sv.edu.ues.fia.rumboamifuturo;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class VistaPrincipal extends AppCompatActivity {
    MediaPlayer Media;
    ImageButton imageButtonRegistro;
    ImageButton imageButtonListaU;
    ImageButton imageButtonAudio;
    ImageButton imageButtonMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);

        Media=MediaPlayer.create(getApplicationContext(),R.raw.audio_pag_principal);
        imageButtonAudio=(ImageButton)findViewById(R.id.imageButton3);
        imageButtonRegistro=(ImageButton)findViewById(R.id.imageButton);
        imageButtonListaU=(ImageButton)findViewById(R.id.imageButton2);
        imageButtonMapa=(ImageButton)findViewById(R.id.imageButton4);
        imageButtonAudio.setOnClickListener(onclick);
        imageButtonRegistro.setOnClickListener(onclick);
        imageButtonListaU.setOnClickListener(onclick);
        imageButtonMapa.setOnClickListener(onclick);

    }
    View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageButton:{
                    Intent intent1=new Intent(VistaPrincipal.this,IngresarPerfilActivity.class);
                    startActivity(intent1);
                    break;
                }

                case  R.id.imageButton2:{
                    Intent intent2=new Intent(VistaPrincipal.this,ListaUniversidadesActivity.class);
                    startActivity(intent2);
                    break;
                }
                case  R.id.imageButton4:{
                    Intent intent2=new Intent(VistaPrincipal.this,Mapa.class);
                    startActivity(intent2);
                    break;
                }


                case R.id.imageButton3:
                {
                    if(Media.isPlaying()){
                        Media.pause();
                        imageButtonAudio.setImageResource(R.drawable.ic_action_name4);
                    }else{
                        Media.start();
                        imageButtonAudio.setImageResource(R.drawable.ic_action_name5);
                    }
                    break;
                }


            }


        }
    };

}
