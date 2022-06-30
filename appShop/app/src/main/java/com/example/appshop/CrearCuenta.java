package com.example.appshop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearCuenta extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,bCreateUser;
    EditText email, nombre, apellidos, userName, password,repeatPassword;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

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

                if (!validateEmail(s_email)) {
                    Toast.makeText(CrearCuenta.this, "Por favor, rellene el Email correctamente...", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pswErr = validatePassword(s_password);
                if (!pswErr.isEmpty()) {
                    onCreateDialog(pswErr).show();
                    return;
                }

                if (!s_password.equals(s_repeatPassword)) {
                    Toast.makeText(CrearCuenta.this, "Las contraseñas no son las mismas...", Toast.LENGTH_SHORT).show();
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

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private static String validatePassword(String pswStr) {
        String[] regexArray = new String[]{ "^(?=.*[0-9])", "(?=.*[a-z])", "(?=.*[A-Z])", "(?=\\S+$).{8,}$"};
        List<String> errorsArray = new ArrayList<String>();
        String error = "";
        for(int i = 0; i <regexArray.length; i++){
            Pattern VALID_PSW_REGEX =
                    Pattern.compile(regexArray[i], Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_PSW_REGEX.matcher(pswStr);

            if(!matcher.find()){
                errorsArray.add(regexArray[i]);
            }
        }
        if(!errorsArray.isEmpty()){
            for(int i = 0; i < errorsArray.size(); i++){
                switch (errorsArray.get(i)){
                    case "^(?=.*[0-9])":
                        error+="\n - Un dígito debe aparecer al menos una vez.\n";
                        break;
                    case "(?=.*[a-z])":
                        error+="\n - Una letra minúscula debe aparecer al menos una vez.\n";
                        break;
                    case "(?=.*[A-Z])":
                        error+="\n - Una letra mayúscula debe aparecer al menos una vez.\n";
                        break;
                    case "(?=\\S+$).{8,}$":
                        error+="\n - No se permiten espacios en blanco.\n\n - Tiene que ser mayor de 8 carácteres.\n";
                        break;
                }
            }

        }else{
            error = "";
        }
        return error;
    }

    private Dialog onCreateDialog(String errors) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Por favor, rellene la contraseña correctamente:\n"+errors)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
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