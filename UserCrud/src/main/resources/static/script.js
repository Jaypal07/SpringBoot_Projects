const baseUrl = "http://localhost:8080/user";

function showMessage(message, type = "success") {
    const msgBox = document.getElementById("messageBox");
    msgBox.textContent = message;
    msgBox.className = `message ${type}`;
    msgBox.style.display = "block";
    setTimeout(() => {
        msgBox.style.display = "none";
    }, 4000);
}

function clearInputs(ids) {
    ids.forEach(id => {
        document.getElementById(id).value = "";
    });
}

async function addUser() {

    const firstName = document.getElementById("addFirstName").value.trim();
    const lastName = document.getElementById("addLastName").value.trim();
    const email = document.getElementById("addEmail").value.trim();

    if (!firstName || !lastName || !email) {
        showMessage("All fields are required to add a user", "error");
        return;
    }

    try {
        const response = await fetch(`${baseUrl}/add-user`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ firstName, lastName, email })
        });

        const message = await response.text();
        if (response.ok) {
            showMessage(message, "success");
            clearInputs(["addFirstName", "addLastName", "addEmail"]);
            // getAllUsers(); // auto-refresh user list
        } else {
            showMessage("Failed to add user", "error");
        }
    } catch (error) {
        showMessage("Error adding user", "error");
    }
}

async function updateUser() {
    const id = document.getElementById("updateId").value.trim();
    const firstName = document.getElementById("updateFirstName").value.trim();
    const lastName = document.getElementById("updateLastName").value.trim();
    const email = document.getElementById("updateEmail").value.trim();

    if (!id || !firstName || !lastName || !email) {
        showMessage("All fields are required to update a user", "error");
        return;
    }

    try {
        const response = await fetch(`${baseUrl}/update/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ id, firstName, lastName, email })
        });

        const message = await response.text();
        if (response.ok) {
            showMessage(message, "success");
            clearInputs(["updateId", "updateFirstName", "updateLastName", "updateEmail"]);
            // getAllUsers();
        } else {
            showMessage("Failed to update user", "error");
        }
    } catch (error) {
        showMessage("Error updating user", "error");
    }
}

async function deleteUser() {
    const id = document.getElementById("deleteId").value.trim();

    if (!id) {
        showMessage("User ID is required to delete", "error");
        return;
    }

    try {
        const response = await fetch(`${baseUrl}/delete/${id}`, {
            method: "DELETE"
        });

        const message = await response.text();
        if (response.ok) {
            showMessage(message, "success");
            clearInputs(["deleteId"]);
            // getAllUsers();
        } else {
            showMessage("Failed to delete user", "error");
        }
    } catch (error) {
        showMessage("Error deleting user", "error");
    }
}

async function getAllUsers() {
    try {
        const response = await fetch(`${baseUrl}/users`);
        const users = await response.json();

        const userList = document.getElementById("userList");
        userList.innerHTML = "";

        users.forEach(user => {
            const li = document.createElement("li");
            li.textContent = `ID: ${user.id}, Name: ${user.firstName} ${user.lastName}, Email: ${user.email}`;
            userList.appendChild(li);
        });
    } catch (error) {
        showMessage("Failed to fetch users", "error");
    }
}
