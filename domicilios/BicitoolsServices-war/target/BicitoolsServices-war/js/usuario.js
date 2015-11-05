/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var usuario;

function tomaUsuario() {
    var todo = location.search.substring(1);
    var param = todo.substring(0, todo.indexOf("="));
    usuario = todo.substring(todo.indexOf("=") + 1);
    document.getElementById("usuarioL").value = usuario;

}

function tomaUsuarioCabeza() {
    return location.search.substring(1);
}
function daUsuario() {

    return document.getElementById("usuarioL").value;
}





