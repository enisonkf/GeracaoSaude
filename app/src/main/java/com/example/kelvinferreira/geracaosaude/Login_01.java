package com.example.kelvinferreira.geracaosaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class Login_01 extends AppCompatActivity implements View.OnClickListener {
    private List<Usuario> usuariosList;
    private UsuarioAdapter usuarioAdapter;
    private Usuario usuario;
    private ArrayList<Usuario> usuarios;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_01);
        Button bt_inserir = (Button) findViewById(R.id.bt_add_usuario);
        bt_inserir.setOnClickListener(this);
        updateTable();
        if(retornaUsuarios().isEmpty()){
            abreCadastro();
        }else {}
    }

    public void updateTable(){
        listView = (ListView) findViewById(R.id.list_usuarios);
        usuarios = new ArrayList<>();
        usuario = new Usuario();
        usuariosList = retornaUsuarios();
        for (int i = 0; i < usuariosList.size(); i++) {
            usuario = usuariosList.get(i);
            usuarios.add(usuario);
        }
        usuarioAdapter = new UsuarioAdapter(Login_01.this, usuarios);
        listView.setAdapter(usuarioAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Login_01.this,CadastraIndices.class);
                it.putExtra("id",usuarios.get(position).getId());
                startActivity(it);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                usuario=usuariosList.get(position);
                usuario.delete();
                updateTable();
                return false;
            }
        });

    }

    private List<Usuario> retornaUsuarios() {
        return new Select().from(Usuario.class).execute();
    }

    public void abreCadastro(){
        Intent it = new Intent(this, CadastroUsuarios.class);
        startActivity(it);
    }

    @Override
    public void onClick(View v) {
            abreCadastro();
    }
}
