package model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Kelvin Ferreira on 12/06/2017.
 */
@Table(name = "usuarios")
public class Usuario extends Model {
    @Column(name = "nome",index = true)
    private String nome;
    @Column(name = "sexo", index = true)
    private boolean sexo;

    public Usuario(){
        super();
    }
    public Usuario(String nome, boolean sexo){
        super();
        this.sexo=sexo;
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
}
