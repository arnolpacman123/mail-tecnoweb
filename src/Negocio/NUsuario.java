package Negocio;

import java.util.LinkedList;

import Datos.DUsuario;

public class NUsuario {
    DUsuario dUsuario = new DUsuario();

    public boolean insert(LinkedList<String> arg) {
        dUsuario.setNombres(arg.get(0));
        dUsuario.setApellidos(arg.get(1));
        dUsuario.setCorreo(arg.get(2));
        dUsuario.setContrasena(arg.get(3));
        dUsuario.setPaisNacionalidad(arg.get(4));
        dUsuario.setTipoUsuario(arg.get(5));
        dUsuario.setPerfilId(arg.get(6));	
        return dUsuario.insert();
    }

    public LinkedList<String> mostrar() {
        return dUsuario.mostrar();
    }

    public LinkedList<String> mostrarPorCampo(String campo, String valor) {
        return dUsuario.mostrarPorCampo(campo, valor);
    }
}
