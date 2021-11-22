package com.example.proyectofinal_android.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.proyectofinal_android.Internet.OperacionesDB;
import com.example.proyectofinal_android.R;

public class ActivityLogin extends AppCompatActivity {

    private EditText editText_nombreUsuario;
    private EditText editText_contra;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();


        intent = getIntent();
        //Si se acaba de crear un usuario
            if(intent.hasExtra("nombreUsuario") && intent.hasExtra("contraseña")){
                editText_nombreUsuario.setText(intent.getStringExtra("nombreUsuario"));
                editText_contra.setText(intent.getStringExtra("contraseña"));
            }
        //editText_nombreUsuario.setText(getIntent().getExtras().getString("nombreUsuario",""));


    }

    private void initViews() {
        editText_nombreUsuario = findViewById(R.id.editTextNombreUsuario);
        editText_contra = findViewById(R.id.editText_password);

        //iniciarSpinner();
    }

    private void iniciarSpinner() {
        //Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        //spinner.setAdapter(adapter);

    }

    public void iniciarIntentRegistro(View view) {
        startActivity(new Intent(this, ActivityRegistro.class));

    }

    public static int idUsuarioLogueado = 0;
    public void iniciarIntentProductos(View v) {
        //startActivity(new Intent(this, ActivityListaProductos.class));
        new OperacionesDB(this, OperacionesDB.COMPROBAR_USUARIO, editText_nombreUsuario.getText().toString(), editText_contra.getText().toString()).execute();
    }
}
