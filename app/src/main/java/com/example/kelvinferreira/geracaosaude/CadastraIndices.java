package com.example.kelvinferreira.geracaosaude;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.text.DecimalFormat;

import model.Indices;
import model.Usuario;

public class CadastraIndices extends AppCompatActivity {
    private Usuario usuario;
    private Indices indice;
    private TextView IMC;
    private TextView notificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_indices);
        Intent it = getIntent();


        indice = new Indices();
        long idPassado= it.getLongExtra("id",0);
        usuario = Usuario.load(Usuario.class,idPassado);


        TextView textoWelcome = (TextView) findViewById(R.id.tv_ins_ind_welcome);
        textoWelcome.setText(geraMensagemBemVindo(it));
        final Button bt_gravar= (Button) findViewById(R.id.bt_gravar_imc);
        final Button bt_exibir= (Button) findViewById(R.id.bt_exibir_dados);
        IMC =(TextView) findViewById(R.id.tv_ins_ind_imc);
        notificacao=(TextView) findViewById(R.id.tv_ins_ind_notifica);
        indice.setData();
        final EditText editPeso = (EditText) findViewById(R.id.tf_ins_ind_peso);
        final EditText editAltura = (EditText) findViewById(R.id.tf_ins_ind_altura);
        editPeso.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && editPeso.getText().toString().trim().isEmpty()) {

                }else{}
                if(hasFocus==false) {
                    indice.setPeso(Double.parseDouble(editPeso.getText().toString().trim()));
                    if(!editAltura.getText().toString().trim().isEmpty()){
                        indice.setAltura(Double.parseDouble(editAltura.getText().toString().trim()));
                        indice.calculaIMC();
                        imprimeIMC();
                    }
                }
            }
        });

        editAltura.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false && editAltura.getText().toString().trim().isEmpty()) {

                }else{}
                if(hasFocus==false) {
                    indice.setAltura(Double.parseDouble(editAltura.getText().toString().trim()));
                    indice.calculaIMC();
                    imprimeIMC();
                }
                if(!editAltura.getText().toString().trim().isEmpty()){
                    indice.setAltura(Double.parseDouble(editAltura.getText().toString().trim()));
                    indice.calculaIMC();
                    imprimeIMC();
                }
            }
        });
        bt_gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indice.setUsuario(usuario);
                indice.setPeso(Double.parseDouble(editPeso.getText().toString().trim()));
                indice.setAltura(Double.parseDouble(editAltura.getText().toString().trim()));
                indice.calculaIMC();
                indice.save();
                imprimeIMC();
                Intent it = new Intent(CadastraIndices.this,ExibirIndices.class);
                it.putExtra("id",usuario.getId());
                startActivity(it);
            }
        });
        bt_exibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CadastraIndices.this,ExibirIndices.class);
                it.putExtra("id",usuario.getId());
                startActivity(it);
            }
        });

    }

    public String transformaValor(double valor){
        return new DecimalFormat("#0.00").format(valor);
    }

    public void imprimeIMC(){
        double imc =indice.getImc();
        String texto = transformaValor(imc);
        if(imc<=18.5){
            IMC.setText(texto);
            IMC.setTextColor(getResources().getColor(R.color.amarelo));
            notificacao.setText("Abaixo do peso");
            notificacao.setTextColor(getResources().getColor(R.color.amarelo));
        }else{}
        if(imc>=18.51 && imc<25){
            IMC.setText(texto);
            IMC.setTextColor(getResources().getColor(R.color.azul_claro));
            notificacao.setText("Peso ideal");
            notificacao.setTextColor(getResources().getColor(R.color.azul_claro));
        }else{}
        if(imc>=25 && imc<30){
            IMC.setText(texto);
            IMC.setTextColor(getResources().getColor(R.color.laranja));
            notificacao.setText("Acima do peso");
            notificacao.setTextColor(getResources().getColor(R.color.laranja));
        }else{}
        if(imc>=30){
            IMC.setText(texto);
            IMC.setTextColor(getResources().getColor(R.color.vermelho));
            notificacao.setText("Obeso");
            notificacao.setTextColor(getResources().getColor(R.color.vermelho));
        }else{}

    }

    public String geraMensagemBemVindo(Intent it){
        String welcome = "Bem vindo ";
        if(usuario.getSexo())
            welcome+="Sr.: ";
        else
            welcome+="Sra.: ";
        return welcome+=usuario.getNome()+"!";
    }


}
