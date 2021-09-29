var tags = [];



$(() => {

	initPage()

})

const initPage = function () {
	initSelectProvincia()
	initFile()
	initTags()
}

const initTags = function () {
	
	$.getJSON("http://localhost:8080/api/tags/", function (data) {

		municipies = new Bloodhound({
			datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
			queryTokenizer: Bloodhound.tokenizers.whitespace,
			local: $.map(data, function (tag) {
				console.log(tag)
				return {
					value: tag.idEtiqueta,
					name: tag.nombre
				};
			})
		});
		municipies.initialize();

		$('#tagsinput').tagsinput({
			tagClass: 'badge badge-success badge-pill big mr-2 big',
			itemValue: function (item) {
				return item.value
			},
			itemText: function (item) {
				return item.name
			},
			typeaheadjs: [{
				minLength: 1,
				highlight: true,
			}, {
				minlength: 1,
				name: 'citynames',
				displayKey: 'name',
				valueKey: 'name',
				source: municipies.ttAdapter()
			}],
			focusClass: 'focus',
			freeInput: true,
			addOnBlur: true,
			maxTags: undefined,
			maxChars: undefined,
			confirmKeys: [13, 44],
			delimiter: ',',
			delimiterRegex: null,
			cancelConfirmKeysOnEmpty: false,
			onTagExists: function (item, $tag) {
				$tag.hide().fadeIn();
			},
			trimValue: false,
			allowDuplicates: false,
			triggerChange: true
		}
		)
	})	

	
}
const initFile = function () {
	$('#chooser').on('change', function () {
		var { files } = $(this)[0]
		if (files.length > 0) {
			fileLength = $('input:file').length
			addInputFile(files)
			addImages(files)
		}
	})
	$('ul.galery').on('click', '.image-delete', function () {

		let id = $(this).attr('id')
		for (let index = 1; index <= $('input[name=file]').length; index++) {
			if ($(this).hasClass(index)) {
				var input = $(`input:file#${index}`)[0]
				var dt = new DataTransfer()
				$.each(input.files, (key, value) => {
					if (value.name !== id) {
						dt.items.add(value)
					}
				})
				input.files = dt.files
			}
		}
		$(this).parent().remove()
	})
}

const addInputFile = function (files) {
	var $input = $('<input>', {
		type: 'file',
		accept: 'image/*',
		name: 'file',
		id: fileLength,
		multiple: 'multiple',
		style: 'display: none;'
	})
	$('#files').append($input)
	$(`#${fileLength}`)[0].files = files
}

const addImages = function (files) {
	for (let index = 0; index < files.length; index++) {
		$img = $('<img>', {
			src: URL.createObjectURL(files[index])
		})
		$span = $('<span>', {
			text: 'del',
			class: `${fileLength} image-delete`,
			id: files[index].name,
		})
		$li = $('<li>')
		$li.append($img)
		$li.append($span)
		$('.galery').append($li)
	}
}
const initSelectProvincia = () => {
	$provincia = $('#provincia')
	$municipio = $('#municipio')

	$provincia.change(() => {
		provincia = $provincia.val()
		fillMunicipios(provincia, $municipio)
	})
}

const fillMunicipios = (provincia, $municipio) => {
	$.getJSON("http://localhost:8080/api/municipies/" + provincia, (data) => {
		$municipio.empty()
		$municipio.append('<option>---</option>')
		$.each(data, (key, datum) => {

			$municipio.append($('<option>', {
				value: datum.idMunicipio,
				text: datum.nombre
			}))
		})
	})
}



