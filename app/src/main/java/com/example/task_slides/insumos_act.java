package com.example.task_slides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Clases.AdminSQLiteOpenHelper;

public class insumos_act extends AppCompatActivity {

    private EditText edCodigo, edNombre, edStock, edPrecio;
    private AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Fichero", null, 1);
    private SQLiteDatabase db = admin.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos_act);

        edCodigo = (EditText)findViewById(R.id.edCodigo);
        edNombre = (EditText)findViewById(R.id.edNombre);
        edStock = (EditText)findViewById(R.id.edStock);
        edPrecio = (EditText)findViewById(R.id.edPrecio);
    }

    public void  guardarInsumo(View view)
    {
        if(!edCodigo.getText().toString().isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("codigo", Integer.parseInt(edCodigo.getText().toString()));
            registro.put("nombre", edNombre.getText().toString());
            registro.put("stock", Integer.parseInt(edStock.getText().toString()));
            registro.put("precio", Float.parseFloat(edPrecio.getText().toString()));

            db.insert("insumos", null, registro);
            db.close();

            Toast.makeText(this, "Se ha guardado el Insumo", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Debe Rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void motraraInsumos(View view)
    {
        String codigo = edCodigo.getText().toString();

        if(!codigo.isEmpty())
        {
            Cursor fila = db.rawQuery("SELECT nombre, stock, precio FROM insumos WHERE codigo=" + codigo, null);

            if (fila.moveToFirst())
            {
                edNombre.setText(fila.getString(0));
                edStock.setText(fila.getString(1));
                edPrecio.setText(fila.getString(2));
            }
            else
            {
                Toast.makeText(this, "No hay campos en insumos", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "Ingrese codigo", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarInsumo(View view)
    {
        String codigo = edCodigo.getText().toString();

        if (!codigo.isEmpty())
        {
            db.delete("insumos", "codigo=" + codigo, null);
            db.close();

            Toast.makeText(this, "Insumo Eliminado", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Ingrese codigo", Toast.LENGTH_LONG).show();
        }
    }

    public void actualizarInsumo(View view)
    {
        String codigo = edCodigo.getText().toString();

        ContentValues cont = new ContentValues();
        cont.put("codigo", Integer.parseInt(edCodigo.getText().toString()));
        cont.put("nombre", edNombre.getText().toString());
        cont.put("stock", Integer.parseInt(edStock.getText().toString()));
        cont.put("precio", Float.parseFloat(edPrecio.getText().toString()));

        if (!codigo.isEmpty())
        {
            db.update("insumos", cont, "codigo=" + codigo, null);
            db.close();

            Toast.makeText(this, "Has actualiza un Insumo", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Ingrese codigo", Toast.LENGTH_LONG).show();
        }
    }

    public void vaciarCampos(View view)
    {
        edCodigo.setText("");
        edNombre.setText("");
        edStock.setText("");
        edPrecio.setText("");
    }

}