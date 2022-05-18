package com.example.appshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appshop.db.DbUsuario;
import com.example.appshop.entidades.Usuario;
import com.example.appShop.R;

public class LogIn extends AppCompatActivity {
    ImageView logo;
    EditText user, password;
    Button submitButton, bBorrarTextUser, bBorrarTextPassword;
    TextView crearCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        inicializaciones();
    }
    void inicializaciones(){
        logo = findViewById(R.id.logoViewForm);
        user = findViewById(R.id.userText);;
        password = findViewById(R.id.passwordText);
        submitButton = findViewById(R.id.submitButtonForm);
        crearCuenta = findViewById(R.id.noTengoCuenta);
        bBorrarTextUser = findViewById(R.id.bBorrarUserText);
        bBorrarTextPassword = findViewById(R.id.bBorrarPasswordText);

        //----------------------------------------LISTENERS------------------------------------------------------------------
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_userName = user.getText().toString();
                String s_password = password.getText().toString();

                // validating if the text fields are empty or not.
                if (s_userName.isEmpty() && s_password.isEmpty()) {
                    Toast.makeText(LogIn.this, "Por favor, rellene todos los datos...", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    DbUsuario dbusuario = new DbUsuario(LogIn.this);
                    Usuario usuario_validar = new Usuario();
                    Usuario usuario_recibido = null;
                    usuario_validar.setNick(s_userName);
                    usuario_validar.setPassword(s_password);
                    usuario_recibido = dbusuario.verUsuario(usuario_validar);

                    if(usuario_recibido==null || usuario_recibido.getNick().equals("")){
                        Toast.makeText(LogIn.this, "Usuario no encontrado.", Toast.LENGTH_SHORT).show();
                    }
                    else if(usuario_recibido.getNick().equals(usuario_validar.getNick()) && usuario_recibido.getPassword().equals(usuario_validar.getPassword())) {
                        Toast.makeText(LogIn.this, "Iniciando sesion.", Toast.LENGTH_SHORT).show();
                        limpiar();
                        startActivity(new Intent(LogIn.this, AppChollosMain.class));
                    }
                    else if(usuario_recibido.getNick().equals(usuario_validar.getNick()) && !usuario_recibido.getPassword().equals(usuario_validar.getPassword())){
                        Toast.makeText(LogIn.this, "La contraseña es incorrecta.", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    Toast.makeText(LogIn.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this, CrearCuenta.class);
                startActivity(i);
            }
        });
    }

    private void limpiar(){
        user.setText("");
        password.setText("");
    }

    public void onClick(View v){
        if(v.getId()==bBorrarTextUser.getId()){
            user.setText("");
        }else if(v.getId() == bBorrarTextPassword.getId()){
            password.setText("");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //System.exit(0);
    }
}