package com.example.kelvinferreira.geracaosaude;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Indices;
import model.Usuario;

/**
 * Created by Kelvin Ferreira on 19/06/2017.
 */

public class UsuarioAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<Usuario> itemsArrayList;

    public UsuarioAdapter(Context context, ArrayList<Usuario> itemsArrayList) {
        this.context=context;
        this.itemsArrayList=itemsArrayList;
    }

    @Override
    public int getCount() {
        return itemsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.show_users,null);
        }
        TextView usuarioNome = (TextView) convertView.findViewById(R.id.tv_nome_list);
        ImageView usuarioSexo = (ImageView) convertView.findViewById(R.id.bt_show_sexo);

        Usuario usuario =itemsArrayList.get(position);

        usuarioNome.setText(usuario.getNome());
        if(usuario.getSexo()){
            usuarioSexo.setImageResource(R.drawable.icon_masc);
        }else{
            usuarioSexo.setImageResource(R.drawable.icon_fem);
        }

        return convertView;
    }
}
