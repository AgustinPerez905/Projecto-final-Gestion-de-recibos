package modelo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Usuario {
    private String id;

    private String nombreComp;

    private String nombreUser;

    private String contrasenia;

    private ArrayList<Recibo> listaDeRecibos;

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    private static String getAlphaNumericString() {
        int numero = ThreadLocalRandom.current().nextInt(10000, 100000);
        return String.valueOf(numero);
    }

    public Usuario(String nombreComp, String nombreUser, String contrasenia) {
        this.id = getAlphaNumericString();
        this.nombreComp = nombreComp;
        this.nombreUser = nombreUser;
        this.contrasenia = contrasenia;
    }


    public Usuario(String id, String nombreComp, String nombreUser, String contrasenia) {
        this.id = id;
        this.nombreComp = nombreComp;
        this.nombreUser = nombreUser;
        this.contrasenia = contrasenia;

    }


    public ArrayList<Recibo> getListaDeRecibos() {
        return listaDeRecibos;
    }

    public void setListaDeRecibos(ArrayList<Recibo> listaDeRecibos) {
        this.listaDeRecibos = listaDeRecibos;
    }

    public String getId() {
        return id;
    }

    public String getNombreComp() {
        return nombreComp;
    }


    public String getNombreUser() {
        return nombreUser;
    }


    public String getContrasenia() {
        return contrasenia;
    }

}
