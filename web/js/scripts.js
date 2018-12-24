function addPosition(sectionType, counterIndex) {


    addPositionDateField(sectionType, counterIndex, "Дата начала", "dateBegin");
    addPositionDateField(sectionType, counterIndex, "Дата окончания", "dateEnd");

    addPositionField(sectionType, counterIndex, "Должность", "title");
    addPositionField(sectionType, counterIndex, "Описание", "description");
}

function addPositionDateField(sectionType, counterIndex, textLabel, addText) {
    let container = document.getElementById("container" + sectionType + counterIndex);
    let label = document.createElement("label");
    label.textContent = textLabel;
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

function addPositionField(sectionType, counterIndex, textLabel, addText) {
    let container = document.getElementById("container" + sectionType + counterIndex);
    let labelPosition = document.createElement("label");
    labelPosition.textContent = textLabel;
    labelPosition.htmlFor = sectionType + counterIndex + addText;
    let inputPosition = document.createElement("input");
    inputPosition.className = "form-control";
    inputPosition.id = sectionType + counterIndex + addText;
    inputPosition.type = "text";
    inputPosition.name = sectionType + counterIndex + addText;
    container.appendChild(labelPosition);
    container.appendChild(inputPosition);
}
