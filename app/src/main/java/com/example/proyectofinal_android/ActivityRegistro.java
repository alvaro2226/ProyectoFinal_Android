package com.example.proyectofinal_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityRegistro extends AppCompatActivity {

    private EditText editText_nombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        initViews();
    }

    private void initViews() {
        editText_nombreUsuario = findViewById(R.id.editText_nombreUsuario);
    }

    public void confirmarRegistro(View view) {

        //Si los datos son correctos
        Intent intent = new Intent(this, ActivityLogin.class);
        intent.putExtra("nombreUsuario", editText_nombreUsuario.getText().toString());
        startActivity(intent);
    }
}
