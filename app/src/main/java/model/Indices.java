package model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kelvin Ferreira on 12/06/2017.
 */

@Table(name="indices")
public class Indices extends Model {
    @Column(name = "peso", index = true)
    private double peso;
    @Column(name = "altura")
    private double altura;
    @Column(name = "imc")
    private double imc;
    @Column(name = "data")
    private String data;
    @Column(name = "fk_usuario", onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.SET_DEFAULT)
    private Usuario usuario;

    public Indices(double imc,Usuario usuario, double peso, double altura, String data) {
        super();
        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
        if(data!=null) {
            this.data = data;
        }else{
            this.data=new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date());
        }
        this.usuario = usuario;
    }


    public Indices() {
        super();
    }

    public String getData() {
        return data;
    }

    public void setData() {
        this.data = new SimpleDateFormat("dd/MM/yy").format(new Date());
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso =peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getImc() {
        return imc;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void calculaIMC(){
        this.imc=+this.getPeso()/this.getAltura()/this.getAltura();
    }
}
