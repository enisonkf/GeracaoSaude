package com.example.kelvinferreira.geracaosaude;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Indices;

import static android.R.attr.x;

public class ExibirIndices extends AppCompatActivity {
    private GraphView grafico;
    private List<Indices> indicesList;
    private IndicesAdapter indicesAdapter;
    private Indices indice;
    private ArrayList<Indices> indices;
    private ListView listView;
    private Intent it;
    private long userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        it = getIntent();
        userKey = it.getLongExtra("id",0);
        setContentView(R.layout.activity_exibir_indices);
        updateTable();
    }


    public void geraGrafico(){
        DataPoint dataPoint[] = new DataPoint[indices.size()];
        double inicio =-1, fim, x,y;
        fim = indices.size()+2;
        for(int i=0;i<indices.size();i++){
            x=i+1;
            String imc=new DecimalFormat("#,##").format(indices.get(i).getImc());
            y=Double.parseDouble(imc);
            dataPoint[i] = new DataPoint(x,y);
        }
        grafico = (GraphView) findViewById(R.id.grafico);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoint);
        series.setTitle("IMC");
        series.setColor(getResources().getColor(R.color.azul_claro));
        grafico.setTitleColor(getResources().getColor(R.color.vermelho));
        grafico.getViewport().setScrollable(true);
        grafico.setScaleX((float) 0.90);
        grafico.setScaleY((float) 0.90);
        grafico.getGridLabelRenderer().setNumHorizontalLabels(5);
        grafico.getGridLabelRenderer().setNumVerticalLabels(3);
        grafico.getGridLabelRenderer().setLabelsSpace(2);
        grafico.getViewport().setMinX(inicio);
        grafico.getViewport().setMaxX(fim);
        grafico.getViewport().setMinY(series.getLowestValueY()-2);
        grafico.getViewport().setMaxY(series.getHighestValueY()+2);
        grafico.getViewport().setYAxisBoundsManual(true);
        grafico.getViewport().setXAxisBoundsManual(true);
        grafico.addSeries(series);
    }

    public void updateTable(){
        listView = (ListView) findViewById(R.id.lista_Indices);
        indices = new ArrayList<>();
        indice = new Indices();
        indicesList = retornaIndices();
        for (int i = 0; i < indicesList.size(); i++) {
            if(indicesList.get(i).getUsuario().getId()==userKey) {
                indice = indicesList.get(i);
                indices.add(indice);
            }else{}
        }
        indicesAdapter = new IndicesAdapter(ExibirIndices.this,indices);
        listView.setAdapter(indicesAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indice=indicesList.get(position);
                indice.delete();
                updateTable();
                grafico.destroyDrawingCache();
                return false;
            }
        });
        geraGrafico();

    }

    private List<Indices> retornaIndices() {
        return new Select().from(Indices.class).execute();
    }
}
