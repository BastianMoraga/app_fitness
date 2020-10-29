package com.example.task_slides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class menu_act extends AppCompatActivity {

    private ViewFlipper vf;
    private int[] image = {R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_act);

        vf = (ViewFlipper)findViewById(R.id.slider);

        for(int i = 0; i < image.length ; i++)
        {
            flip_image(image[i]);
        }
    }

    public void flip_image(int i)
    {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);

        //Configuracion del viewflipper.

        vf.addView(view);               //El viewflipper tendra mi arreglo de imagenes.
        vf.setFlipInterval(2000);       //Desplazamiento del slider.
        vf.setAutoStart(true);          //El silider inicie de forma automatica.

        //Sentido del slider.

        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void info(View view)
    {
        Intent i = new Intent(this, info_act.class);
        startActivity(i);
    }

    public void mapa(View view)
    {
        Intent i = new Intent(this, Maps_act.class);
        startActivity(i);
    }

    public void clientes (View view)
    {
        ArrayList<String> clientes = new ArrayList<String>();
        ArrayList<String> planes = new ArrayList<String>();

        clientes.add("Roberto");
        clientes.add("Ivan");

        planes.add("xtreme");
        planes.add("mindfullness");


        Intent i = new Intent(this, clientes_act.class);

        i.putExtra("listaClientes", clientes);
        i.putExtra("listaPlanes", planes);

        startActivity(i);
    }
}