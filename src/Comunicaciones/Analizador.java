package Comunicaciones;

import Negocio.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import javax.mail.MessagingException;


public class Analizador {

    POP3 entrada;
    Mailer salida;
    LinkedList<String> parametros;
    String from;
    String stat;

    public void analizar() throws MessagingException {
        this.salida = new Mailer();
        Validador validador = new Validador();
        String comando = parametros.get(0);
        if (!validador.isComando(comando)) {
            System.out.println("COMANDO: " + comando + " INVALIDO");
            salida.sendHtmlEmail(from, "Comando " + comando + " no encontrado", ToHTML.getHTMLMessage(
                    "Comando " + comando + " no encontrado", "Para consultar los comandos escriba y envie HELP "));
            return;
        } else {
            System.out.println("COMANDO: " + comando);
        }

        switch (comando) {
            case "HELP":
                System.out.println("");
                salida.sendHtmlEmail(from, "Tabla de comandos \"MINI TIK TOK\"",
                        ToHTML.getHTMLMessage("Comando: " + comando, ToHTML.getHelpTable()));
                break;
            case "REGUSER":
                System.out.println("");
                if (parametros.size() == 8) {
                    String nombres = parametros.get(1);
                    String apellidos = parametros.get(2);
                    String correo = parametros.get(3);
                    String contrasena = parametros.get(4);
                    String paisNacionalidad = parametros.get(5);
                    String tipoUsuario = parametros.get(6);
                    String perfilId = parametros.get(7);

                    boolean validarCompos = validador.validarCamposRegistrarUsuario(
                            nombres,
                            apellidos,
                            correo,
                            contrasena,
                            paisNacionalidad,
                            tipoUsuario,
                            perfilId);
                    if (validarCompos) {
                        LinkedList<String> arg = new LinkedList<>();
                        for (int i = 1; i <= 7; i++) {
                            arg.add(parametros.get(i));
                        }

                        NUsuario nUsuario = new NUsuario();
                        boolean insercionExitosa = nUsuario.insert(arg);
                        if (insercionExitosa) {
                            salida.sendHtmlEmail(from, "EL USUARIO HA SIDO REGISTRADO",
                                    ToHTML.getHTMLMessage("El usuario ha sido registrado",
                                            "El nuevo usuario ha sido registrado y guardado en el sistema"));
                        } else {
                            salida.sendHtmlEmail(from, "EL USUARIO NO HA SIDO REGISTRADO",
                                    ToHTML.getHTMLMessage("El usuario no ha sido registrado",
                                            "El nuevo usuario no ha sido registrado"));
                        }
                    } else {
                        System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                        salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO",
                                ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto",
                                        "Verifique que los datos enviados tengan el formato y orden correcto"));
                    }
                } else {
                    System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                    salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage(
                            "Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
                }
                break;
            case "LISTUSER":
                if (parametros.size() == 2) {
                    String segundoParametro = parametros.get(1);
                    String[] partes = segundoParametro.split("=");
                    boolean valido = validador.validarCampoBuscarUsuario(partes[0]);
                    if (valido) {
                        NUsuario nUsuario = new NUsuario();
                        LinkedList<String> datos = new LinkedList<>();
                        datos = nUsuario.mostrarPorCampo(partes[0], partes[1]);
                        if (datos.size() > 0) {
                            LinkedList<String> campos = new LinkedList<>();
                            campos.add("id");
                            campos.add("nombres");
                            campos.add("apellidos");
                            campos.add("correo");
                            campos.add("contraseña");
                            campos.add("pais_nacionalidad");
                            campos.add("tipo_usuario");
                            campos.add("perfil_id");
                            salida.sendHtmlEmail(from, "Comando: " + comando + " enviado",
                                    ToHTML.getHTMLMessage(
                                            "LISTAR USUARIOS con el campo " + partes[0] + ": " + partes[1],
                                            ToHTML.getTable(datos, campos)));
                        } else {
                            salida.sendHtmlEmail(from, "NO HAY USUARIOS",
                                    ToHTML.getHTMLMessage("No hay usuarios", "No hay usuarios en el sistema"));
                        }
                    } else {
                        System.out.println("ERROR: EL CAMPO DE BUSQUEDA ES INCORRECTO");
                        salida.sendHtmlEmail(from, "ERROR: EL CAMPO DE BUSQUEDA ES INCORRECTO",
                                ToHTML.getHTMLMessage("El campo de busqueda es incorrecto",
                                        "Verifique que el campo de busqueda sea correcto"));
                    }
                } else {
                    System.out.println();
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("id");
                    campos.add("nombres");
                    campos.add("apellidos");
                    campos.add("correo");
                    campos.add("contraseña");
                    campos.add("pais_nacionalidad");
                    campos.add("tipo_usuario");
                    campos.add("perfil_id");
                    NUsuario nUsuario = new NUsuario();
                    LinkedList<String> datos = new LinkedList<>();
                    datos = nUsuario.mostrar();
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado",
                            ToHTML.getHTMLMessage("LISTAR USUARIOS", ToHTML.getTable(datos, campos)));
                }
                break;
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // DEVUELVE LA VARIABLE STAT [4]
    public int getStat() {
        return Integer.parseInt(this.stat);
    }
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // HACE UNA LECTURA DE LOS NUEVOS MENSAJES RECIBIDOS [2]
    public void setStat() {
        this.entrada = new POP3();
        try {
            entrada.connect();
            entrada.logIn();
            this.stat = entrada.getStat();
            entrada.logOut();
            entrada.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // MODIFICA LA VARIABLE STAT, SEGUN EL VALOR DADO [3]

    public void setStat(int stat) {
        this.stat = String.valueOf(stat);
    }
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void setFrom() {
        this.entrada = new POP3();
        try {
            entrada.connect();
            entrada.logIn();
            this.from = entrada.getFrom(this.stat);
            entrada.logOut();
            entrada.close();
            System.out.println("From: " + this.from);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // METODO AUXILIARES
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // SEPARA EL SUBJECT, OBTENIENDO ASI LAS DIRECTRICES NECESARIAS [1]
    public void separar() {
        this.entrada = new POP3();
        this.parametros = new LinkedList<>();
        String subject = "";
        try {
            this.entrada.connect();
            this.entrada.logIn();
            subject = this.entrada.getSubject(this.stat);

            this.entrada.logOut();
            this.entrada.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        subject = subject.trim();
        if (!"".equals(subject)) {
            String[] partes = subject.split(":", -1);
            // String[] partes = subject.split(";");
            this.parametros.addAll(Arrays.asList(partes));
            System.out.println("Parametros: " + this.parametros);
        }
    }

}
