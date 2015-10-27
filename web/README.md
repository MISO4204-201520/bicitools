# Bicitools-Web

**Tabla de contenido**
* [Instalación de Dependencias](#instalación-de-dependencias)
* [Descargando el proyecto](#descargando-el-proyecto)
* [Subir al servidor](#subir-al-servidor)
* [Instalación de Paquetes](#instalación-de-paquetes)
* [Recomendaciones](#recomendaciones)

## Instalación de Dependencias

### Node.js
* Descargar e instalar [Node.js](https://nodejs.org/en/).

### Bower
*(Requiere tener instalado Node.js)*

Abrir la consola y ejecutar el siguiente comando:
```bash
$ npm install -g bower
```
### Gulp
*(Requiere tener instalado Node.js)*

Abrir la consola y ejecutar el siguiente comando:
```bash
$ npm install -g gulp
```

## Descargando el proyecto
Una vez instalado Node.js, Bower y Gulp proceda con estos pasos:

1. Abrir la consola y navegar hasta la ruta donde desea clonar el repositorio
  
  ```bash
  $ cd <rutaDeseada>
  ```
2. Clonarlo
  
  ```bash
  $ git clone https://github.com/Levaneng/Bicitools-Web.git
  ```
3. Ir a la carpeta descargada

  ```bash
  $ cd Bicitools-Web/
  ```
4. Instalar los paquetes de Node.js
  
  ```bash
  $ npm install
  ```
5. Instalar los paquetes de Bower
  
  ```bash
  $ bower install
  ```
  
6. Compilar el proyecto con Gulp
  ```bash
  $ gulp
  ```

## Subir al servidor
Para subir el sitio al servidor, se debe correr el siguiente comando:
```bash
$ node app.js
```
Para detenerlo, se hace con la combinación de teclas `Ctrl + C`


## Instalación de Paquetes

### Node.js
Todos los paquetes deben tener la isntrucción `--save-dev`, con el fin de agregar de forma automática la referencia al archivo `packages.json`:
```bash
$ npm install --save-dev <paquete>
```
### Bower
Todos los paquetes deben tener la isntrucción `--save-dev`, con el fin de agregar de forma automática la referencia al archivo `bower.json`:
```bash
$ bower install --save-dev <paquete> 
```

## Recomendaciones
* Las hojas de estilo solamente se editan desde la carpeta `src/sass`, los arhivos css se generan automáticamente mediante el watcher de gulp.
* Si al ejecutar `$ git add <archivos>` les aparece este mensaje:

> warning: LF will be replaced by CRLF in bower.json.
> The file will have its original line endings in your working directory.

No es nada de qué preocuparse. Pueden ignorar la advertencia con el siguiente comando
  ```bash
  $ git config core.autocrlf false
  ```


## Util

http://157.253.214.72:8080/


Usuario:      bicitoolsgu
Recorridos:   bicitoolsre
Domicilios:   bicitoolsdo
Comunicación: bicitoolsco




"1,2,3"
["1","2","3"]
public String arrayToString(string[] arr)
{
  String dias = arr[0];
  for(int i = 1; i < arr.length; i++)
    dias += "," + arr[i];
  return dias; 
}