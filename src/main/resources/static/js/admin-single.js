$(function() {
	initModal()
	initPage()
})

const initPage = function() {
	initDate()
	$('input:checkbox').click(function() {
		$('#roles-div').removeClass('form-error')
		$('#roles-div + p').text('')
	})
	$('#password').click(function() {
		$(this).removeClass('form-error')
	})
	$('#btn-save').click(function(e) {
		var checkOwner = $('#check-role-owner').prop('checked')
		var checkAdmin = $('#check-role-admin').prop('checked')
		var checkCeo = $('#check-role-ceo').prop('checked')
		var checkAdminTr = $('#check-role-admintrainee').prop('checked')
		if ((!checkAdmin && !checkOwner && !checkCeo && !checkAdminTr)) {
			
			e.preventDefault()
			fieldError('#roles-div', 'Debe seleccionar al menos un role administrativo')
			return false
		}
	})
}

const initDate = function() {

	$('.date').each(function() {
		var date = new Date($(this).data('date'))
		$(this).text($(this).text() + date.getDate() + '/' + date.getMonth() + '/' + date.getFullYear())
	})
}

const saveRoles = function() {

	$('.check-role').each(function() {
		var b = $(this).prop('checked')
		if (b === false) {
			$('input:hidden', $(this).parent()).remove()
		}
	})
}

const fieldError = function(fieldId, errorMessage) {

	$(fieldId).addClass('form-error')
	$(fieldId + ' + p').text(errorMessage)
}

const initModal = function() {

	var modal = $('#insertPasswordModal')
	modal.on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget)
		modal.data('formId', button.attr('form'))
	})
	modal.find('#btn-accept').click(function() {
		if ($('#password').val().length == 0) {
			fieldError('#password', '')
			return
		}
		submitForm()
	})
}

const submitForm = function() {

	var jsonPass = $('#password').val()
	var username = $('#username').val()
	var modal = $('#insertPasswordModal')

	$.ajax({
		type: "POST",
		url: `http://localhost:8080/admin/api/check/credentials/${username}`,
		headers: {
			"Content-Type": "application/json"
		},
		data: jsonPass,
		success: function(b) {
			if (b) {
				saveRoles()
				$('#' + modal.data('formId')).submit()
			} else {
				fieldError('#password', '')
			}
		},
		error: function() {
			alert("Error: La Tarea no se pudo completar")
		}
	})
}

