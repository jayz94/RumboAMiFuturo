package sv.edu.ues.fia.rumboamifuturo;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class VistaPrincipal extends AppCompatActivity {
    ImageButton imageButtonRegistro;
    ImageButton imageButtonListaU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);
        imageButtonRegistro=(ImageButton)findViewById(R.id.imageButton);
        imageButtonListaU=(ImageButton)findViewById(R.id.imageButton2);

        imageButtonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1= new Intent(VistaPrincipal.this,registroActivity.class);
                startActivity(intent1);
            }
        });

        imageButtonListaU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VistaPrincipal.this,ItemListActivity.class);
                startActivity(intent);
            }
        });
    }

}
