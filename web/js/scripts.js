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

     addPosition(sectionType, counterIndex+1);
     let link = document.createElement("a");
     link.href = "#" + sectionType + counterIndex;
     link.className = "btn btn-primary mb-2";
     link.setAttribute("onclick", `addPosition('${sectionType}', ${counterIndex+1})`);
     link.innerText = "добавить позицию";
     divContOrg.appendChild(link);
}

function addPosition(sectionType, counterIndex) {
    addPositionDateField("container", sectionType, counterIndex-1, "Дата начала", "dateBegin");
    addPositionDateField("container", sectionType, counterIndex-1, "Дата окончания", "dateEnd");

    addPositionField("container", sectionType, counterIndex-1, "Должность", "title");
    addPositionField("container", sectionType, counterIndex-1, "Описание", "description");
}

function addPositionDateField(prefix, sectionType, counterIndex, textLabel, addText) {
    let container = document.getElementById(prefix + sectionType + counterIndex);
    let label = document.createElement("label");
    label.innerHTML = textLabel;
    label.htmlFor = sectionType + counterIndex + addText;
    let input = document.createElement("input");
    input.className = "form-control";
    input.id = sectionType + counterIndex + addText;
    input.type = "text";
    input.name = sectionType + counterIndex + addText;
    input.placeholder = "MM/yyyy";
    container.appendChild(label);
    container.appendChild(input);
}

function addPositionField(prefix, sectionType, counterIndex, textLabel, addText) {
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
