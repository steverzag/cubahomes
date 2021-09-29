$(function() {
	initPage()
})

const initPage = function() {

	initEvents()
}

const initEvents = function() {
    $('.btn-form').click(function(e){
        if(!isItErrors())
		submitUnderPassword($(this).parent())
    })
	$('input:password').bind('click focus keypress', function(){
			$(this).removeClass('form-error')
			$(' + p', this).text('')
	})
}

const isItErrors = function() {
    var b = false
    let pass = $('#password').val()
	let pass2 = $('#password-repeat').val()
    if(actualPasswordEmpty()){
        b = true
        fieldError('#actual-password', '')
    }
    if ($('#password').val().length < 4) {
		b = true
		fieldError('#password', 'La contrasena debe tener no menos de 4 caracteres')
	} else if (pass != pass2) {
		b = true
		fieldError('#password-repeat', 'Las contrasenas no coinciden')
	}
    return b
}

actualPasswordEmpty = function() {
    return $('#actual-password').val().length == 0
}
const fieldError = function(fieldId, errorMessage) {

	$(fieldId).addClass('form-error')
	$(fieldId + ' + p').text(errorMessage)
}

const submitUnderPassword = function(form) {

	var jsonPass = $('#actual-password').val()
	var username = $('#username').val()
	
	$.ajax({
		type: "POST",
		url: `http://localhost:8080/admin/api/check/credentials/${username}`,
		headers: {
			"Content-Type": "application/json"
		},
		data: jsonPass,
		success: function(b) {
			if (b) {
				form.submit()
			} else {
				fieldError('#actual-password', '')
			}
		},
		error: function() {
			alert("Error: La Tarea no se pudo completar")
		}
	})
}