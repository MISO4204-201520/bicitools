ProyectoBicicletas : Recorridos GestionUsuario Comunicacion Reportes [ConfiguradorBicicletas] FrontEnd Domicilios :: _ProyectoBicicletas ;

Recorridos : Sitios [Alquileres] MejorRuta Mapas Desplazamiento Metricas HistorialViajes :: _Recorridos ;

Mapas : Posicion :: _Mapas ;

Desplazamiento : Individual [Grupal] :: _Desplazamiento ;

GestionUsuario : Seguridad ManejoPerfiles RedesSociales* :: _GestionUsuario ;

RedesSociales : Twitter
	| Facebook ;

Comunicacion : [CompartirRedesSociales] GestionAmigos Mensaje [Notificaciones] :: _Comunicacion ;

Reportes : ReporteMetricas ReporteRutas ReporteHistorialViajes :: _Reportes ;

FrontEnd : Movil Web :: _FrontEnd ;

%%

Grupal implies CompartirRedesSociales ;
Notificaciones implies Alquileres ;
Alquileres implies Notificaciones ;
RedesSociales implies CompartirRedesSociales ;
CompartirRedesSociales implies RedesSociales ;
RedesSociales implies Notificaciones ;
Alquileres implies ConfiguradorBicicletas ;
ConfiguradorBicicletas implies Notificaciones ;

