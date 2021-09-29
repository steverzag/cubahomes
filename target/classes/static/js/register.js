$(document).ready(() => {
    
    $('#submit').submit((e) => {

        e.preventDefault()
        var pass = $('#password').val()
        var pass2 = $('#password-repeat').val()
        console.log('password 1: ' + pass)
        console.log('password 2: ' + pass2)
        console.log(pass == pass2)

    })
    $('#button').click((e) => {

        e.preventDefault()
        var pass = $('#password').val()
        var pass2 = $('#password-repeat').val()
        console.log('password 1: ' + pass)
        console.log('password 2: ' + pass2)
        console.log(pass == pass2)

    })
})
