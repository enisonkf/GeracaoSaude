package com.example.kelvinferreira.geracaosaude;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import model.Usuario;

public class CadastroUsuarios extends AppCompatActivity {
    private String nome;
    private boolean sexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuarios);
        final EditText tf_nome = (EditText) findViewById(R.id.tf_ins_us_nome);
        ImageButton bt_Fem = (ImageButton) findViewById(R.id.ins_us_bt_fem);
        ImageButton bt_Masc = (ImageButton) findViewById(R.id.ins_us_bt_masc);
        Button bt_Save = (Button) findViewById(R.id.ins_us_bt_salvar);
        final Usuario usuario= new Usuario();
        bt_Fem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setSexo(false);
            }
        });
        bt_Masc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setSexo(true);
            }
        });
        bt_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setNome(tf_nome.getText().toString().trim().toUpperCase());
                if(usuario.getNome().isEmpty()){
                    Toast.makeText(CadastroUsuarios.this,"Insira todos os dados",Toast.LENGTH_LONG).show();
                }else{
                    Intent it = new Intent(CadastroUsuarios.this,CadastraIndices.class);
                    usuario.save();
                    it.putExtra("id",usuario.getId());
                    startActivity(it);
                }
            }
        });
    }

}
