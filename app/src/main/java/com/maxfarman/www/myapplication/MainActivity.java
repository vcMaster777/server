package com.maxfarman.www.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
EditText ingreso;
Button enviar;
TextView respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingreso=findViewById(R.id.editText);
        enviar=findViewById(R.id.button);
        respuesta=findViewById(R.id.textView);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient cliente = new AsyncHttpClient();
                RequestParams parametros = new RequestParams();
                parametros.put("texto",ingreso.getText().toString());
                Context c= getApplicationContext();
                String u ="http://programadoresperuanos.com/test_app/archivo.php";
                cliente.post(c, u, parametros, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                String res= new String(responseBody);
                                respuesta.setText(res);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                respuesta.setText("Error con el servidor");
                    }
                });
            }
        });
    }
}
