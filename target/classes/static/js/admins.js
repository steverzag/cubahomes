$(function(){
    initDesde()
})

const initDesde = function(){
    
    $('td.desde').each(function(){
        var date = new Date($(this).data('desde'))
        $(this).text(date.getDate() + '/' + date.getMonth() + '/' + date.getFullYear())
    })
}