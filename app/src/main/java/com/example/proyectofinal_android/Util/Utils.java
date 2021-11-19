package com.example.proyectofinal_android.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Utils {

    public static final String url_DB = "jdbc:mysql://153.92.220.151/u544052383_ordertracker";
    public static final String usuario_DB = "u544052383_alvaromb";
    public static final String contra_DB = "OrderTracker2021";


    public static Bitmap descargarImagen(String ruta) throws IOException {


        String rutaFinal = "https://programaloalvaro.es/archivos/ordertracker/imagenes/" + ruta;

        URL url = new URL(rutaFinal);
        URLConnection connection = url.openConnection();
        connection.connect();

        InputStream input = new BufferedInputStream(url.openStream());
        Bitmap imagen = BitmapFactory.decodeStream(input);

        return imagen;
    }

}
