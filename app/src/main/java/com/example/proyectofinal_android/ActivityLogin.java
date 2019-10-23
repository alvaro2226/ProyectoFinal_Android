package com.example.proyectofinal_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    private EditText editText_nombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        //editText_nombreUsuario.setText(getIntent().getExtras().getString("nombreUsuario",""));


    }

    private void initViews(){
        editText_nombreUsuario = findViewById(R.id.editTextUname);
    }
    public void iniciarIntentRegistro(View view) {
        startActivity(new Intent(this,ActivityRegistro.class));
    }
    public void iniciarIntentProductos(View v){
        startActivity(new Intent(this, ActivityListaProductos.class));
    }
}
