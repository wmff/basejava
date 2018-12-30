function addOrganization(sectionType, counterIndex) {
    let containerOrgs = document.getElementById("containerOrgs" + sectionType);
    let divContOrg = document.createElement("div");
    divContOrg.id = "containerOrg" + sectionType + counterIndex;
    containerOrgs.appendChild(divContOrg);

    addOrgField("containerOrg", sectionType, counterIndex, "<b>Наименование организации</b>", "");
    addOrgField("containerOrg", sectionType, counterIndex, "URL", "URL");

    let divCont = document.createElement("div");
    divCont.id = "container" + sectionType + counterIndex;
    divContOrg.appendChild(divCont);

    let link = document.createElement("a");
    link.href = "#" + sectionType + counterIndex;
    link.setAttribute("id", sectionType + counterIndex + 'addBtn');
    link.className = "btn btn-primary mb-2";
    link.innerText = "добавить позицию";
    divContOrg.appendChild(link);

    addPosition(sectionType, counterIndex + 1, 0);
}

function addPosition(sectionType, counterIndex, counterPosIndex) {
    addPositionDateField("container", sectionType, counterIndex - 1, counterPosIndex, "Дата начала", "dateBegin");
    addPositionDateField("container", sectionType, counterIndex - 1, counterPosIndex, "Дата окончания", "dateEnd");

    let divFormCheck = document.createElement("div");

    divFormCheck.className = "form-check";
    let inputCheck = document.createElement("input");
    inputCheck.id = sectionType + (counterIndex-1) + counterPosIndex + "checkbox";
    inputCheck.className = "form-check-input";
    inputCheck.type = "checkbox";
    inputCheck.setAttribute("onclick", `showHideDateEnd('${sectionType}${counterIndex-1}${counterPosIndex}')` );
    let labelCheck = document.createElement("label");
    labelCheck.htmlFor = sectionType + (counterIndex-1) + counterPosIndex + "checkbox";
    labelCheck.innerHTML = "по настоящее время";
    let container = document.getElementById("container" + sectionType + (counterIndex - 1));
    divFormCheck.append(inputCheck);
    divFormCheck.append(labelCheck);
    container.append(divFormCheck);

    addPositionField("container", sectionType, counterIndex - 1, counterPosIndex, "Должность", "title");
    addPositionField("container", sectionType, counterIndex - 1, counterPosIndex, "Описание", "description");

    container.append(document.createElement("hr"));
    document.getElementById(sectionType + (counterIndex - 1) + 'addBtn')
        .setAttribute("onclick", `addPosition('${sectionType}', ${counterIndex}, ${counterPosIndex + 1})`);
}

function addPositionDateField(prefix, sectionType, counterIndex, counterPosIndex, textLabel, addText) {
    let divBox = document.createElement("div");
    if (addText === 'dateEnd') {
        divBox.id = sectionType + counterIndex + counterPosIndex+"boxEnd";
    }
    let container = document.getElementById(prefix + sectionType + counterIndex);
    let label = document.createElement("label");
    label.innerHTML = textLabel;
    label.htmlFor = sectionType + counterIndex + counterPosIndex + addText;
    let input = document.createElement("input");
    input.className = "form-control date";
    input.id = sectionType + counterIndex + counterPosIndex + addText;
    input.type = "text";
    input.name = sectionType + counterIndex + addText;
    input.placeholder = "MM/yyyy";
    input.setAttribute("width", "234");
    input.setAttribute("readonly", "");
    divBox.appendChild(label);
    divBox.appendChild(input);
    container.appendChild(divBox);
    datepicker(sectionType + counterIndex + counterPosIndex);
}

function addPositionField(prefix, sectionType, counterIndex, counterPosIndex, textLabel, addText) {
    let container = document.getElementById(prefix + sectionType + counterIndex);
    let labelPosition = document.createElement("label");
    labelPosition.innerHTML = textLabel;
    labelPosition.htmlFor = sectionType + counterIndex + addText;
    let inputPosition = document.createElement("input");
    inputPosition.className = "form-control";
    inputPosition.id = sectionType + counterIndex + addText;
    inputPosition.type = "text";
    inputPosition.name = sectionType + counterIndex + addText;
    container.appendChild(labelPosition);
    container.appendChild(inputPosition);
}

function addOrgField(prefix, sectionType, counterIndex, textLabel, addText) {
    let container = document.getElementById(prefix + sectionType + counterIndex);
    let labelPosition = document.createElement("label");
    labelPosition.innerHTML = textLabel;
    labelPosition.htmlFor = sectionType + addText;
    let inputPosition = document.createElement("input");
    inputPosition.className = "form-control";
    inputPosition.id = sectionType + addText;
    inputPosition.type = "text";
    inputPosition.name = sectionType + addText;
    container.appendChild(labelPosition);
    container.appendChild(inputPosition);
}

function showHideDateEnd(id) {
    let boxEnd = '#' + id + 'boxEnd';
    if ($('#' + id + 'checkbox').is(":checked")) {
        $(boxEnd).hide();
        $(boxEnd).find(".date").val("Сейчас");
    } else {
        $(boxEnd).show();
    }
}

function datepicker(id) {
    let paramsDatepicker = {
        uiLibrary: 'bootstrap4',
        weekStartDay: 1,
        locale: 'ru-ru',
        format: 'mm/yyyy'
    };
    $('#' + id + 'dateBegin')
        .datepicker(paramsDatepicker);
    $('#' + id + 'dateEnd')
        .datepicker(paramsDatepicker);
}

