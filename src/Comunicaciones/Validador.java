package Comunicaciones;

public class Validador {

    private static final String[] COMANDOS_VALIDOS = { "HELP", "REGUSER", "LISTUSER" };

    private String[] PAISES = {
            "Afganistán",
            "Alemania",
            "Arabia Saudita",
            "Argentina",
            "Australia",
            "Bélgica",
            "Bolivia",
            "Brasil",
            "Camboya",
            "Canadá",
            "Chile",
            "China",
            "Colombia",
            "Corea",
            "Costa Rica",
            "Cuba",
            "Dinamarca",
            "Ecuador",
            "Egipto",
            "El Salvador",
            "Escocia",
            "España",
            "Estados Unidos",
            "Estonia",
            "Etiopia",
            "Filipinas",
            "Finlandia",
            "Francia",
            "Gales",
            "Grecia",
            "Guatemala",
            "Haití",
            "Holanda",
            "Honduras",
            "Indonesia",
            "Inglaterra",
            "Irak",
            "Irán",
            "Irlanda",
            "Israel",
            "Italia",
            "Japón",
            "Jordania",
            "Laos",
            "Letonia",
            "Lituania",
            "Malasia",
            "Marruecos",
            "México",
            "Nicaragua",
            "Noruega",
            "Nueva Zelanda",
            "Panamá",
            "Paraguay",
            "Perú",
            "Polonia",
            "Portugal",
            "Puerto Rico",
            "Republica Dominicana",
            "Rumania",
            "Rusia",
            "Suecia",
            "Suiza",
            "Tailandia",
            "Taiwán",
            "Turquía",
            "Ucrania",
            "Uruguay",
            "Venezuela",
            "Vietnam",
    };

    public static String[] camposTablaUsuario = {
            "nombres",
            "apellidos",
            "correo",
            "pais_nacionalidad",
            "tipo_usuario",
    };

    /************* UTILIZADO PARA VERIFICAR SI ES UN COMANDO VALIDO ***************/
    public boolean isComando(String comando) {
        for (String COMANDOS_VALIDOS1 : COMANDOS_VALIDOS) {
            if (comando.equals(COMANDOS_VALIDOS1)) {
                return true;
            }
        }
        return false;
    }

    private String expresionValidadoraDeNombres = "([A-Z]{1}[a-z]{1,})(( )([A-Z]{1}[a-z]{1,})){0,}";

    public boolean validarCamposRegistrarUsuario(String nombres, String apellidos, String correo, String contrasena,
            String paisNacionalidad, String tipoUsuario, String perfilId) {
        return validarNombres(nombres) && validarApellidos(apellidos) && validarCorreo(correo)
                && validarContrasena(contrasena) && validarPaisNacionalidad(paisNacionalidad)
                && validarTipoUsuario(tipoUsuario) && validarPerfilId(perfilId);
    }

    public boolean validarCampoBuscarUsuario(String parametro) {
        for (String campo : camposTablaUsuario) {
            if (parametro.equals(campo)) {
                return true;
            }
        }
        return false;
    }

    public boolean validarNombres(String nombres) {
        return nombres.matches(expresionValidadoraDeNombres);
    }

    public boolean validarApellidos(String apellidos) {
        return apellidos.matches(expresionValidadoraDeNombres);
    }

    public boolean validarCorreo(String correo) {
        return correo.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
    }

    public boolean validarContrasena(String contrasena) {
        return contrasena.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&._[-]]).{8,20}$");
    }

    public boolean validarPaisNacionalidad(String paisNacionalidad) {
        return existePais(paisNacionalidad);
    }

    private boolean existePais(String paisNacionalidad) {
        for (String pais : PAISES) {
            if (pais.equals(paisNacionalidad)) {
                return true;
            }
        }
        return false;
    }

    public boolean validarTipoUsuario(String tipoUsuario) {
        return tipoUsuario.matches("[0-9]+");
    }

    private boolean validarPerfilId(String perfilId) {
        return perfilId.equals("") || perfilId.toLowerCase().equals("null") || perfilId.matches("[0-9]+");
    }

    /***************
     * UTILIZADO PARA VERIFICAR SI ES UN NOMBRA VALIDO
     *****************/
    // public boolean isName(String name) {
    // return (name.length() <= 100);
    // }

    // /************* UTILIZADO PARA VERIFICAR SI ES UNA FECHA VALIDA
    // ***************/
    // public boolean isFecha(String fecha) {
    // try {
    // DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    // df.setLenient(false);
    // df.parse(fecha);
    // return true;
    // } catch (ParseException e) {
    // return false;
    // }
    // }

    // public boolean isEmail(String email) {
    // return (email.length() <= 100 && email.matches(".*@.*"));
    // }

    // public boolean isLogin(String login) {
    // return (login.length() > 0 && login.length() <= 100);
    // }

    // public boolean isPassword(String pass) {
    // return (pass.length() > 0 && pass.length() <= 30);
    // }

    // /************* UTILIZADO PARA VERIFICAR SI ES UN SEXO VALIDO ***************/
    // public boolean isSexo(String sexo) {
    // return (sexo.equals("M") || sexo.equals("F"));
    // }

    // /************* UTILIZADO PARA VERIFICAR SI ES UN TELE VALIDO ***************/
    // public boolean isCelular(String celular) {
    // return celular.matches("[0-9]{3,12}$");
    // }

    // public boolean isCI(String ci) {
    // return ci.matches("[0-9]{3,9}$");
    // }

    // public boolean isNumber(String id) {
    // try {
    // Integer.parseInt(id);
    // return true;
    // } catch (NumberFormatException e) {
    // return false;
    // }
    // }

    // /************* UTILIZADO PARA VERIFICAR SI ES UN COMANDO VALIDO
    // ***************/
    // public boolean isMensaje(String data) {
    // return (data.length() <= 160 && data.matches("^[a-zA-Z0-9() .,]+$"));
    // }

    // public boolean isDireccion(String data) {
    // return (data.length() > 0 && data.length() <= 100);
    // }

    // public boolean isUbicacion(String data) {
    // return (data.length() > 0 && data.length() <= 60);
    // }

    // public boolean isDimension(String data) {
    // return (data.length() > 0 && data.length() <= 10);
    // }

    // public boolean isEstado(String estado) {
    // return estado.equals("curso") || estado.equals("enviado");
    // }

    // public boolean isAsunto(String asunto) {
    // return asunto.equals("reclamo") || asunto.equals("sugerencia") ||
    // asunto.equals("personal");
    // }

    // public boolean isFloat(String flota) {
    // return flota.matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$");
    // }

    // public boolean isAll(String all) {
    // return all.equals("*");
    // }

    // public boolean isTipo(String tipo) {
    // if (tipo.equals("ADMINISTRADOR")) {
    // return true;
    // } else {
    // String[] partes = tipo.split("\\|");
    // if (partes[0].equals("PROPIETARIO") && (partes[1].equals("DUENO")))
    // return true;
    // if (partes.length == 3) {
    // if (partes[0].equals("PROPIETARIO") && partes[1].equals("INQUILINO") &&
    // isFecha(partes[2]))
    // return true;
    // }
    // }
    // return false;
    // }

    // public boolean isCargo(String cargo) {
    // return (cargo.length() > 0 && cargo.length() <= 30);
    // }

    // public boolean isTipoUser(String tipo) {
    // return tipo.equals("COMITE") || tipo.equals("PROPIETARIO");
    // }

    // public boolean isTipoManzana(String tipo) {
    // return tipo.equals("urbano") || tipo.equals("area verde") ||
    // tipo.equals("parque") || tipo.equals("rural");
    // }

    // public boolean isTipoComunicado(String tipo) {
    // return tipo.equals("reunion") || tipo.equals("actividad") ||
    // tipo.equals("recomendacion");
    // }

    // public boolean isTipoActa(String tipo) {
    // return tipo.equals("descriptiva") || tipo.equals("informativa");
    // }

    // public boolean isFiltro(String filtro) {
    // return filtro.equals("ADMINISTRADOR") || filtro.equals("COMITE") ||
    // filtro.equals("PROPIETARIO")
    // || filtro.equals("*");
    // }

    public static void main(String args[]) {
    }

}