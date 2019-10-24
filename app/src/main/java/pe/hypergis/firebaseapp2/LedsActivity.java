package pe.hypergis.firebaseapp2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pe.hypergis.firebaseapp2.model.Leds;
import pe.hypergis.firebaseapp2.model.Persona;

public class LedsActivity extends AppCompatActivity {
    private ImageView imgLed1;
    private ImageView imgLed2;
    private ImageView imgLed3;
    private Button btnLedOn1;
    private Button btnLedOn2;
    private Button btnLedOn3;
    private Button btnLedOff1;
    private Button btnLedOff2;
    private Button btnLedOff3;
    private Button btnBuscar;
    private Leds leds;
    private Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leds);

        btnBuscar = (Button)findViewById(R.id.btnbuscar);

      /*  imgLed1 = (ImageView)findViewById(R.id.imgLed1);
        imgLed2 = (ImageView)findViewById(R.id.imgLed2);
        imgLed3 = (ImageView)findViewById(R.id.imgLed3);
        btnLedOn1 = (Button)findViewById(R.id.btnLedOn1);
        btnLedOn2 = (Button)findViewById(R.id.btnLedOn2);
        btnLedOn3 = (Button)findViewById(R.id.btnLedOn3);
        btnLedOff1 = (Button)findViewById(R.id.btnLedOff1);
        btnLedOff2 = (Button)findViewById(R.id.btnLedOff2);
        btnLedOff3 = (Button)findViewById(R.id.btnLedOff3); */

        // Conectar a la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Utilizar la referencia "Persona"
        //final DatabaseReference myRef = database.getReference("leds");
        final DatabaseReference myRef2= database.getReference("persona");

        // Leer de la base de datos
        //myRef.addValueEventListener(new ValueEventListener() {
           // @Override
            //public void onDataChange(DataSnapshot dataSnapshot) {
                //Capturando el valor de la referencia "saludo"
              //  leds = dataSnapshot.getValue(Leds.class);
               // if(leds.led1.equals("1")){
                //    imgLed1.setImageResource(R.drawable.led_on_blue);
                //}else{
                 //   imgLed1.setImageResource(R.drawable.led_off);
               // }

               // if(leds.led2.equals("1")){
               //     imgLed2.setImageResource(R.drawable.led_on_orange);
               // }else{
                //    imgLed2.setImageResource(R.drawable.led_off);
               // }

               // if(leds.led3.equals("1")){
                //    imgLed3.setImageResource(R.drawable.led_on_yellow);
               // }else{
                //    imgLed3.setImageResource(R.drawable.led_off);
               // }
           // }

           // @Override
            //public void onCancelled(DatabaseError error) {
                // Enviar mensaje de error
            //}
       // });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                persona = dataSnapshot.getValue(Persona.class);
                if(persona.estado.equals("0")){
                    Toast.makeText(LedsActivity.this, "Fuera de la oficina", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LedsActivity.this, "Dentro de la oficina", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(LedsActivity.this, "error en la consulta", Toast.LENGTH_SHORT).show();

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (persona.estado.equals("0")){
                    myRef2.setValue(leds);
                }else {
                    myRef2.setValue(leds);
                }
            }
        });

        /* btnLedOn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led1 = "1";
                myRef.setValue(leds);
            }
        });
        btnLedOff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led1 = "0";
                myRef.setValue(leds);
            }
        });
        btnLedOn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led2 = "1";
                myRef.setValue(leds);
            }
        });
        btnLedOff2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led2 = "0";
                myRef.setValue(leds);
            }
        });
        btnLedOn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led3 = "1";
                myRef.setValue(leds);
            }
        });
        btnLedOff3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leds.led3 = "0";
                myRef.setValue(leds);
            }
        });*/
    }
}
