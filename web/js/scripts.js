function addOrganization(sectionType, counterIndex) {
    let containerOrgs = $("#containerOrgs" + sectionType);

    let divContainerOrg = $("<div/>", {
        id: "containerOrg" + sectionType + counterIndex,

    });

    containerOrgs.append(divContainerOrg);

    addInputField("containerOrg", sectionType, counterIndex, -1, "<b>Наименование организации</b>", "");
    addInputField("containerOrg", sectionType, counterIndex, -1, "URL", "URL");

    let divContainer = $("<div/>", {
        id: "container" + sectionType + counterIndex,
    });
    divContainerOrg.append(divContainer);


    let link = $("<a/>", {
        href: "#" + sectionType + counterIndex,
        id: sectionType + counterIndex + 'addBtn',
        "class": "btn btn-primary mb-2",
        html: "добавить позицию",
    });
    divContainer.append(link);

    addPosition(sectionType, counterIndex + 1, 0);

    document.getElementById(sectionType + (counterIndex - 1) + 'addOrgBtn')
        .setAttribute("onclick", `addOrganization('${sectionType}', ${counterIndex + 1})`);

}

function addPosition(sectionType, counterIndex, counterPosIndex) {
    addPositionDateField("container", sectionType, counterIndex - 1, counterPosIndex, "Дата начала", "dateBegin");
    addPositionDateField("container", sectionType, counterIndex - 1, counterPosIndex, "Дата окончания", "dateEnd");

    let divFormCheck = $("<div/>", {
        "class": "form-check",
    });
    let inputCheck = $("<input/>", {
        id: sectionType + (counterIndex - 1) + counterPosIndex + "checkbox",
        "class": "form-check-input",
        type: 'checkbox',
        onclick: `showHideDateEnd('${sectionType}${counterIndex - 1}${counterPosIndex}')`,

    });
    let labelCheck = $("<label/>" , {
        htmlFor: sectionType + (counterIndex - 1) + counterPosIndex + "checkbox",
        html: "по настоящее время",
    });
    let container = $("#container" + sectionType + (counterIndex - 1));

    divFormCheck.append(inputCheck);
    divFormCheck.append(labelCheck);
    container.append(divFormCheck);

    addInputField("container", sectionType, counterIndex - 1, counterPosIndex, "Должность", "title");
    addInputField("container", sectionType, counterIndex - 1, counterPosIndex, "Описание", "description");

    $("<hr/>").appendTo(container);

    document.getElementById(sectionType + (counterIndex - 1) + 'addBtn')
        .setAttribute("onclick", `addPosition('${sectionType}', ${counterIndex}, ${counterPosIndex + 1})`);
}

function addPositionDateField(prefix, sectionType, counterIndex, counterPosIndex, textLabel, addText) {
    let container = '#' + prefix + sectionType + counterIndex;
    let id = sectionType + counterIndex + counterPosIndex;

    let divBox = addText === 'dateEnd' ? $("<div/>", {
        id: id + "boxEnd",
    }) : $("<div/>", {
        id: id + "boxBegin",
    });

    divBox.append(createLabel(sectionType + counterIndex + counterPosIndex + addText, textLabel));
    divBox.append(createInputDate(sectionType + counterIndex + counterPosIndex + addText, sectionType + counterIndex + addText));
    $(container).append(divBox);

    datepicker(sectionType + counterIndex + counterPosIndex);
}

function createLabel(id, textLabel) {
    return $("<label/>", {
        for: id,
        html: textLabel,
    });
}

function createInput(id) {
    return $("<input/>", {
        "class": "form-control",
        id: id,
        type: "text",
        name: name,
    });
}

function createInputDate(id, name) {
    return $("<input/>", {
        "class": "form-control date",
        id: id,
        type: "text",
        name: name,
        placeholder: "MM/yyyy",
        readonly: true,
    });
}

function addInputField(prefix, sectionType, counterIndex, counterPosIndex, textLabel, addText) {
    let container = '#' + prefix + sectionType + counterIndex;
    let id = counterPosIndex === -1 ? sectionType + addText : sectionType + counterIndex + addText;

    $(container).append(createLabel(id, textLabel));
    $(container).append(createInput(id));
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
        format: 'mm/yyyy',
        width: '234',
    };
    $('#' + id + 'dateBegin')
        .datepicker(paramsDatepicker);
    $('#' + id + 'dateEnd')
        .datepicker(paramsDatepicker);
}

