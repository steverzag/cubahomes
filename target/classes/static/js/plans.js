$(function(){
    initForm()
    initTable()
    initModal()
})

const initForm = function(){
    $('form').submit(function (e) {
        e.preventDefault()
        savePlan(formPlan())
        $('#add-form input').val('')
    })
    
}

const formPlan = function(){
    var plan = {
        idPlan: 0,
        nombre: $('#nombre').val(),
        meses: $('#meses').val(),
        semanas: $('#semanas').val(),
        precio: $('#precio').val()
    }
    return plan
}
const savePlan = function(plan){

    var jsonPlan = JSON.stringify(plan)
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/admin/api/plans",
        headers: {
            "Content-Type": "application/json" 
        },
        data: jsonPlan,
        success: function() {
            initTable()
        },
        error: function() {
            alert("Error: La Tarea no se pudo completar")
        }
    })
}

const initTable = function(){
    $('#plans-table tbody').empty()
    $.getJSON("http://localhost:8080/user/api/plans", function(data) {
        $.each(data, function(key, plan){
            addTableRow(plan)
            console.log('aded plan')
            $(`button[value="${plan.idPlan}"]`).data('plan', plan)
        })
    })
}

const addTableRow = function(plan){
    var tr = $('<tr></tr>')
    var tdNombre = $(`<td>${plan.nombre}</td>`)
    var tdMeses = $(`<td>${plan.meses}</td>`)
    var tdSemanas = $(`<td>${plan.semanas}</td>`)
    var tdPrecio = $(`<td>${plan.precio}</td>`)
    var tdEditar = $(`<td></td>`)
    var btn = $(`<button class="btn btn-warning" value="${plan.idPlan}" data-toggle="modal" data-target="#editPlanModal">Editar</td>`)
    
    tr.append(tdNombre)
    tr.append(tdMeses)
    tr.append(tdSemanas)
    tr.append(tdPrecio)
    tr.append(tdEditar)
    tdEditar.append(btn)
    
    $('#plans-table tbody').append(tr)
}

const initModal = function(){

    var modal = $('#editPlanModal')
    modal.on('show.bs.modal', function(event){
        var button = $(event.relatedTarget)
        var plan = button.data('plan')
        setModalPlan(plan)
    })
    modal.find('#btn-editar-guardar').click(function(){
        savePlan(modalPlan())
        $('#btn-editar-delete').click()
    })
    modal.find('#btn-editar-eliminar').click(function(){
        deletePlan(modalPlan())
        $('#btn-editar-delete').click()
    })
}
const setModalPlan = function(plan){
    var modal = $('#editPlanModal')

    modal.find('#editIdPlan').val(plan.idPlan)
    modal.find('#editNombre').val(plan.nombre)
    modal.find('#editMeses').val(plan.meses)
    modal.find('#editSemanas').val(plan.semanas)
    modal.find('#editPrecio').val(plan.precio)
}

 const modalPlan = function(){
    var modal = $('#editPlanModal')
    var plan = {}
    plan.idPlan = modal.find('#editIdPlan').val()
    plan.nombre = modal.find('#editNombre').val()
    plan.meses = modal.find('#editMeses').val()
    plan.semanas = modal.find('#editSemanas').val()
    plan.precio = modal.find('#editPrecio').val()

    return plan;
}

const deletePlan = function(plan){
    
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/admin/api/plans/' + plan.idPlan,
        success: function(){
            
            initTable()
        },
        error: function(){console.log('error')}
    })
}
