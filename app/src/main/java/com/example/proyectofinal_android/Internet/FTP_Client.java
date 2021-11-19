package com.example.proyectofinal_android.Internet;


import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
public class FTP_Client {

    public static final String server = "programaloalvaro.es";
    private final int port = 21;
    private final String user = "u544052383.alvaromb";
    private final String password = "OrderTracker2021";
    //public static final String RUTA_IMAGENES = "fotos/productos/";
    private FTPClient ftp;

    public void iniciarConexion() throws IOException {

        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftp.connect(server, port);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);

        int respuesta = ftp.getReplyCode();

        if (!FTPReply.isPositiveCompletion(respuesta)) {
            ftp.disconnect();

            System.out.println("Error al conectar al servidor FTP \n "
                    + "URL: " + server + ":" + port + "\n "
                    + "Usuario: " + user + "\n "
                    + "Contraseña: " + password);


            throw new IOException("Error al conectar al servidor FTP");

        }

        ftp.login(user, password);
    }

    public void cerrarConexion() throws IOException {
        ftp.disconnect();
    }

    public void descargarArchivo(String source, String destination) throws IOException {
        FileOutputStream out = new FileOutputStream(destination);
        ftp.retrieveFile(source, out);
    }

    public void subirArchivo(File file, String path) throws IOException {

        if (file != null){
            ftp.storeFile(path, new FileInputStream(file));
        }

    }

    public void borrarArchivo(String ruta) throws IOException {
        ftp.deleteFile(ruta);
    }

}
