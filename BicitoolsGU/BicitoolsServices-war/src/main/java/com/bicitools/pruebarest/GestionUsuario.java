package com.bicitools.pruebarest;

import com.bicitools.dao.ConexionesDAOLocal;
import com.bicitools.dao.UsuarioDAOLocal;
import com.bicitools.dao.VendedorDAOLocal;
import com.bicitools.entity.Conexiones;
import com.bicitools.entity.Usuario;
import com.bicitools.entity.Vendedor;
import com.bicitools.enviarcorreo.EnviaCorreo;
import com.bicitools.mjson.gestionusuario.ActualizarPerfilJson;
import com.bicitools.mjson.gestionusuario.ActualizarPerfilVendedorJson;
import com.bicitools.mjson.gestionusuario.CerrarSesionJson;
import com.bicitools.mjson.gestionusuario.ConstruyeRespuesta;
import com.bicitools.mjson.gestionusuario.GetConexionJson;
import com.bicitools.mjson.gestionusuario.GetUsuariobyUsernameJson;
import com.bicitools.mjson.gestionusuario.LoginUsuarioFacebookJson;
import com.bicitools.mjson.gestionusuario.LoginUsuarioJson;
import com.bicitools.mjson.gestionusuario.LoginUsuarioTwitterJson;
import com.bicitools.mjson.gestionusuario.ObtenerDetallesPerfilVendedorJson;
import com.bicitools.mjson.gestionusuario.ObtenerDetallesUsuarioJson;
import com.bicitools.mjson.gestionusuario.RecuperarClaveJson;
import com.bicitools.mjson.gestionusuario.RegistrarPerfilVendedorJson;
import com.bicitools.mjson.gestionusuario.RegistrarUsuarioFacebookJson;
import com.bicitools.mjson.gestionusuario.RegistrarUsuarioJson;
import com.bicitools.mjson.gestionusuario.RegistrarUsuarioTwitterJson;
import com.bicitools.mjson.gestionusuario.RespuestaJson;
import com.bicitools.mjson.gestionusuario.SetConexionJson;
import com.bicitools.mjson.gestionusuario.TemporalConexion;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author TaidyJohana
 */
@Path("/gestionusuario")
@Stateless
public class GestionUsuario {
    boolean variableTemporal=true;
    @EJB
    private UsuarioDAOLocal usuarioDAOLocal;
    @EJB
    private VendedorDAOLocal vendedorDAOLocal;
    @EJB
    private ConexionesDAOLocal conexionesDAOLocal;

    
    
    //Obligatorio, GestionUsuario   
    
    //@AnotacionVariabilidad (featureName = FeatureName.GestionUsuario)
    @POST
    @Path("/registrarUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson registrarUsuario(RegistrarUsuarioJson usuarioJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<Integer> dato = new ArrayList<Integer>();
        try {
            int idUusario = usuarioDAOLocal.registrarUsuario(usuarioJson.getNumeroIdentificacion(), usuarioJson.getTipoIdentificacion(),
                    usuarioJson.getTipoPerfil(), usuarioJson.getGenero(), usuarioJson.getNombres(), usuarioJson.getApellidos(),
                    usuarioJson.getFoto(), usuarioJson.getCorreo(), usuarioJson.getFechaNacimiento(), usuarioJson.getDireccionCasa(), usuarioJson.getDireccionTrabajo(),
                    usuarioJson.getTelefonoFijo(), usuarioJson.getTelefonoMovil(), usuarioJson.getFacebookUser(), usuarioJson.getTwitterUser(),
                    usuarioJson.getUsuario(), usuarioJson.getContrasenia());

            dato.add(idUusario);
            new ArrayList().add(idUusario);
            if (idUusario != 0) {
                respuesta = ConstruyeRespuesta.construyeRespuestaConDatos(0, "info", "Usuario Registrado Correctamente", dato);
            } else {
                respuesta = ConstruyeRespuesta.construyeRespuestaFalla("Error al insertar", dato);
            }
        } catch (Exception e) {
            System.out.println("noguardo");
            dato.add(0);
            respuesta = ConstruyeRespuesta.construyeRespuestaFalla("Error al insertar", dato);
        }
    return respuesta;
    }

    //Obligatorio, Seguridad
//@AnotacionVariabilidad (featureName = FeatureName.Seguridad)
    @POST
    @Path("/loginUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson loginUsuario(LoginUsuarioJson usuarioJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<Integer> datos = new ArrayList<Integer>();
        try {
            int id_usuario = usuarioDAOLocal.loginUsuario(usuarioJson.getUser(), usuarioJson.getContrasenia());
            if (id_usuario != 0) {
                datos.add(id_usuario);
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Inicio Correctamente");
                respuesta.setDatos(datos);
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error de acceso");
                respuesta.setDatos(datos);
            }
        } catch (Exception e) {
            System.out.println("noguardo");
            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error de acceso");
            respuesta.setDatos(datos);
        }
        return respuesta;
    }

    //Obligatorio, GestionUsuario
    //@AnotacionVariabilidad (featureName = FeatureName.GestionUsuario)
    @POST
    @Path("/getUsuariobyUsername")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson getUsuariobyUsername(GetUsuariobyUsernameJson usuarioJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList datos = new ArrayList();
        try {
            datos = usuarioDAOLocal.getUsuariobyUsername(usuarioJson.getUsuario());
            if (datos != null) {
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("ok");
                respuesta.setDatos(datos);
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error mostrar datos de usuario");
            }
        } catch (Exception e) {
            System.out.println("noguardo");
            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error mostrar datos de usuario");
        }

        return respuesta;
    }

    //Obligatorio, Seguridad
    //@AnotacionVariabilidad (featureName = FeatureName.Seguridad)
    @POST
    @Path("/recuperarClave")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson recuperarClave(RecuperarClaveJson usuarioJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<String> datos = new ArrayList<String>();

        try {
            String clave = usuarioDAOLocal.recuperarClave(usuarioJson.getCorreo());
            if (!clave.equals("error")) {
                // datos.add(clave);
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Correo con clave enviado");
                respuesta.setDatos(datos);
                System.out.print("antesenvio");
                EnviaCorreo e = new EnviaCorreo();
                e.enviarCorreo(usuarioJson.getCorreo(), clave);
                System.out.print("despuesenvio");
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("usuario no encontrado");
                respuesta.setDatos(datos);
            }
        } catch (Exception e) {
            System.out.println("noguardo");
            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("usuario no encontrado");
            respuesta.setDatos(datos);
        }

        return respuesta;
    }


    //Obligatorio, Seguridad
    //@AnotacionVariabilidad (featureName = FeatureName.Seguridad)
    @POST
    @Path("/setConexion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson setConexion(SetConexionJson conexionJson) {
        RespuestaJson respuesta = new RespuestaJson();
        try {
            String res = conexionesDAOLocal.setConexion(conexionJson.getId_usuario(), new Date(), conexionJson.getTipoConexion());
            if (res.equals("ok")) {
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Conexion establecida");
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error de conexion");
            }
        } catch (Exception e) {
            System.out.println("noguardo");
            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error de conexion");
        }

        return respuesta;
    }


    //Obligatorio, Seguridad
    //@AnotacionVariabilidad (featureName = FeatureName.Seguridad)
    @POST
    @Path("/getConexion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson getConexion(GetConexionJson conexionJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<Conexiones> datos = new ArrayList<Conexiones>();
        ArrayList datosEnviar = new ArrayList();
        try {
            datos = conexionesDAOLocal.getConexion(conexionJson.getId_usuario());
            if (datos != null) {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (int i = 0; i < datos.size(); i++) {
                    String s = formatter.format(datos.get(i).getFechaConexion());
                    TemporalConexion tc = new TemporalConexion(datos.get(i).getIdConexion(), datos.get(i).getIdUsuario(), s, datos.get(i).getTipoConexion());
                    datosEnviar.add(tc);
                }
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Lista de conexiones");
                respuesta.setDatos(datosEnviar);
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error de acceso");
                respuesta.setDatos(datos);
            }
        } catch (Exception e) {
            System.out.println("noguardo");
            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error de acceso");
            respuesta.setDatos(datos);
        }
        return respuesta;
    }

    //Oblogatorio, GestionUsuario
    //@AnotacionVariabilidad (featureName = FeatureName.GestionUsuario)    
    @POST
    @Path("/obtenerDetallesUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson obtenerDetallesUsuario(ObtenerDetallesUsuarioJson usuarioJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<String> datos = new ArrayList<String>();
        try {
            Usuario usuario = new Usuario();
            usuario = usuarioDAOLocal.obtenerDetallesUsuario(usuarioJson.getId_usuario());

            if (usuario != null) {
                datos.add(String.valueOf(usuario.getNumeroIdentificacion()));
                datos.add(String.valueOf(usuario.getTipoIdentificacion()));
                datos.add(String.valueOf(usuario.getTipoPerfil()));
                datos.add(String.valueOf(usuario.getGenero()));
                datos.add(usuario.getNombres());
                datos.add(usuario.getApellidos());
                datos.add(usuario.getFoto());
                datos.add(usuario.getCorreo());
                datos.add(usuario.getFechaNacimiento());
                datos.add(usuario.getDireccionCasa());
                datos.add(usuario.getDireccionTrabajo());
                datos.add(usuario.getTelefonoFijo());
                datos.add(usuario.getTelefonoMovil());
                datos.add(usuario.getFacebookUser());
                datos.add(usuario.getTwitterUser());
                datos.add(usuario.getUsuario());
                datos.add(usuario.getContrasenia());
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("ok");

                respuesta.setDatos(datos);
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error mostrar datos de usuario");

            }
        } catch (Exception e) {
            System.out.println("noguardo");

            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error mostrar datos de usuario");
        }

        return respuesta;
    }


    //Obligatorio, Seguridad
    //@AnotacionVariabilidad (featureName = FeatureName.Seguridad)
    @POST
    @Path("/cerrarSesion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson cerrarSesion(CerrarSesionJson cerrarSesionJson) {
        RespuestaJson respuesta = new RespuestaJson();
        try {
            String res = conexionesDAOLocal.setConexion(cerrarSesionJson.getId_usuario(), new Date(), cerrarSesionJson.getTipoConexion());
            if (res.equals("ok")) {
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Cerro sesion correctamente");
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error de logout");
            }
        } catch (Exception e) {
            System.out.println("noguardo");
            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error de logout");
        }

        return respuesta;
    }
    
    //Variable, ManejoPerfiles
    //@AnotacionVariabilidad (featureName = FeatureName.ManejoPerfiles)
    @POST
    @Path("/registrarPerfilVendedor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson registrarPerfilVendedor(RegistrarPerfilVendedorJson vendedorJson) {
        RespuestaJson respuesta = new RespuestaJson();

        try {
            String res = vendedorDAOLocal.registrarPerfilVendedor(vendedorJson.getId_usuario(),
                    vendedorJson.getNombre_establecimiento(), vendedorJson.getDireccion_establecimiento(), vendedorJson.getTelefono_establecimiento(),
                    vendedorJson.getCelular_establecimiento(), vendedorJson.getCorreo_establecimiento(), vendedorJson.getNit_establecimiento(),
                    vendedorJson.getFoto_establecimiento());
            if (res.equals("ok")) {

                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Perfil de vendedor registrado correctamente");
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error al registrar perfil");
            }
        } catch (Exception e) {
            System.out.println("noguardo");

            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error al registrar perfil");
        }

        return respuesta;
    }

    
    //Variable, ManejoPerfiles
    //@AnotacionVariabilidad (featureName = FeatureName.ManejoPerfiles)
    @POST
    @Path("/actualizarPerfil")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson actualizarPerfil(ActualizarPerfilJson actualizarPerfilJson) {
  
        
        RespuestaJson respuesta = new RespuestaJson();

        try {

            String res = usuarioDAOLocal.actualizarPerfil(actualizarPerfilJson.getId_usuario(), actualizarPerfilJson.getNumeroIdentificacion(),
                    actualizarPerfilJson.getTipoIdentificacion(), actualizarPerfilJson.getTipoPerfil(),
                    actualizarPerfilJson.getGenero(), actualizarPerfilJson.getNombres(), actualizarPerfilJson.getApellidos(),
                    actualizarPerfilJson.getFoto(), actualizarPerfilJson.getCorreo(),
                    actualizarPerfilJson.getFechaNacimiento(), actualizarPerfilJson.getDireccionCasa(), actualizarPerfilJson.getDireccionTrabajo(),
                    actualizarPerfilJson.getTelefonoFijo(), actualizarPerfilJson.getTelefonoMovil(), actualizarPerfilJson.getContrasenia()
            );

            if (res.equals("ok")) {

                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Perfil actualizado");
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error al actualizar perfil");
            }
        } catch (Exception e) {
            System.out.println("noguardo");

            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error al actualizar perfil");
        }

        return respuesta;
    }

    //Variable, ManejoPerfiles
    //@AnotacionVariabilidad (featureName = FeatureName.ManejoPerfiles)
    @POST
    @Path("/actualizarPerfilVendedor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson actualizarPerfilVendedor(ActualizarPerfilVendedorJson vendedorJson) {
      
        RespuestaJson respuesta = new RespuestaJson();

        try {

            String res = vendedorDAOLocal.actualizarPerfilVendedor(vendedorJson.getId_usuario(),
                    vendedorJson.getNombre_establecimiento(), vendedorJson.getDireccion_establecimiento(), vendedorJson.getTelefono_establecimiento(),
                    vendedorJson.getCelular_establecimiento(), vendedorJson.getCorreo_establecimiento(), vendedorJson.getNit_establecimiento(),
                    vendedorJson.getFoto_establecimiento());

            if (res.equals("ok")) {

                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Perfil vendedor actualizado");
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error al actualizar perfil vendedor");
            }
        } catch (Exception e) {
            System.out.println("noguardo");

            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error al actualizar perfil vendedor");
        }

        return respuesta;
    }

    //Opcional, ManejoPerfiles
    //@AnotacionVariabilidad (featureName = FeatureName.ManejoPerfiles)
    @POST
    @Path("/obtenerDetallePerfilVendedor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson obtenerDetallePerfilVendedor(ObtenerDetallesPerfilVendedorJson usuarioJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<String> datos = new ArrayList<String>();
        try {

            Vendedor vendedor = new Vendedor();
            vendedor = vendedorDAOLocal.obtenerDetallesUsuario(usuarioJson.getId_usuario());

            if (vendedor != null) {

                datos.add(vendedor.getNombreEstablecimiento());
                datos.add(vendedor.getDireccionEstablecimiento());
                datos.add(vendedor.getTelefonoEstablecimiento());
                datos.add(vendedor.getCelularEstablecimiento());
                datos.add(vendedor.getCorreoEstablecimiento());
                datos.add(vendedor.getNitEstablecimiento());
                datos.add(vendedor.getFotoEstablecimiento());

                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("ok");

                respuesta.setDatos(datos);
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error mostrar datos de usuario");

            }
        } catch (Exception e) {
            System.out.println("noguardo");

            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error mostrar datos de usuario");
        }

        return respuesta;
    }
    
    //Opcional, redesSociales
    //@AnotacionVariabilidad (featureName = FeatureName.RedesSociales)
    @POST
    @Path("/loginUsuarioFacebook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson loginUsuarioFacebook(LoginUsuarioFacebookJson usuarioJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<String> datos = new ArrayList<String>();
        try {
            Usuario usuario = new Usuario();
            usuario = usuarioDAOLocal.loginUsuarioFacebook(usuarioJson.getFacebookToken());
            if (usuario != null) {
                datos.add(String.valueOf(usuario.getId()));
                datos.add(usuario.getUsuario());
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Inicio Correctamente");
                respuesta.setDatos(datos);
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error de login");

            }
        } catch (Exception e) {

            System.out.println("noguardo");

            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error de login");
        }

        return respuesta;
    }

    //Opcional, redesSociales
//@AnotacionVariabilidad (featureName = FeatureName.RedesSociales)
    @POST
    @Path("/loginUsuarioTwitter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson loginUsuarioTwitter(LoginUsuarioTwitterJson usuarioJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<String> datos = new ArrayList<String>();
        try {
            Usuario usuario = new Usuario();
            usuario = usuarioDAOLocal.loginUsuarioTwitter(usuarioJson.getTwitterToken());
            if (usuario != null) {
                datos.add(String.valueOf(usuario.getId()));
                datos.add(usuario.getUsuario());
                respuesta.setCodigo(0);
                respuesta.setValor("info");
                respuesta.setDescripcion("Inicio Correctamente");
                respuesta.setDatos(datos);
            } else {
                respuesta.setCodigo(101);
                respuesta.setValor("error");
                respuesta.setDescripcion("Error de login");

            }
        } catch (Exception e) {
            System.out.println("noguardo");

            respuesta.setCodigo(101);
            respuesta.setValor("error");
            respuesta.setDescripcion("Error de login");
        }

        return respuesta;
    }
    
    //Opcional Redes Sociales
    //@AnotacionVariabilidad (featureName = FeatureName.RedesSociales)
    @POST
    @Path("/registrarUsuarioFacebook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaJson registrarUsuarioFacebook(RegistrarUsuarioFacebookJson usuarioFacebookJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<Integer> dato = new ArrayList<Integer>();
        try {
            System.out.print("entro registro222.....");
            int idUusario = usuarioDAOLocal.registrarUsuarioFacebook(usuarioFacebookJson.getNumeroIdentificacion(), usuarioFacebookJson.getTipoIdentificacion(),
                    usuarioFacebookJson.getTipoPerfil(), usuarioFacebookJson.getGenero(), usuarioFacebookJson.getNombres(), usuarioFacebookJson.getApellidos(),
                    usuarioFacebookJson.getFoto(), usuarioFacebookJson.getCorreo(), usuarioFacebookJson.getFechaNacimiento(), usuarioFacebookJson.getDireccionCasa(),
                    usuarioFacebookJson.getDireccionTrabajo(), usuarioFacebookJson.getTelefonoFijo(),
                    usuarioFacebookJson.getTelefonoMovil(), usuarioFacebookJson.getFacebookUser(), usuarioFacebookJson.getFacebookToken(),
                    usuarioFacebookJson.getTwitterUser(), usuarioFacebookJson.getUsuario(), usuarioFacebookJson.getContrasenia());
            System.out.print("SALIO registro.....");
            dato.add(idUusario);
            new ArrayList().add(idUusario);
            if (idUusario != 0) {
                respuesta = ConstruyeRespuesta.construyeRespuestaConDatos(0, "info", "Usuario Registrado con Lgin de facebook Correctamente", dato);
            } else {
                respuesta = ConstruyeRespuesta.construyeRespuestaFalla("Error al insertar", dato);
            }
        } catch (Exception e) {
            System.out.println("noguardo pilas" + e.toString());
            dato.add(0);
            respuesta = ConstruyeRespuesta.construyeRespuestaFalla("Error al insertar", dato);
        }

        return respuesta;
    }

    //Opcional RedesSociales
    //@AnotacionVariabilidad (featureName = FeatureName.RedesSociales)
    @POST
    @Path("/registrarUsuarioTwitter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public RespuestaJson registrarUsuarioTwitter(RegistrarUsuarioTwitterJson usuarioTwitterJson) {
        RespuestaJson respuesta = new RespuestaJson();
        ArrayList<Integer> dato = new ArrayList<Integer>();
        try {
            int idUusario = usuarioDAOLocal.registrarUsuarioTwitter(usuarioTwitterJson.getNumeroIdentificacion(), usuarioTwitterJson.getTipoIdentificacion(),
                    usuarioTwitterJson.getTipoPerfil(), usuarioTwitterJson.getGenero(), usuarioTwitterJson.getNombres(), usuarioTwitterJson.getApellidos(),
                    usuarioTwitterJson.getFoto(), usuarioTwitterJson.getCorreo(), usuarioTwitterJson.getFechaNacimiento(), usuarioTwitterJson.getDireccionCasa(),
                    usuarioTwitterJson.getDireccionTrabajo(), usuarioTwitterJson.getTelefonoFijo(),
                    usuarioTwitterJson.getTelefonoMovil(), usuarioTwitterJson.getFacebookUser(),
                    usuarioTwitterJson.getTwitterUser(), usuarioTwitterJson.getTwitterToken(), usuarioTwitterJson.getUsuario(), usuarioTwitterJson.getContrasenia());
            dato.add(idUusario);
            new ArrayList().add(idUusario);
            if (idUusario != 0) {
                respuesta = ConstruyeRespuesta.construyeRespuestaConDatos(0, "info", "Usuario Registrado con Lgin de twitter Correctamente", dato);
            } else {
                respuesta = ConstruyeRespuesta.construyeRespuestaFalla("Error al insertar", dato);
            }
        } catch (Exception e) {
            System.out.println("noguardo");
            dato.add(0);
            respuesta = ConstruyeRespuesta.construyeRespuestaFalla("Error al insertar", dato);
        }

        return respuesta;
    }

    //variabilidad Completo
    @GET
    //@Path("/variabilidad/{name")
    @Path("/variabilidad")
    @Produces(MediaType.APPLICATION_JSON)
    public void variabilidadRedSocial() {
        System.out.println("Testing...");
        /*
        
        Class<GestionUsuario> obj = GestionUsuario.class;
        for (Method method: obj.getDeclaredMethods()){
            if (method.isAnnotationPresent(AnotacionVariabilidad.class)){
                Annotation annotation = method.getAnnotation(AnotacionVariabilidad.class);
                    AnotacionVariabilidad anotacionVariabilidad= (AnotacionVariabilidad)annotation;
                    System.out.print("Anotacion sobre el metodo "+ method.getName()+" label anotacion "+ anotacionVariabilidad.featureName()
                    +"valor de ese label de anotacion "+LectorProperties.getMensajeFeatureProperties(anotacionVariabilidad.featureName().toString()));
                      
            }
        }*/
        
    }


}
