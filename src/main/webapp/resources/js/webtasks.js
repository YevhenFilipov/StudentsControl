function deleteStudents() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Пожалуйста, выберете студентов");
        return;
    }
    var ids = "";
    for (var i = 0; i < items.length; i++) {
        ids += $(items[i]).attr("id");
        if (i < items.length - 1) {
            ids += "|";
        }
    }
    var form = '<form id="deleteStudentForm" action="'
        + context
        + '/admin/studentsList.php" method="post"><input type="hidden" name="ids" /></form>';
    $("body").append(form);
    $('#deleteStudentForm input').val(ids);
    $('#deleteStudentForm').submit();

}

function progressStudents() {

    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Пожалуйста, выберите студента");
        return;
    }
    if (items.length > 1) {
        alert("Нужно выбрать не более одного студента");
        return;
    }
    var id = $(items).attr("id");
    var form = '<form id="progressStudentsForm" action="'
        + context + mapping
        + '/studentProgress.php" method="get"><input type="hidden" name="id" /></form>';
    $("body").append(form);
    $('#progressStudentsForm input').val(id);
    $('#progressStudentsForm').submit();
}

function modifyingStudent() {
    var item = $("input[type=checkbox]:checked");
    if (item.length == 0) {
        alert("Пожалуйста, выберите студента");
        return;
    }
    if (item.length > 1) {
        alert("Нужно выбрать не более одного студента");
        return;
    }
    var id = $(item).attr("id");
    var form = '<form id="modifyingStudentForm" action="'
        + context
        + '/admin/studentModifying.php" method="get"><input type="hidden" name="id" /></form>';
    $("body").append(form);
    $('#modifyingStudentForm input').val(id);
    $('#modifyingStudentForm').submit();

}

function deleteDiscipline() {
    var items = $("input[type=checkbox]:checked");
    if (items.length == 0) {
        alert("Пожалуйста, выберите дисциплины");
        return;
    }

    var ids = "";
    for (var i = 0; i < items.length; i++) {
        ids += $(items[i]).attr("id");
        if (i < items.length - 1) {
            ids += "|";
        }
    }
    var form = '<form id="deleteDisciplineForm" action="'
        + context
        + '/admin/disciplinesList.php" method="post"><input type="hidden" name="ids" /></form>';
    $("body").append(form);
    $('#deleteDisciplineForm input').val(ids);
    $('#deleteDisciplineForm').submit();
}

function modifyingDiscipline() {
    var item = $("input[type=checkbox]:checked");
    if (item.length == 0) {
        alert("Пожалуйста, выберете дисциплину");
        return;
    }
    if (item.length > 1) {
        alert("Нужно выбрать не более одной дисциплины");
        return;
    }

    var id = $(item).attr("id");
    var form = '<form id="modifyingDisciplineForm" action="'
        + context
        + '/admin/disciplinesModifying.php" method="get"><input type="hidden" name="id" /></form>';
    $("body").append(form);
    $('#modifyingDisciplineForm input').val(id);
    $('#modifyingDisciplineForm').submit();
}

function modifyTerm() {
    var selectElement = document.getElementById('term_list_select');
    var id;
    if (selectElement.selectedIndex != -1) {
        id = selectElement.options[selectElement.selectedIndex].value;
    }

    var form = '<form id="termModifyingForm" action="'
        + context
        + '/admin/termModifying.php" method="get"><input type="hidden" name="id" /></form>';
    $("body").append(form);
    $('#termModifyingForm input').val(id);
    $('#termModifyingForm').submit();

}

function deleteTerm() {

    var selectElement = document.getElementById('term_list_select');
    var id;
    if (selectElement.selectedIndex != -1) {
        id = selectElement.options[selectElement.selectedIndex].value;
    }
    var form = '<form id="termDeleteForm" action="'
        + context
        + '/admin/termsList.php" method="post"><input type="hidden" name="id_term_to_delete" /></form>';
    $("body").append(form);
    $('#termDeleteForm input').val(id);
    $('#termDeleteForm').submit();
}

function verifyNewStudentForm() {
    var firstName = document.getElementById('first_name').value;
    if (isInputEmpty(firstName, '\"Фамилия\"')) {
        return;
    }
    if (!testUserNameInputs(firstName, '\"Фамилия\"')) {
        return;
    }

    var name = document.getElementById('name').value;
    if (isInputEmpty(name, '\"Имя\"')) {
        return;
    }
    if (!testUserNameInputs(name, '\"Имя\"')) {
        return;
    }

    var group = document.getElementById('group').value;
    if (isInputEmpty(group, '\"Группа\"')) {
        return;
    }

    var date = document.getElementById('datepicker').value;
    if (isInputEmpty(date, '\"Дата поступления\"')) {
        return;
    }
    var currentDate = new Date();

    var arr = date.split('.');
    var userDate = new Date(arr[2], arr[1] - 1, arr[0]);

    if (userDate.getTime() < 0) {
        alert('Даты до 1-го января 1970 года не поддерживаются');
        return;
    }
    if (userDate > currentDate) {
        alert('Дата поступления не может быть больше текущей даты');
        return;
    }

    document.getElementById('studentForm').submit();
}

function verifyDisciplineForm() {
    var name = document.getElementById('discipline_name').value;
    if (name.length == 0) {
        alert("Поле \"Название\" не заполнено");
        return;
    }
    document.getElementById('disciplineForm').submit();
}

function verifyTermForm() {
    var termDuration = document.getElementById('term_duration').value;
    if (isNaN(termDuration) || termDuration <= 0 || termDuration > 480 || (termDuration % 1) != 0) {
        alert("Поле \"Длительнсть в неделях\" заполнено некорректно. \n " +
            "Длительность должна быть целым числом больше 0 и меньше 480 (т.е. меньше 10 лет)");
        return;
    }
    var disciplinesSelect = document.getElementById('disciplines_list');
    if (disciplinesSelect.selectedIndex == -1) {
        alert("Выберите дисциплины");
        return;
    }
    var items = new Array;
    for (var i = 0; i < disciplinesSelect.options.length; i++) {
        if (disciplinesSelect.options[i].selected) {
            items.push(disciplinesSelect.options[i].value)
        }
    }
    var ids = "";
    for (var j = 0; j < items.length; j++) {
        ids += items[j];
        if (j < items.length - 1) {
            ids += "|";
        }
    }
    document.getElementById('id_disciplines').value = ids;
    document.getElementById('termForm').submit();
}

function goToChangeMarks() {
    var idStudent = document.getElementById('id_student').value;
    var select = document.getElementById('opening_list');
    var idTerm = select.options[select.selectedIndex].value;
    var form = '<form id="chooseStudentTermMarksForm" action="'
        + context
        + '/admin/ModifyStudentProgress.php" method="get">' +
        '<input type="hidden" name="id_student" value="' + idStudent + '">' +
        '<input type="hidden" name="id_term" value="' + idTerm + '">' +
        '</form>';
    $("body").append(form);
    $('#chooseStudentTermMarksForm').submit();
}

function verifyStudentProgressForm() {
    var markInputs = document.getElementsByName('mark');
    var disciplineInputs = document.getElementsByName('disciplineId');
    var disciplineMarks = "";
    for (var i = 0; i < markInputs.length; i++) {
        var markString = markInputs[i].value;
        var intValue = parseFloat(markString);

        if (isNaN(intValue)) {
            alert('Оценка введена в неподдерживаемом формате.' +
                '\n' +
                'Значение оценки должно быть целым числом от 0 до 5');
            return;
        }

        if (intValue < 0 || intValue > 5) {
            alert('Значение оценки должно быть целым числом от 0 до 5');
            return;
        }
        if ((intValue % 1) != 0) {
            alert('Значение оценки должно быть целым числом от 0 до 5');
            return;
        }

        disciplineMarks += disciplineInputs[i].value;
        disciplineMarks += "|";
        disciplineMarks += markString;
        if (i < markInputs.length - 1) {
            disciplineMarks += "|";
        }
    }

    var respParam = parseQueryString(window.location.search),
        idCurrTerm = respParam['id_term'];
    var form = '<form id="ModifyProgressStudentsForm" action="'
        + context + mapping
        + '/ModifyStudentProgress.php" method="post">' +
        '<input type="hidden" name="discipline_marks" value="' + disciplineMarks + '">' +
        '<input type="hidden" name="id_student" value="' + document.getElementById('id_student').value + '">' +
        '<input type="hidden" name="id_term" value="' + idCurrTerm + '">' +
        '</form>';
    $("body").append(form);
    $('#ModifyProgressStudentsForm').submit();
}

var parseQueryString = function (strQuery) {
    var strSearch = strQuery.substr(1),
        strPattern = /([^=]+)=([^&]+)&?/ig,
        arrMatch = strPattern.exec(strSearch),
        objRes = {};
    while (arrMatch != null) {
        objRes[arrMatch[1]] = arrMatch[2];
        arrMatch = strPattern.exec(strSearch);
    }
    return objRes;
};

function testUserNameInputs(string, stringName) {
    if (/[!-,./:-@[-`{-~\d\s]/.test(string)) {
        alert('Поле ' + stringName + ' заполнено неверно. Допустимо применять буквы без пробелов и символ "-"');
        return false;
    } else {
        return true;
    }
}

function isInputEmpty(string, stringName) {
    if (string.length == 0) {
        alert('Поле ' + stringName + ' не заполнено');
        return true;
    } else {
        return false;
    }
}

