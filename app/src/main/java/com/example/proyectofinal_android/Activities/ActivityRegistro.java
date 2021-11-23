package com.example.proyectofinal_android.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal_android.Internet.OperacionesDB;
import com.example.proyectofinal_android.Pojos.Usuario;
import com.example.proyectofinal_android.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ActivityRegistro extends AppCompatActivity {

    private ArrayList<EditText> campos = new ArrayList<>();
    private EditText editText_nombreUsuario;
    private EditText editText_email;
    private EditText editText_contra;
    private EditText editText_nombre;
    private EditText editText_apellidos;
    private EditText editText_telefono;
    private EditText editText_calle;
    private EditText editText_localidad;
    private EditText editText_provincia;
    private EditText editText_codigopostal;
    private EditText editText_pais;
    private Button botonCancelar;
    private Button botonVolverAtras;

    public static Usuario usuarioLogueado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        initViews();

        if (getIntent().hasExtra("comprobarMisDatos")) {


            //hace invisible el boton cancelar
            botonCancelar.setEnabled(false);
            botonCancelar.setVisibility(View.INVISIBLE);
            //el boton confirmar ahora es volver atrás
            botonVolverAtras.setText("Volver");
            //bloquea los campos de texto
            editText_nombreUsuario.setEnabled(false);
            editText_email.setEnabled(false);
            editText_contra.setEnabled(false);
            editText_nombre.setEnabled(false);
            editText_apellidos.setEnabled(false);
            editText_telefono.setEnabled(false);
            editText_calle.setEnabled(false);
            editText_localidad.setEnabled(false);
            editText_provincia.setEnabled(false);
            editText_codigopostal.setEnabled(false);
            editText_pais.setEnabled(false);

            //Consulta a la base de datos los datos del usuario logueado
            usuarioLogueado = new Usuario();
            OperacionesDB thread = new OperacionesDB(this, OperacionesDB.GET_DATOS_USUARIO_LOGUEADO);
            thread.execute();
            try {
                thread.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            editText_nombreUsuario.setText(usuarioLogueado.getNombreUsuario());
            editText_email.setText(usuarioLogueado.getEmail());
            editText_contra.setText(usuarioLogueado.getContra());
            editText_nombre.setText(usuarioLogueado.getNombre());
            editText_apellidos.setText(usuarioLogueado.getApellidos());
            editText_telefono.setText(usuarioLogueado.getTelefono());
            editText_calle.setText(usuarioLogueado.getCalle());
            editText_localidad.setText(usuarioLogueado.getLocalidad());
            editText_provincia.setText(usuarioLogueado.getProvincia());
            editText_codigopostal.setText(usuarioLogueado.getCp());
            editText_pais.setText(usuarioLogueado.getPais());


        }

    }

    private void initViews() {

        editText_nombreUsuario = findViewById(R.id.editText_nombreUsuario);
        editText_email = findViewById(R.id.editText_email);
        editText_contra = findViewById(R.id.editText_contra);
        editText_nombre = findViewById(R.id.editText_nombre);
        editText_apellidos = findViewById(R.id.editText_apellidos);
        editText_telefono = findViewById(R.id.editText_telefono);
        editText_calle = findViewById(R.id.editText_calle);
        editText_localidad = findViewById(R.id.editText_Localidad);
        editText_provincia = findViewById(R.id.editText_provincia);
        editText_codigopostal = findViewById(R.id.editText_cp);
        editText_pais = findViewById(R.id.editText_pais);
        botonCancelar = findViewById(R.id.botonCancelar);
        botonVolverAtras = findViewById(R.id.button2);

        campos.add(editText_nombre);
        campos.add(editText_email);
        campos.add(editText_contra);
        campos.add(editText_apellidos);
        campos.add(editText_telefono);
        campos.add(editText_calle);
        campos.add(editText_localidad);
        campos.add(editText_provincia);
        campos.add(editText_codigopostal);
        campos.add(editText_pais);
        campos.add(editText_nombreUsuario);

    }

    public void confirmarRegistro(View view) {

        //Si el usuario ya ha logueado
        if (getIntent().hasExtra("comprobarMisDatos")) {
            startActivity(new Intent(this,ActivityListaProductos.class));

        }else{
            //Está registrandose

            //Si los datos son correctos
            if (comprobarCampos()) {
                new OperacionesDB(this, OperacionesDB.AÑADIR_USUARIO, editText_calle.getText().toString(), editText_localidad.getText().toString(),
                        editText_provincia.getText().toString(), editText_codigopostal.getText().toString(), editText_pais.getText().toString(), editText_nombre.getText().toString(),
                        editText_nombreUsuario.getText().toString(), editText_contra.getText().toString(), editText_email.getText().toString(), editText_apellidos.getText().toString(),
                        editText_telefono.getText().toString(), editText_codigopostal.getText().toString()).execute();

                Intent intent = new Intent(this, ActivityLogin.class);
                intent.putExtra("nombreUsuario", editText_nombreUsuario.getText().toString());
                intent.putExtra("contraseña", editText_contra.getText().toString());

                startActivity(intent);

            } else {
                Toast.makeText(this, "Compruebe que no hay campos vacíos.", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void cancelarRegistro(View view) {
        startActivity(new Intent(this, ActivityLogin.class));
    }

    private boolean comprobarCampos() {
        boolean todoBien = true;

        for (EditText campo : campos) {
            if (campo.getText().toString().equals("") || campo.getText().toString() == null) {
                todoBien = false;
            }
        }
        return todoBien;
    }
}
