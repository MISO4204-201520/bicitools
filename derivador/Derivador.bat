java -jar Derivador.jar
start preproceso.bat
start compilaCommon.bat
timeout 10
start preprocesador.bat
timeout 5
start compilacion.bat
timeout 70
copiar.bat