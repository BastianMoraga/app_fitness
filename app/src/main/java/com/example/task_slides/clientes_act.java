package com.example.task_slides;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import Clases.Planes;

public class clientes_act extends AppCompatActivity {

    private Spinner spin, spin2;
    private EditText edit;
    private TextView text, calculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_act);

        spin = (Spinner)findViewById(R.id.spinCliente);
        spin2 = (Spinner)findViewById(R.id.spinPlanes);
        edit = (EditText)findViewById(R.id.edMonto);
        text = (TextView)findViewById(R.id.txtTotal);
        calculo = (TextView)findViewById(R.id.txtCalculo);

        ArrayList<String> listaClientes = (ArrayList<String>) getIntent().getSerializableExtra("listaClientes");
        ArrayList<String> listaPlanes = (ArrayList<String>) getIntent().getSerializableExtra("listaPlanes");

        ArrayAdapter<String> adaptClientes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaClientes);
        ArrayAdapter<String> adaptPlanes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPlanes);

        spin.setAdapter(adaptClientes);
        spin2.setAdapter(adaptPlanes);
    }

    public void calcular(View view)
    {
        String cliente = spin.getSelectedItem().toString();
        String plan = spin2.getSelectedItem().toString();

        Planes planes = new Planes();

        int monto, valor, resultado;


        if(plan.equals("xtreme"))
        {
            monto = Integer.parseInt(edit.getText().toString());
            valor = Integer.parseInt(planes.getPlanXtreme());
            resultado = valor - monto;

            text.setText(cliente + " el plan " + plan + " tiene un valor de: " + planes.getPlanXtreme());

            if(resultado < valor)
            {
                calculo.setText("Error Faltan: $" + resultado);
            }
            if(monto >= valor)
            {
                resultado = monto - valor;
                calculo.setText("Compra Exitosa" + "\n" + "Su vuelto es de: $" + resultado);
            }
        }
        if(plan.equals("mindfullness"))
        {
            monto = Integer.parseInt(edit.getText().toString());
            valor = Integer.parseInt(planes.getPlanMindfullness());
            resultado = valor - monto;

            text.setText(cliente + " el plan " + plan + " tiene un valor de: " + planes.getPlanMindfullness());

            if(resultado < valor)
            {
                calculo.setText("Error Faltan: $" + resultado);
            }
            if(monto >= valor)
            {
                resultado = monto - valor;
                calculo.setText("Compra Exitosa" + "\n" + "Su vuelto es de: $" + resultado);
            }
        }
    }
}