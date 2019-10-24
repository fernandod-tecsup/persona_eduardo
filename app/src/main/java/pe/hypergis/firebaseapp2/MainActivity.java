package pe.hypergis.firebaseapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView lblSaludo;
    private Button btnCambiar;
    private int contador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectar a la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Utilizar la referencia "saludo"
        final DatabaseReference myRef = database.getReference("saludo");

        contador = 1;
        lblSaludo = (TextView)findViewById(R.id.lblSaludo);
        btnCambiar = (Button)findViewById(R.id.btnCambiar);
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = "Cambio " + String.valueOf(contador);
                //Escribiendo en la base de datos
                myRef.setValue(texto);
                contador++;
            }
        });

        // Leer de la base de datos
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Capturando el valor de la referencia "saludo"
                String value = dataSnapshot.getValue(String.class);
                //Asignamos a la variable "lblSaludo"
                lblSaludo.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Enviar mensaje de error
            }
        });
    }
}
