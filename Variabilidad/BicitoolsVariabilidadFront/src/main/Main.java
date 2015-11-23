package main;

import java.io.*;

import org.json.*;

public class Main {
	
	private File file;
	
	private Main(String path){
		file = new File(path);
	}
	
	private String generateContent(String featuresString){
		//featuresString = "ProyectoBicicletas,Recorridos,Sitios,Alquileres,MejorRuta,Mapas,Posicion,Desplazamiento,Individual,Grupal,Metricas,HistorialViajes,GestionUsuario,Seguridad,ManejoPerfiles,RedesSociales,Twitter,Facebook,Comunicacion,CompartirRedesSociales,GestionAmigos,Mensaje,Notificaciones,Reportes,ReporteMetricas,ReporteRutas,ReporteHistorialViajes,ConfiguradorBicicletas,FrontEnd,Movil,Web,Domicilios";
		String[] features = featuresString.split(",");
		JSONArray jsonArray = new JSONArray(features);
		return generateJavaScriptContent(jsonArray.toString());
	}
	
	private String generateJavaScriptContent(String content){
		return "var variabilidad = " + content + ";";
	}
	
	private void generateFile(String content) throws IOException{
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();		
	}
	
	private String getFullPath(){
		return file.getAbsoluteFile().getPath();
	}
	
	public static void main(String[] args) {
		try {
			Main main = new Main("../web/app/VariabilidadFront.js");
			String featuresString = (args.length > 0) ? args[0] : "";
			String content = main.generateContent(featuresString);
			main.generateFile(content);
			System.out.println("Archivo generado en la ruta: " + main.getFullPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}