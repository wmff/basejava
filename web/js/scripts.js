function addFields(containerName, name) {
    var container = document.getElementById(containerName);
    var li = document.createElement("li");
    var input = document.createElement("input");
    input.type = "text";
    input.size = 100;
    input.name = name;
    li.appendChild(input);
    container.appendChild(li);
}
