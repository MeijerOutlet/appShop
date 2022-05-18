package com.example.appshop.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "chollobikes.sqlite";
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String TABLA_COMERCIOS = "comercios";
    public static final String TABLA_CATEGORIAS = "categorias";
    public static final String TABLA_PRODUCTOS = "productos";
    public static final String TABLA_PROMOCIONES = "promociones";
    public static final String TABLA_VALORACIONES = "valoraciones";
    public static final String TABLA_PRODUCTOS_COMERCIOS = "productos_comercios";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_USUARIOS + "(" +
                "nick varchar(50)," +
                "nombre varchar(50) NOT NULL," +
                "apellidos varchar(50) NOT NULL," +
                "email varchar(100) NOT NULL," +
                "password varchar(50) NOT NULL," +
                "PRIMARY KEY (nick))");

        db.execSQL("CREATE TABLE " + TABLA_COMERCIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre varchar(100) NOT NULL," +
                "cif varchar(9) NOT NULL," +
                "direccion varchar(100) NOT NULL," +
                "region varchar(100))");

        db.execSQL("CREATE TABLE " + TABLA_CATEGORIAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descripcion varchar(100) NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLA_PRODUCTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre varchar(50) NOT NULL," +
                "descripcion varchar(100)," +
                "talla varchar(20)," +
                "categoria INTEGER," +
                "FOREIGN KEY (categoria) REFERENCES categorias (id))");

        db.execSQL("CREATE TABLE " + TABLA_PROMOCIONES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre varchar(100) NOT NULL," +
                "tipo_descuento decimal(3,2) NOT NULL," +
                "fecha_desde_valida date NOT NULL," +
                "fecha_hasta_valida date NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLA_VALORACIONES + "(" +
                "nick varchar(50)," +
                "id_com int," +
                "id_prod int," +
                "valoracion int," +
                "comentario varchar(100)," +
                "fecha date NOT NULL," +
                "PRIMARY KEY (nick,id_com,id_prod)," +
                "FOREIGN KEY (nick) REFERENCES usuarios (nick)," +
                "FOREIGN KEY (id_com) REFERENCES comercios (id)," +
                "FOREIGN KEY (id_prod) REFERENCES productos (id))");

        db.execSQL("CREATE TABLE " + TABLA_PRODUCTOS_COMERCIOS + "(" +
                "id_prod int," +
                "id_com int," +
                "id_prom int," +
                "precio_total decimal(6, 2)," +
                "descuento decimal(6, 2)," +
                "importe_total_descontado decimal(6, 2)," +
                "cantidad int," +
                "PRIMARY KEY(id_prod, id_com)," +
                "FOREIGN KEY(id_prod) REFERENCES productos(id)," +
                "FOREIGN KEY (id_com) REFERENCES comercios (id)," +
                "FOREIGN KEY (id_prom) REFERENCES promociones (id))");



        // INSERT TABLA COMERCIOS
        db.execSQL("insert into comercios(nombre, cif, direccion, region) values('Decimas', '12345678P', 'C/ Prueba', 'Madrid');");
        db.execSQL("insert into comercios(nombre, cif, direccion, region) values('Springfield', '12345435P', 'C/ Prueba2', 'Barcelona');");
        db.execSQL("insert into comercios(nombre, cif, direccion, region) values('JD', '12345135P', 'C/ Sur', 'Sevilla');");
        db.execSQL("insert into comercios(nombre, cif, direccion, region) values('H&M', '42345435P', 'C/ Laguna', 'Zaragoza');");
        db.execSQL("insert into comercios(nombre, cif, direccion, region) values('Berhska', '84345435P', 'C/ Puebla', 'Segovia');");

        //INSERT TABLA CATEGORIAS
        db.execSQL("insert into categorias(descripcion) values('Camiseta');");
        db.execSQL("insert into categorias(descripcion) values('Pantalon');");
        db.execSQL("insert into categorias(descripcion) values('Chaqueta');");
        db.execSQL("insert into categorias(descripcion) values('Sudadera');");

        // INSERT TABLA PRODUCTOS
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Camiseta Nike', 'La camiseta Nike Sportswear Logo manga corta ha sido fabricada con un tejido suave de algodón que proporciona un tacto ligero y suave a la piel. El logo de la marca Nike va bordado en la parte central de la camiseta.', 'S,M,L,', 1);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Camiseta Adidas', 'Camiseta para mujer adidas 3 bandas con corte clásico que se ajusta al cuerpo cómodamente, con cuello de canalé y confeccionada en punto 100% algodón.', 'S,M,L,', 1);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Camiseta Basica', 'Camiseta para mujer Keya WCS150. En material 100% algodón de 150g/m2.', 'S,M,L,', 1);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Pantalon Vaquero', 'Jeans Clásico hombre en tejido NO elástico de color azul. Línea Regular Fit.', '40,42,43,46,', 2);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Pantalon Vaquero Gris', 'Pantalones vaqueros acampanados de tejido elástico, en color gris oscuro. Cierre mediante cremallera y botón con cinco bolsillos.', '26,27,28,30,31,', 2);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Pantalon Vaquero Straigth Figth', 'Pantalón vaquero para hombre, con diseño de cinco bolsillo y estilo Straight, cierre mediante botón y cremallera. Algodón:98% Elastano:2%', '45,46,', 2);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Chaqueta de Cuero', 'Chaqueta de cuero negro genuino para hombre, piel de cordero marrón, chaqueta de cuero para motocicleta', 'XS,L,', 3);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Chaqueta de Cuero Negro', 'Esta chaqueta de cuero para mujer está perfectamente elaborada por profesionales para darte una experiencia de estilo duradera; por lo que es un regalo perfecto de aniversario, cumpleaños para tu ser querido una vez e ideal para fiestas y lugares de reunión.', 'L,XL,', 3);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Chaqueta Biker Efecto Piel', 'Cazadora efecto piel de largo medio con cuello con solapa y manga larga. Bolsillos delanteros con cremallera metálica. Cinturón en bajo con hebilla. Cierre frontal cruzado con cremallera metálica.', 'M,', 3);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Sudadera Basica', 'Sudadera con capucha para hombre, suave y cómoda', 'L,', 4);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Sudadera Piolin', 'Sudadera de corte holgado de manga larga y cuello redondo con acabados de cuello, mangas y bajo de punto canalé. El tejido tiene un suave cepillado en el interior que le aporta confort y calidez a la prenda. Tiene un estampado de Piolín © &™ WARNER BROS.', 'S,M,', 4);");
        db.execSQL("insert into productos(nombre, descripcion, talla, categoria) values('Sudadera Universidad', 'Sudadera con estampado de la universidad y con capucha para hombre, suave y cómoda', 'XS,', 4);");

        // INSERT TABLA USUARIOS
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('luis', 'luis', 'luis', 'luis@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('manuel', 'manuel', 'manuel', 'manuel@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('daniel', 'daniel', 'daniel', 'daniel@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('nerea', 'nerea', 'nerea', 'nerea@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('noelia', 'noelia', 'noelia', 'noelia@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('mario', 'mario', 'mario', 'mario@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('irene', 'irene', 'irene', 'irene@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('maria', 'maria', 'maria', 'maria@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('marta', 'marta', 'marta', 'marta@gmail.com', '1234');");
        db.execSQL("insert into usuarios(nick, nombre, apellidos, email, password) values('miriam', 'miriam', 'miriam', 'miriam@gmail.com', '1234');");

        // INSERT TABLA PROMOCIONES
        db.execSQL("insert into promociones(nombre, tipo_descuento, fecha_desde_valida, fecha_hasta_valida) values('20% de descuento', 0.2, '2022-04-01', '2022-04-30');"); // ID 1
        db.execSQL("insert into promociones(nombre, tipo_descuento, fecha_desde_valida, fecha_hasta_valida) values('30% de descuento', 0.3, '2022-04-01', '2022-04-30');"); // ID 2
        db.execSQL("insert into promociones(nombre, tipo_descuento, fecha_desde_valida, fecha_hasta_valida) values('50% de descuento', 0.5, '2022-04-01', '2022-04-30');"); // ID 3

        // INSERT TABLA VALORACIONES
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('luis', 1, 1, 3, 'Gran camiseta de nike con buena tela, pero el logo no es lo mejor.', '2022-04-16');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('nerea', 2, 2, 4, 'Gran camiseta, muy contenta con la compra.', '2022-04-13');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('noelia', 3, 3, 5, 'La textura super suave y el color me encanta! Sin duda la volveria a comprar', '2022-04-12');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('mario', 4, 4, 2, 'Me quedaba pequeño y me costo mucho que me lo cambiaran.', '2022-04-13');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('irene', 5, 5, 1, 'No sirve, no cumple con las especificaciones.', '2022-04-16');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('manuel', 1, 6, 3, 'Por ahora parece que aguanta, pero no transmite confianza.', '2022-04-13');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('daniel', 2, 7, 4, 'Muy contenta, el color no es del todo el esperado pero me gusta.', '2022-04-15');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('daniel', 3, 1, 1, 'El pedido me llego sin envolver, parecia hasta usada, Fatal.', '2022-04-17');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('maria', 4, 2, 4, 'La compre por que me la recomendaron, esperando a que llegue.', '2022-04-12');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('luis', 5, 3, 2, 'Muy mala, el color se quita si le da mucho el sol, y se estropea.', '2022-04-13');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('manuel', 1, 4, 4, 'Muy contento, el vaquero se ajusta muy bien al cuerpo y se ve bastante bueno.', '2022-04-15');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('marta', 2, 5, 3, 'Parece que resiste aun que no termina de convencerme, esperemos que dure.', '2022-04-16');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('luis', 3, 6, 4, 'Contento con el producto, no es lo mejor pero cumple precio/calidad.', '2022-04-16');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('manuel', 4, 7, 5, 'Muy contenta, es incluso mejor que la original, compraré otra para tenerla guardada.', '2022-04-14');");
        db.execSQL("insert into valoraciones(nick, id_com, id_prod, valoracion, comentario, fecha) values('miriam', 5, 1, 4, 'Se la compre a mi chico, muy contentos.', '2022-04-14');");

        // INSERT TABLA PRODUCTOS_COMERCIOS
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (1,1,2,15,3,12,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (1,2,1,12,1,11,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (1,3,null,17,0,17,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (1,4,3,7,1,6,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (1,5,1,9,0,9,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (2,1,2,15,3,12,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (2,2,1,12,1,11,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (2,3,null,17,0,17,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (2,4,3,7,1,6,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (2,5,1,9,0,9,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (3,1,2,15,3,12,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (3,2,1,12,1,11,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (3,3,null,17,0,17,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (3,4,3,7,1,6,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (3,5,1,9,0,9,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (4,1,2,30,4,26,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (4,2,1,28,0,28,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (4,3,null,29,5,24,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (4,4,3,25,3,22,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (4,5,1,26,2,24,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (5,1,2,30,4,26,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (5,2,1,28,0,28,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (5,3,null,29,5,24,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (5,4,3,25,3,22,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (5,5,1,26,2,24,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (6,1,2,30,4,26,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (6,2,1,28,0,28,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (6,3,null,29,5,24,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (6,4,3,25,3,22,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (6,5,1,26,2,24,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (7,1,2,50,2,48,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (7,2,1,53,1,52,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (7,3,null,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (7,4,3,47,5,42,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (7,5,1,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (8,1,2,50,2,48,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (8,2,1,53,1,52,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (8,3,null,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (8,4,3,47,5,42,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (8,5,1,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (9,1,2,50,2,48,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (9,2,1,53,1,52,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (9,3,null,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (9,4,3,47,5,42,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (9,5,1,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (10,1,2,45,0,45,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (10,2,1,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (10,3,null,50,0,50,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (10,4,3,42,0,42,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (10,5,1,44,0,44,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (11,1,2,45,0,45,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (11,2,1,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (11,3,null,50,0,50,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (11,4,3,42,0,42,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (11,5,1,44,0,44,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (12,1,2,45,0,45,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (12,2,1,49,0,49,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (12,3,null,50,0,50,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (12,4,3,42,0,42,200);");
        db.execSQL("insert into productos_comercios(id_prod, id_com, id_prom, precio_total, descuento, importe_total_descontado, cantidad) values (12,5,1,44,0,44,200);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLA_USUARIOS);
        db.execSQL("DROP TABLE " + TABLA_COMERCIOS);
        db.execSQL("DROP TABLE " + TABLA_CATEGORIAS);
        db.execSQL("DROP TABLE " + TABLA_PRODUCTOS);
        db.execSQL("DROP TABLE " + TABLA_PROMOCIONES);
        db.execSQL("DROP TABLE " + TABLA_VALORACIONES);
        db.execSQL("DROP TABLE " + TABLA_PRODUCTOS_COMERCIOS);

        onCreate(db);
    }
}