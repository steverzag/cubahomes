$(() => {

	initBtnsDelete()
})

const initBtnsDelete = () => {
	
	$('.btn-danger').each(function() {
		
		$(this).click(function() {

			$.ajax({
                type: 'DELETE',
                url: 'http://localhost:8080/user/api/anounces/' + $(this).val(),
                success: console.log('ok'),
                error: console.log('error')
            })
		})

	})
}