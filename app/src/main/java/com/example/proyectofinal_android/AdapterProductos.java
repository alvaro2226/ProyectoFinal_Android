package com.example.proyectofinal_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal_android.Pojos.Producto;

import java.util.ArrayList;

public class AdapterProductos extends ArrayAdapter<Producto> {

    ArrayList<Producto> productos;
    Context context;

    public AdapterProductos(ArrayList<Producto> data, Context context) {
        super(context, R.layout.list_design, data);
        this.productos = data;
        this.context=context;
    }

    private static class ViewHolder {
        TextView textView_nombre;
        TextView textView_Descripcion;
        ImageButton botonAñadir;
        ImageView imagenProducto;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Producto producto = productos.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_design, parent, false);
            viewHolder.textView_nombre = (TextView) convertView.findViewById(R.id.textView_productoNombre);
            viewHolder.textView_Descripcion = (TextView) convertView.findViewById(R.id.textView_productoDescripcion);
            viewHolder.imagenProducto = (ImageView) convertView.findViewById(R.id.imageView_imagenProducto);
            viewHolder.botonAñadir = (ImageButton) convertView.findViewById(R.id.botonAnadir);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.textView_nombre.setText(producto.getNombre());
        viewHolder.textView_Descripcion.setText(producto.getDescripcion());
        viewHolder.imagenProducto.setImageResource(R.drawable.logo);
        viewHolder.botonAñadir.setImageDrawable(getContext().getResources().getDrawable(android.R.drawable.ic_input_add,null));

        viewHolder.botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"HAS PULSADO EL BOTON " + position,Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase();

        //Log.e("Filter string: ", charText);
        //Log.e("Productos size:  ", productos.size() + "");
        //Log.e("ProductosAux size:  ", ActivityListaProductos.productosAux.size() + "");

        productos.clear();
        if (charText.length() == 0) {
            productos.addAll(ActivityListaProductos.productosAux);
        } else {

            //Log.e("ProductosAux","Entra en 1");
            //Log.e("ProductosAux size:  ", ActivityListaProductos.productosAux.size() + "");

            for (int i = 0; i < ActivityListaProductos.productosAux.size() ; i ++) {

                //Log.e("ProductosAux nombre", ActivityListaProductos.productosAux.get(i).getNombre().toLowerCase());

                if (ActivityListaProductos.productosAux.get(i).getNombre().toLowerCase().contains(charText)) {
                    //Log.e("ProductosAux contiene  ", charText);
                    productos.add(ActivityListaProductos.productosAux.get(i));
                }

            }
        }
        notifyDataSetChanged();
    }
}
