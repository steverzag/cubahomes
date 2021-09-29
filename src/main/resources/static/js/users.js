$(function() {
	initTable()
})
const initTable = function() {
	initDate()
	initColAnuncios()


}

const initDate = function() {

	$('.date').each(function() {
		var date = new Date($(this).data('date'))
		$(this).text($(this).text() + date.getDate() + '/' + date.getMonth() + '/' + date.getFullYear())
	})
}

const initColAnuncios = function() {
	var anuncios = 0;
	$('.anuncios').each(function() {
		var number = parseInt($(this).text())
		anuncios += number
	})
	$('#total-anounces').text('Total Anuncios: ' + anuncios)
}