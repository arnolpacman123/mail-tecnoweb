package Datos;

import java.sql.ResultSet;
import java.util.LinkedList;

public class DUsuario {
    private String id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String paisNacionalidad;
    private String tipoUsuario;
    private String estado;
    private String perfilId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return "'" + nombres + "'";
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return "'" + apellidos + "'";
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return "'" + correo + "'";
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return "'" + contrasena + "'";
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPaisNacionalidad() {
        return "'" + paisNacionalidad + "'";
    }

    public void setPaisNacionalidad(String paisNacionalidad) {
        this.paisNacionalidad = paisNacionalidad;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {

        this.tipoUsuario = tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(String perfilId) {
        if (perfilId.equals("")) {
            perfilId = "null";
        }
        this.perfilId = perfilId;
    }

    public DUsuario() {

    }

    public boolean insert() {
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "INSERT INTO usuario(nombres, apellidos, correo, contrasena, pais_nacionalidad, tipo_usuario, perfil_id) VALUES ("
                        + getNombres() + "," + getApellidos() + "," + getCorreo() + "," + getContrasena() + ","
                        + getPaisNacionalidad() + "," + getTipoUsuario() + "," + getPerfilId() + ")";
                System.out.println(sql);
                connection.insert(sql);
                connection.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public LinkedList<String> mostrar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "SELECT * FROM usuario";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(result.getString("id"));
                    datos.add(result.getString("nombres"));
                    datos.add(result.getString("apellidos"));
                    datos.add(result.getString("correo"));
                    datos.add(result.getString("contrasena"));
                    datos.add(result.getString("pais_nacionalidad"));
                    datos.add(result.getString("tipo_usuario"));
                    String perfilId = result.getString("perfil_id");
                    if (perfilId == null) {
                        perfilId = "";
                    }
                    datos.add(perfilId);
                }
                System.out.println(datos);
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return datos;
    }

    public LinkedList<String> mostrarPorCampo(String campo, String valor) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "SELECT * FROM usuario WHERE " + campo + " LIKE " + "'%" + valor + "%'";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(result.getString("id"));
                    datos.add(result.getString("nombres"));
                    datos.add(result.getString("apellidos"));
                    datos.add(result.getString("correo"));
                    datos.add(result.getString("contrasena"));
                    datos.add(result.getString("pais_nacionalidad"));
                    datos.add(result.getString("tipo_usuario"));
                    String perfilId = result.getString("perfil_id");
                    if (perfilId == null) {
                        perfilId = "";
                    }
                    datos.add(perfilId);
                }
                System.out.println(datos);
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return datos;
    }
}
