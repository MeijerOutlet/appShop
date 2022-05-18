package com.example.appshop.entidades;

public class Usuario {

    private String nick;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;

    public Usuario(String nick, String nombre, String apellidos, String email, String password) {
        setNick(nick);
        setNombre(nombre);
        setApellidos(apellidos);
        setEmail(email);
        setPassword(password);
    }

    public Usuario() {
    }

    public Usuario(String nick) {
        setNick(nick);
    }

    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNick(String nick) {
        if(nick==null){
            throw new RuntimeException("El campo nick no puede estar vacio.");
        }else if(!(nick.trim().length() >0) || !(nick.trim().length()<50)){
            throw new RuntimeException("La longitud del nick no es válida. (1-49)");
        } else {
            this.nick = nick.trim();
        }
    }

    public void setNombre(String nombre) {
        if(nombre==null){
            throw new RuntimeException("El campo nombre no puede estar vacio.");
        }else if(!(nombre.trim().length() >0) || !(nombre.trim().length()<50)){
            throw new RuntimeException("La longitud del nombre no es válida. (1-49)");
        } else {
            this.nombre = nombre.trim();
        }
    }

    public void setApellidos(String apellidos) {
        if(apellidos==null){
            throw new RuntimeException("El campo apellidos no puede estar vacio.");
        }else if(!(apellidos.trim().length() >0) || !(apellidos.trim().length()<50)){
            throw new RuntimeException("La longitud de los apellidos no es válida. (1-49)");
        } else {
            this.apellidos = apellidos.trim();
        }
    }

    public void setEmail(String email) {
        if(email==null){
            throw new RuntimeException("El campo email no puede estar vacio.");
        }else if(!(email.trim().length() >0) || !(email.trim().length()<100)){
            throw new RuntimeException("La longitud del email no es válida. (1-99)");
        } else {
            this.email = email.trim();
        }
    }

    public void setPassword(String password) {
        if(password==null){
            throw new RuntimeException("El campo password no puede estar vacio.");
        }else if(!(password.trim().length() >0) || !(password.trim().length()<50)){
            throw new RuntimeException("La longitud de la password no es válida. (1-49)");
        } else {
            this.password = password.trim();
        }
    }
}

