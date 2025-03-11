import ace from 'ace-builds/src-noconflict/ace'; // 
import 'ace-builds/src-noconflict/mode-python'; // 사용할 언어 모드
import 'ace-builds/src-noconflict/theme-monokai'; // 사용할 테마
import 'ace-builds/src-noconflict/ext-language_tools';
//  에이스 에디터 설정 

var Range = ace.require('ace/range').Range;

export function setupAceEditor(editorId: string) {
    const editor = ace.edit(editorId);
    editor.setTheme('ace/theme/monokai');
    editor.session.setMode('ace/mode/python');
    editor.setFontSize("14px");
    editor.setHighlightActiveLine(true);
    editor.setOptions({
        enableBasicAutocompletion: true,
        behavioursEnabled: true,
        enableLiveAutocompletion: true,
        wrap: true,
        hasCssTransforms: true,
        useworker: true,
        printMargin: false,
    });

    // 배경색과 거터 스타일 설정
    const gutter = editor.container.querySelector('.ace_gutter');
    if (gutter) {
        (gutter as HTMLElement).style.backgroundColor = '#1E273B';
        (gutter as HTMLElement).style.borderRight = 'none';
    }
    
    editor.container.style.backgroundColor = '#1E273B';
    
    const langTools = ace.require("ace/ext/language_tools");

    return editor;
}

