const newInput = document.querySelector("#new");
const addButton = document.querySelector("#add-btn")

newInput.oninput = (e) => {
    if (e.target.value.trim() != "") {
        addButton.removeAttribute("disabled");
    } else {
        addButton.setAttribute("disabled", true);
    }
}

const toggleStatus = (taskId, checkbox) => {
    document.getElementById('update-form-' + taskId).submit();
    const inputField = checkbox.nextElementSibling;
    inputField.classList.toggle('done', checkbox.checked)
}

const onChange = (taskId, field, prev) => {
    const btn = document.getElementById('update-btn-' + taskId);
    if (field.value !== prev) {
            btn.style.visibility = "visible";
    } else {
        btn.style.visibility = "hidden";
    }

    field.addEventListener("blur", () => {
        if (field.value == prev) {
            btn.style.visibility = "hidden";
        }
    })
}

window.onload = () => {
    console.log("loaded...")
}
