
$(function () {
    username = $('#username').val()
    length = $('tbody tr').length
    initPage()
})

const initPage = function () {
    $select = $('#select-municipie')
    $(function () {

        $.getJSON('http://localhost:8080/api/municipies', function (data) {
            $.each(data, function (key, datum) {
                $select.append($('<option>', {
                    text: datum.nombre,
                    value: datum.idMunicipio
                }))
            })
        })
        $("#select-municipie").select2()
    })

    $('#btn-add').click(function () {
        var mVal = $select.val()
        var mText = $(`option[value=${mVal}]`).text()
        i = $(`input:hidden[value=${mVal}]`).length
        if (i == 0)
            addMunicipieRow(mVal, mText)
    })

    $('.btn-del').click(function () {
        $tr = $(this).parent('div').parent('td').parent('tr')
        $tr.remove()
    })
}

const addMunicipieRow = function (opVal, mText) {
    var $tr = $('<tr>', {
        field: opVal
    })
    var $td1 = $('<td>', { text: mText })
    var $td2 = $('<td>')
    var $div = $('<div>')
    var $a = $('<a>', {
        href: `/admin/${username}/locations/municipies/${opVal}`,
        text: 'Ver',
        class: 'btn btn-primary'
    })
    var $btn = $('<button>', {
        type: 'button',
        text: 'Quitar',
        class: 'btn btn-warning mr-4'
    })
    var $hiddenVal = $('<input>', {
        type: 'hidden',
        id: `municipios${length}.idMunicipio`,
        name: `municipios[${length}].idMunicipio`,
        value: opVal
    })
    var $hiddenName = $('<input>', {
        type: 'hidden',
        id: `municipios${length}.nombre`,
        name: `municipios[${length}].nombre`,
        value: mText
    })
    length++
    $div.append($hiddenVal)
    $div.append($hiddenName)
    $div.append($btn)
    $div.append($a)
    $td2.append($div)
    $tr.append($td1)
    $tr.append($td2)
    $('#table-body').append($tr)
}