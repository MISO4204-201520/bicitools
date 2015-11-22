/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bicitools.dao;

import com.bicitools.common.ConstruyeRespuesta;
import com.bicitools.entity.Ordenes;
import com.bicitools.entity.Partes;
import com.bicitools.entity.PartesGenericas;
import com.bicitools.mjson.DatosMisOrdenesJson;
import com.bicitools.mjson.DatosOrdenesJson;
import com.bicitools.mjson.DatosPartesOrdenJson;
import com.bicitools.mjson.InfoConfigMiBiciJson;
import com.bicitools.mjson.InfoOrdenJson;
import com.bicitools.mjson.InfoRolProveedorJson;
import com.bicitools.mjson.OrdenPrecioJson;
import com.bicitools.mjson.ParamConfigBici;
import com.bicitools.mjson.RespuestaJson;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhony
 */
@Stateless
public class OrdenesDAO implements OrdenesDAOLocal {

    @PersistenceContext(unitName = "com.bicitools_unit")
    EntityManager em;

    @EJB
    //public RutasFacadeImpl rutasFacade;
    ParteGenericaDAOLocal parteGenericaDAO;

    @EJB
    //public RutasFacadeImpl rutasFacade;
    PartesDAOLocal parteDAO;

    @EJB
    TipoParteDAOLocal tipoParteDAO;

    
    @Override
    public RespuestaJson obtenerDetallesOrden(InfoOrdenJson info) {

        RespuestaJson res = null;
        ArrayList datos = new ArrayList();

        Ordenes salida = null;
        Query query = em.createNamedQuery("Ordenes.findByIdOrden");

        query.setParameter("idOrden", Integer.parseInt(info.getIdOrden()));
        ArrayList qresul = (ArrayList) query.getResultList();

        
        if (qresul.size() > 0) {
                salida = (Ordenes) qresul.get(0);
                DatosOrdenesJson orden = new DatosOrdenesJson();

                switch (salida.getEstado()) {
                    case "P":
                        orden.setEstado("Pendiente");
                        break;
                    case "E":
                        orden.setEstado("Entregada");
                        break;
                    default:
                        orden.setEstado("N/A");
                        break;
                }
                
                // Create an instance of SimpleDateFormat used for formatting 
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                Date today = salida.getFechaHora();

                orden.setFechaHora(df.format(today));
                orden.setIdOrden(salida.getIdOrden().toString());
                orden.setValor(salida.getValor());

                orden.setPartes(obtenerPartesOrden(salida));
                datos.add(orden);

            res = ConstruyeRespuesta.construyeRespuestaOk();
            res.setDatos(datos);
        
        }
        else{
        res = ConstruyeRespuesta.construyeRespuestaFalla("no hay ordenes para este usuario");    
        }

        
        return res;
    }
    
    @Override
    public RespuestaJson obtenerMisOrdenes(InfoRolProveedorJson info) {

        RespuestaJson res = null;
        ArrayList datos = new ArrayList();

        Ordenes salida = null;
        Query query = em.createNamedQuery("Ordenes.findByUsuario");

        query.setParameter("usuario", info.getUsuario());
        Vector qresul = (Vector) query.getResultList();

        if (qresul.size() > 0) {
            for (int i = 0; i < qresul.size(); i++) {
                salida = (Ordenes) qresul.get(i);
                DatosMisOrdenesJson orden = new DatosMisOrdenesJson();

                switch (salida.getEstado()) {
                    case "P":
                        orden.setEstado("Pendiente");
                        break;
                    case "E":
                        orden.setEstado("Entregada");
                        break;
                    default:
                        orden.setEstado("N/A");
                        break;
                }
                
                
                // Create an instance of SimpleDateFormat used for formatting 
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                Date today = salida.getFechaHora();

                orden.setFechaHora(df.format(today));
                orden.setIdOrden(salida.getIdOrden().toString());
                orden.setValor(salida.getValor());

                //orden.setPartes(obtenerPartesOrden(salida));
                datos.add(orden);

            }
            res = ConstruyeRespuesta.construyeRespuestaOk();
            res.setDatos(datos);
        
        }
        else{
        res = ConstruyeRespuesta.construyeRespuestaFalla("no hay ordenes para este usuario");    
        }

        
        return res;
    }

    ArrayList<DatosPartesOrdenJson> obtenerPartesOrden(Ordenes orden) {

        ArrayList<DatosPartesOrdenJson> partesSalida = new ArrayList<>();
        
        int idParte, idCambio = 1;
        String nombre = "";

        
        do {
            DatosPartesOrdenJson regSalida = new DatosPartesOrdenJson();
            Partes miParte = new Partes();
            switch (idCambio) {
                case 1:
                    idParte = Integer.parseInt(orden.getIdMarco());
                    nombre = "marco";
                    break;
                case 2:
                    idParte = Integer.parseInt(orden.getIdTijera());
                    nombre = "tijera";
                    break;
                case 3:
                    idParte = Integer.parseInt(orden.getIdRuedas());
                    nombre = "ruedas";
                    break;
                case 4:
                    idParte = Integer.parseInt(orden.getIdFrenos());
                    nombre = "frenos";
                    break;
                case 5:
                    idParte = Integer.parseInt(orden.getIdManubrio());
                    nombre = "manubrio";
                    break;
                case 6:
                    idParte = Integer.parseInt(orden.getIdSillin());
                    nombre = "sillin";
                    break;
                case 7:
                    idParte = Integer.parseInt(orden.getIdCambios());
                    nombre = "cambios";
                    break;
                case 8:
                    idParte = Integer.parseInt(orden.getIdPlatoDelantero());
                    nombre = "platodelantero";
                    break;
                case 9:
                    idParte = Integer.parseInt(orden.getIdPlatoTrasero());
                    nombre = "platotrasero";
                    break;
                case 10:
                    idParte = Integer.parseInt(orden.getIdPedales());
                    nombre = "pedales";
                    break;
                case 11:
                    idParte = Integer.parseInt(orden.getIdCadena());
                    nombre = "cadena";
                    break;
                default:
                    idParte = 0;
                    break;
            }
            if (idParte != 0) {
                miParte = parteDAO.obtenerInfoParte(idParte);
            } else {
                nombre = nombre.toLowerCase();
                PartesGenericas parteGenerica = parteGenericaDAO.obtenerInfoParte(tipoParteDAO.obtenerIdTipoParte(nombre));

                miParte.setIdTipo(parteGenerica.getIdTipo());
                miParte.setIdParte(parteGenerica.getIdParte());
                miParte.setDescripcion(parteGenerica.getDescripcion());
                miParte.setNombre(parteGenerica.getNombre());
                miParte.setValor(parteGenerica.getValor());

                
            }
            
            regSalida.setIdParte(miParte.getIdParte().toString());
            regSalida.setDescripcion(miParte.getDescripcion());
            regSalida.setNombre(miParte.getNombre());
            regSalida.setValor(miParte.getValor());
            String tipoP= tipoParteDAO.obtenerNombreTipoParte(miParte.getIdTipo());
            if(tipoP != null)
            regSalida.setTipoParte(tipoP);

            partesSalida.add(regSalida);
            idCambio++;
        } while (idCambio <= 11);

        return partesSalida;
    }

    @Override
    public RespuestaJson validarOrden(InfoConfigMiBiciJson info) {

        RespuestaJson res;
        String mensaje = null;
        Partes datoParte;
        ParamConfigBici config;
        int valor = 0;
        OrdenPrecioJson datosSalida = new OrdenPrecioJson();
        ArrayList datos = new ArrayList();
        String salida = null;

        try {

            config = info.getConfig().get(0);
            salida = calcularValorOrden(config);

            //salir por excepcion
            Integer.parseInt(salida);

            res = ConstruyeRespuesta.construyeRespuestaOk();
            datosSalida.setValor(salida);

            switch (info.getConsulta()) {
                //es una consulta
                case "1":

                    datosSalida.setIdOrden("0");

                    datos.add(datosSalida);
                    res.setDatos(datos);
                    break;
                case "0":
                    Ordenes miOrden = new Ordenes();

                    miOrden.setEstado("P");
                    miOrden.setFechaHora(new Date());
                    miOrden.setIdCadena(config.getCadena());
                    miOrden.setIdCambios(config.getCambios());

                    miOrden.setIdFrenos(config.getFrenos());
                    miOrden.setIdManubrio(config.getManubrio());
                    miOrden.setIdMarco(config.getMarco());
                    miOrden.setIdPedales(config.getPedales());

                    miOrden.setIdPlatoDelantero(config.getPlatoDelantero());
                    miOrden.setIdPlatoTrasero(config.getPlatoTrasero());
                    miOrden.setIdSillin(config.getSillin());
                    miOrden.setIdTijera(config.getTijera());
                    miOrden.setIdRuedas(config.getRuedas());

                    miOrden.setUsuario(info.getUsuario());
                    miOrden.setValor(salida);

                    int idOrden = generarIdOrden();
                    miOrden.setIdOrden(idOrden);

                    datosSalida.setIdOrden(String.valueOf(idOrden));

                    datos.add(datosSalida);
                    res.setDatos(datos);

                    em.persist(miOrden);
                    break;
                default:
                    res = ConstruyeRespuesta.construyeRespuestaFalla("error en tipo de solicitud");
                    break;
            }
        } catch (Exception ex) {
            res = ConstruyeRespuesta.construyeRespuestaFalla(salida);
        }

        return res;
    }

    public Ordenes consultarOrdenPorId(int idOrden) {
        Ordenes salida = null;
        Query query = em.createNamedQuery("Ordenes.findByIdOrden");

        query.setParameter("idOrden", idOrden);
        ArrayList qresul = (ArrayList) query.getResultList();

        if (qresul.size() > 0) {
            salida = (Ordenes) qresul.get(0);
        }
        return salida;
    }

    public int generarIdOrden() {
        Ordenes orden;
        int miId = (int) (Math.random() * 999999 + 1);
        do {
            orden = consultarOrdenPorId(miId);
        } while (orden != null);

        return miId;
    }

    public String calcularValorOrden(Object pObjeto) {
        Object objeto = pObjeto;
        String res = null;
        int valor = 0;
        String mensaje = null;
        Method metodos[] = pObjeto.getClass().getMethods();
        for (int i = 0; i < metodos.length; i++) {
            Method metodo = metodos[i];
            //Si es un metodo get o is lo utilizo con su equivalente set
            if ((metodo.getName().substring(0, 3).equalsIgnoreCase("get") || metodo.getName().substring(0, 2).equalsIgnoreCase("is")) && !metodo.getName().equals("getClass")) {
                String methodNameSet = "";
                if (metodo.getName().substring(0, 3).equalsIgnoreCase("get")) {
                    methodNameSet = metodo.getName().replaceFirst("get", "set");
                } else {
                    methodNameSet = methodNameSet.replaceFirst("is", "set");
                }
                try {
                    Method metodoSet = pObjeto.getClass().getMethod(methodNameSet, metodo.getReturnType());
                    if (metodoSet != null) {
                        //llamado a get
                        if (metodo.getReturnType().equals(java.lang.String.class)) {

                            if (metodo.invoke(pObjeto, new Object[0]).equals("0")) {

                                String nombre = metodo.getName().substring(3, metodo.getName().length());
                                nombre = nombre.toLowerCase();
                                int valorParte = parteGenericaDAO.obtenerValorParte(tipoParteDAO.obtenerIdTipoParte(nombre));
                                if (valorParte != -1) {
                                    valor += valorParte;
                                } else {
                                    mensaje = "error en tipo " + nombre;
                                }
                            } else {
                                Partes datoParte;
                                datoParte = parteDAO.obtenerInfoParte(Integer.parseInt((String) metodo.invoke(pObjeto, new Object[0])));
                                if (datoParte != null) {
                                    String valorString = datoParte.getValor();
                                    valorString = valorString.replace(".", "");
                                    valorString = valorString.replace(",", "");
                                    valor += Integer.parseInt(valorString);
                                } else {
                                    mensaje = "error en tipo " + metodo.getName();
                                    System.out.println(mensaje);
                                }
                            }

                            String valort = (String) metodo.invoke(pObjeto, new Object[0]);
                            if (valort == null) {
                                //metodoSet.invoke(pObjeto, "");
                                res = "error " + metodo.getName();
                            }
                        }

                    }
                } catch (Exception e) {
                }
                if (mensaje != null) {
                    return mensaje;
                }
            }
        }
        res = String.valueOf(valor);
        return res;
    }

}
