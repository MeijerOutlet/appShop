package com.example.appshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appshop.db.DbUsuario;
import com.example.appshop.entidades.Usuario;
import com.example.appShop.R;

public class CrearCuenta extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,bCreateUser;
    EditText email, nombre, apellidos, userName, password,repeatPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        inicializaciones();
    }
    void inicializaciones(){
        //BOTONES
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        bCreateUser = findViewById(R.id.bCreateUser);
        //CAMPOS
        email = findViewById(R.id.emailText);
        nombre = findViewById(R.id.nameText);
        apellidos = findViewById(R.id.apellidosText);
        userName = findViewById(R.id.userNameText);
        password = findViewById(R.id.passwordTextCreateUser);
        repeatPassword = findViewById(R.id.repeatPassCreateUser);

        //-------------LISTENERS BOTONES RESET CAMPOS---------------------
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setText("");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apellidos.setText("");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName.setText("");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText("");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatPassword.setText("");
            }
        });
        bCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbUsuario dbusuario = new DbUsuario(CrearCuenta.this);

                String s_email = email.getText().toString();
                String s_nombre = nombre.getText().toString();
                String s_apellidos = apellidos.getText().toString();
                String s_userName = userName.getText().toString();
                String s_password = password.getText().toString();
                String s_repeatPassword = repeatPassword.getText().toString();

                // validating if the text fields are empty or not.
                if (s_email.isEmpty() || s_nombre.isEmpty() || s_apellidos.isEmpty() || s_userName.isEmpty() || s_password.isEmpty() || s_repeatPassword.isEmpty()) {
                    Toast.makeText(CrearCuenta.this, "Por favor, rellene todos los datos...", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Usuario usuario = new Usuario(s_userName, s_nombre, s_apellidos, s_email, s_password);
                    long id = dbusuario.insertarUsuario(usuario);
                    if(id > 0) {
                        Toast.makeText(CrearCuenta.this, "Usuario registrado correctamente.", Toast.LENGTH_SHORT).show();
                        limpiar();
                        Intent i = new Intent(CrearCuenta.this, LogIn.class);
                        startActivity(i);
                    }
                    else if(dbusuario.verUsuario(usuario)!=null) {
                            Toast.makeText(CrearCuenta.this, "El nick ya está en uso.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(CrearCuenta.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void limpiar(){
        email.setText("");
        nombre.setText("");
        apellidos.setText("");
        userName.setText("");
        password.setText("");
        repeatPassword.setText("");
    }

}