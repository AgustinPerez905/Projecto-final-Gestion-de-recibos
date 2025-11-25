package modelo;

import java.time.LocalDate;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class Recibo {
    private String id;

    private double monto;

    private String descripcion;

    private String idUsuario;

    private String tipo;

    private LocalDate fecha;

    private LocalDate fechaVencimiento;

    private String numRUT;

    private String moneda;

    private double iVA;

    private double subtotal;
    private String debitoTarjeta;

    private static String getAlphaNumericString() {
        int numero = ThreadLocalRandom.current().nextInt(10000, 100000);
        return String.valueOf(numero);
    }

    public Recibo(double monto, String descripcion,
                  String idUsuario, String tipo,
                  LocalDate fecha, LocalDate fechaVencimiento,
                  String numRUT, String moneda, double iVA, double subtotal,
                  String debitoTarjeta) {
        this.id = getAlphaNumericString();
        this.monto = monto;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.fecha = fecha;
        this.fechaVencimiento = fechaVencimiento;
        this.numRUT = numRUT;
        this.moneda = moneda;
        this.iVA = iVA;
        this.subtotal = subtotal;
        this.debitoTarjeta = debitoTarjeta;
    }

    public Recibo(String id,
                  double monto, String descripcion, String idUsuario, String tipo,
                  LocalDate fecha, LocalDate fechaVencimiento, String numRUT, String moneda,
                  double iVA, double subtotal, String debitoTarjeta) {
        this.id = id;
        this.monto = monto;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.fecha = fecha;
        this.fechaVencimiento = fechaVencimiento;
        this.numRUT = numRUT;
        this.moneda = moneda;
        this.iVA = iVA;
        this.subtotal = subtotal;
        this.debitoTarjeta = debitoTarjeta;
    }

    public String getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getNumRUT() {
        return numRUT;
    }

    public String getMoneda() {
        return moneda;
    }

    public double getiVA() {
        return iVA;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getDebitoTarjeta() {
        return debitoTarjeta;
    }
}
