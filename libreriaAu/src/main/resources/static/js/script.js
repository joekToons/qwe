$(document).ready(function () {
    listar();
    

});

function listar() {
    
    $.ajax({
        url: "/autores/all",
        type: 'GET',
        success: function (x) {
            $("#tablita tbody tr").remove();
            for (var i = 0; i < x.length; i++) {
                $("#tablita").append(
                        "<tr><td>" + (i + 1) + "</td><td>" + x[i].idautor + "</td><td>" + x[i].nombre + "</td><td>" + x[i].apellidos + "</td><td>" + x[i].correo  
                        + "</td><td><a href='#' onclick='editar("
                        + x[i].idautor + ")'><i class='fa-solid fa-pen-to-square yelow'></i></a></td><td><a href='#' onclick='eliminar(" + x[i].idautor + ")'><i class='fa-solid fa-trash-can red'></i></a></td></tr>");
            }
        }
    });
}
function editar(id) {
    $.ajax({
        url: "/autores/" + id,
        type: 'GET',
        success: function (w) {
            $("#editar_nombre").val(w.nombre);
            $("#editar_apellidos").val(w.apellidos);
            $("#editar_correo").val(w.correo);
            
            
            $("#idesc").val(w.idautor);
        }
    });
    $("#modalEditar").modal('show');
}
function eliminar(id) {

    bootbox.confirm({
        message: "Realmente desea Eliminar?",
        buttons: {
            confirm: {
                label: 'SI',
                className: 'btn-success'
            },
            cancel: {
                label: 'NO',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: "/autores/" + id,
                    type: 'DELETE',
                    success: function (w) {
                        bootbox.alert({
                            message: "Registro eliminado correctamente...!",
                            callback: function () {
                                console.log('This was logged in the callback!');
                            }
                        });
                        listar();
                    }
                });
            } else {
                bootbox.alert({
                    message: "Registro no eliminado!",
                    size: 'small'
                });
            }
        }
    });
}
$("#guardar").click(function () {
    var nombre = $("#nombre").val();
    var apellidos = $("#apellidos").val();
    var correo = $("#correo").val();
    
    $.ajax({
        url: "/autores/add",
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({'nombre': nombre,'apellidos': apellidos, 'correo': correo }),
        cache: false,
        success: function (w) {
            bootbox.alert({
                message: "Registro guardado correctamente...!",
                callback: function () {
                    console.log('This was logged in the callback!');
                }
            });
            limpiar();
            listar();
        }
    });
    $("#modalGuardar").modal('hide');
});
function limpiar() {
    $("#nombre").val("");
    $("#apellidos").val("");
    $("#correo").val("");
    
}
$("#modificar").click(function () {
    var nombre = $("#editar_nombre").val();
    var apellidos = $("#editar_apellidos").val(); 
    var correo = $("#editar_correo").val(); 
    var id = $("#idesc").val();
    
    bootbox.confirm({
        message: "Realmente desea Modificar?",
        buttons: {
            confirm: {
                label: 'SI',
                className: 'btn-success'
            },
            cancel: {
                label: 'NO',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: "/autores/edit",
                    type: 'PUT',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({'idautor': id, 'nombre': nombre,'apellidos': apellidos, 'correo': correo}),
                    cache: false,
                    success: function (w) {
                        bootbox.alert({
                            message: "Registro Modificado correctamente...!",
                            callback: function () {
                                console.log('This was logged in the callback!');
                            }
                        });
                        limpiar();
                        listar();
                    }
                });
                $("#modalEditar").modal('hide');
            } else {
                bootbox.alert({
                    message: "Registro no Modificado!",
                    size: 'small'
                });
            }
        }
    });
});