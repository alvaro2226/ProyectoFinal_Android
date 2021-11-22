package com.example.proyectofinal_android.Pojos;

import org.apache.commons.net.ntp.TimeStamp;

import java.util.Date;

public class Pedido {

    private TimeStamp fechaCreacion;
    private int usuario_id;

    public Pedido(TimeStamp fechaCreacion, int usuario_id, float costesEnvio, Date fechaEnvioEstimada, int estadoPedido, int metodoPago, boolean pagado, int id_usuarioAsignado) {
        this.fechaCreacion = fechaCreacion;
        this.usuario_id = usuario_id;
        this.costesEnvio = costesEnvio;
        this.fechaEnvioEstimada = fechaEnvioEstimada;
        this.estadoPedido = estadoPedido;
        this.metodoPago = metodoPago;
        this.pagado = pagado;
        this.id_usuarioAsignado = id_usuarioAsignado;
    }

    public void setCostesEnvio(float costesEnvio) {
        this.costesEnvio = costesEnvio;
    }

    public void setFechaEnvioRealizado(Date fechaEnvioRealizado) {
        this.fechaEnvioRealizado = fechaEnvioRealizado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public Pedido() {

    }

    public TimeStamp getFechaCreacion() {
        return fechaCreacion;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public float getCostesEnvio() {
        return costesEnvio;
    }

    public Date getFechaEnvioEstimada() {
        return fechaEnvioEstimada;
    }

    public Date getFechaEnvioRealizado() {
        return fechaEnvioRealizado;
    }

    public int getEstadoPedido() {
        return estadoPedido;
    }

    public int getMetodoPago() {
        return metodoPago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public int getId_usuarioAsignado() {
        return id_usuarioAsignado;
    }

    private float costesEnvio = 1.99f;
    private Date fechaEnvioEstimada;
    private Date fechaEnvioRealizado;

    public void setFechaCreacion(TimeStamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public void setFechaEnvioEstimada(Date fechaEnvioEstimada) {
        this.fechaEnvioEstimada = fechaEnvioEstimada;
    }

    public void setEstadoPedido(int estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public void setMetodoPago(int metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setId_usuarioAsignado(int id_usuarioAsignado) {
        this.id_usuarioAsignado = id_usuarioAsignado;
    }

    private int estadoPedido;
    private int metodoPago;
    private boolean pagado = false;
    private int id_usuarioAsignado;

}
