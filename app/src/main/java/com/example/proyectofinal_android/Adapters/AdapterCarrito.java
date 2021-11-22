package com.example.proyectofinal_android.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinal_android.Activities.ActivityCarrito;
import com.example.proyectofinal_android.Activities.ActivityListaProductos;
import com.example.proyectofinal_android.Pojos.Linea_Pedido;
import com.example.proyectofinal_android.R;

import java.util.ArrayList;

public class AdapterCarrito extends ArrayAdapter<Linea_Pedido> {
    ArrayList<Linea_Pedido> lineas;
    Context context;
    TextView textview_precioSubtotal;

    public AdapterCarrito(ArrayList<Linea_Pedido> data, Context context, TextView textview_precioSubtotal) {
        super(context, R.layout.listview_carrito, data);
        this.lineas = data;
        this.context=context;
        this.textview_precioSubtotal = textview_precioSubtotal;
    }

    private static class ViewHolder {
        TextView textView_nombre;
        TextView textView_Descripcion;
        TextView textView_precio;
        TextView textView_totalProductos;
        Button botonMas;
        Button botonMenos;
        Button botonEliminar;
        ImageView imagenProducto;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Linea_Pedido linea = lineas.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final AdapterCarrito.ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;
        if (convertView == null) {

            viewHolder = new AdapterCarrito.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_carrito, parent, false);

            viewHolder.textView_nombre = convertView.findViewById(R.id.textView_productoNombre);
            viewHolder.textView_Descripcion =  convertView.findViewById(R.id.textView_productoDescripcion);
            viewHolder.textView_totalProductos = convertView.findViewById(R.id.carrito_totalProducto);
            viewHolder.imagenProducto =  convertView.findViewById(R.id.imageView_imagenProducto);
            viewHolder.botonMas =  convertView.findViewById(R.id.botonMas);
            viewHolder.textView_precio = convertView.findViewById(R.id.carrito_precioProducto);
            viewHolder.botonMenos =  convertView.findViewById(R.id.botonMenos);
            viewHolder.botonEliminar =  convertView.findViewById(R.id.carrito_botonEliminar);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AdapterCarrito.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.textView_nombre.setText(linea.getNombreProducto());
        viewHolder.textView_Descripcion.setText(linea.getLinea_pedido_desc());
        viewHolder.textView_precio.setText(linea.getPrecioTotal() + "");
        viewHolder.textView_totalProductos.setText(linea.getLinea_pedido_cantidad() + "");

        //actualizarSubtotal();
        if (linea.getImagen()== null){
            viewHolder.imagenProducto.setImageResource(R.drawable.logo);
        }else{
            viewHolder.imagenProducto.setImageBitmap(linea.getImagen());
        }


        viewHolder.botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineas.remove(position);
                notifyDataSetChanged();
                ActivityCarrito.actualizarSubtotal(ActivityCarrito.ELIMINAR);
            }
        });


        viewHolder.botonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int cantidad = Integer.parseInt(viewHolder.textView_totalProductos.getText().toString());
                cantidad = cantidad + 1;

                viewHolder.textView_totalProductos.setText(cantidad + "");

                lineas.get(position).setLinea_pedido_cantidad(cantidad);
                viewHolder.textView_precio.setText(linea.getPrecioTotal() + "");
                ActivityCarrito.actualizarSubtotal(ActivityCarrito.MAS);

            }
        });
        viewHolder.botonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cantidad = Integer.valueOf(viewHolder.textView_totalProductos.getText()+"");

                if(cantidad - 1 != 0){
                    cantidad = cantidad - 1 ;
                    viewHolder.textView_totalProductos.setText(cantidad + "");
                    lineas.get(position).setLinea_pedido_cantidad(cantidad);
                    viewHolder.textView_precio.setText(linea.getPrecioTotal() + "");
                    ActivityCarrito.actualizarSubtotal(ActivityCarrito.MENOS);
                }




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

        lineas.clear();
        if (charText.length() == 0) {
            lineas.addAll(ActivityListaProductos.lineas);
        } else {

            //Log.e("ProductosAux","Entra en 1");
            //Log.e("ProductosAux size:  ", ActivityListaProductos.productosAux.size() + "");

            for (int i = 0; i < ActivityListaProductos.productos.size() ; i ++) {

                //Log.e("ProductosAux nombre", ActivityListaProductos.productosAux.get(i).getNombre().toLowerCase());

                if (ActivityListaProductos.productos.get(i).getNombre().toLowerCase().contains(charText)) {
                    //Log.e("ProductosAux contiene  ", charText);
                    lineas.add(ActivityListaProductos.lineas.get(i));
                }

            }
        }
        notifyDataSetChanged();
    }
}
