CKEDITOR.editorConfig = function(config) {
	// 이미지 삽입 버튼을 툴바에 추가
	config.toolbarGroups = [
		{ name: 'document', groups: ['mode', 'document', 'doctools'] },
		{ name: 'clipboard', groups: ['clipboard', 'undo'] },
		{ name: 'editing', groups: ['find', 'selection', 'spellchecker', 'editing'] },
		{ name: 'forms', groups: ['forms'] },
		'/',
		{ name: 'basicstyles', groups: ['basicstyles', 'cleanup'] },
		{ name: 'paragraph', groups: ['list', 'indent', 'blocks', 'align', 'bidi', 'paragraph'] },
		{ name: 'links', groups: ['links'] },
		{ name: 'insert', groups: ['insert'] },
		'/',
		{ name: 'styles', groups: ['styles'] },
		{ name: 'colors', groups: ['colors'] },
		{ name: 'tools', groups: ['tools'] },
		{ name: 'others', groups: ['others'] },
		{ name: 'about', groups: ['about'] }
	];

 // 이미지 삽입 버튼을 툴바에 추가
    config.removeButtons = 'Subscript,Superscript,Outdent,Indent,Blockquote,About';
    config.extraPlugins += (config.extraPlugins ? ',' : '') + 'image,multiimage';

    // 이미지 업로드를 위한 서버 URL 설정
    config.filebrowserUploadUrl = '/upload';

    // 툴바에 다중 이미지 선택 버튼 추가
    config.toolbarGroups.push({ name: 'insert', groups: ['multiimage'] });
}

CKEDITOR.plugins.add('multiimage', {
    icons: 'multiimage',
    init: function(editor) {
        editor.addCommand('multiimage', {
            exec: function(editor) {
                // 여기에 이미지 다중 선택 로직을 추가하세요.
                alert('이미지 다중 선택 기능을 추가하세요.');
            }
        });

        editor.ui.addButton('MultiImage', {
            label: '다중 이미지 선택',
            command: 'multiimage',
            toolbar: 'insert',
            icon: this.path + 'icons/multiimage.png'
        });
    }
});
