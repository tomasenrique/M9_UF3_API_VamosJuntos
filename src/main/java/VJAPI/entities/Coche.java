package VJAPI.entities;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
public class Coche implements Serializable {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_coche;
    private String telf_propietario;
    private String tipo_vehiculo;
    private String modelo;
    private String anyo;
    private String punto_salida; // punto de salida (ciudad donde sale el coche)
    private int num_plazas_libres;
    private int num_plazas_ocupadas;
    private String info_complementaria_coche;



    private Evento id_evento;



    private Usuario id_usuario;


    // Builders
    public Coche() {
    }

    public Coche(String telf_propietario, String tipo_vehiculo, String modelo, String anyo, String punto_salida, int num_plazas_libres, int num_plazas_ocupadas, String info_complementaria_coche, Evento id_evento, Usuario id_usuario) {
        this.telf_propietario = telf_propietario;
        this.tipo_vehiculo = tipo_vehiculo;
        this.modelo = modelo;
        this.anyo = anyo;
        this.punto_salida = punto_salida;
        this.num_plazas_libres = num_plazas_libres;
        this.num_plazas_ocupadas = num_plazas_ocupadas;
        this.info_complementaria_coche = info_complementaria_coche;
        this.id_evento = id_evento;
        this.id_usuario = id_usuario;
    }

    // Setter and Getter
    public Long getId_coche() {
        return id_coche;
    }

    public void setId_coche(Long id_coche) {
        this.id_coche = id_coche;
    }

    public String getTelf_propietario() {
        return telf_propietario;
    }

    public void setTelf_propietario(String telf_propietario) {
        this.telf_propietario = telf_propietario;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getPunto_salida() {
        return punto_salida;
    }

    public void setPunto_salida(String punto_salida) {
        this.punto_salida = punto_salida;
    }

    public int getNum_plazas_libres() {
        return num_plazas_libres;
    }

    public void setNum_plazas_libres(int num_plazas_libres) {
        this.num_plazas_libres = num_plazas_libres;
    }

    public int getNum_plazas_ocupadas() {
        return num_plazas_ocupadas;
    }

    public void setNum_plazas_ocupadas(int num_plazas_ocupadas) {
        this.num_plazas_ocupadas = num_plazas_ocupadas;
    }

    public String getInfo_complementaria_coche() {
        return info_complementaria_coche;
    }

    public void setInfo_complementaria_coche(String info_complementaria_coche) {
        this.info_complementaria_coche = info_complementaria_coche;
    }

    public Evento getId_evento() {
        return id_evento;
    }

    public void setId_evento(Evento id_evento) {
        this.id_evento = id_evento;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }
}
