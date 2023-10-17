package dominio;

import java.sql.Date;

public class Tarea {

    private int idTarea;
    private static int contadorTareas;
    
    private String titulo;
    private String descripcion;
    private int estado;
    private Date fechaInicio;
    private Date fechaFin;
    
    public Tarea(){
        this.idTarea = ++contadorTareas;
    }
    public Tarea(int idTarea){
        this.idTarea = idTarea;
    }
    public Tarea(String titulo, String descripcion, int estado, Date fechaInicio, Date fechaFin){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    public Tarea(int idTarea, String titulo, String descripcion, int estado, Date fechaInicio, Date fechaFin){
        this.idTarea = idTarea;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public static int getContadorTareas() {
        return contadorTareas;
    }

    public static void setContadorTareas(int contadorTareas) {
        Tarea.contadorTareas = contadorTareas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    // Devuelve el estado en una cadena en lugar de un int
    public String estadoTexto() {
        String texto ="";
        switch(this.estado) {
            case 0:
                texto = "PENDIENTE";
                break;
            case 1:
                texto = "COMPLETA";
                break;
            case 2:
                texto = "CANCELADA";
                break;
            default:
                this.estado = 0;
                texto = "PENDIENTE";
                break;
        }
        return texto;
    }
    
    @Override
    public String toString() {
        return "Tarea{" + "idTarea=" + idTarea + ", titulo=" + titulo + ", descripcion=" + descripcion + ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
    
    
}
