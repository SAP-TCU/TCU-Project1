function submit() {
    fetch("http://localhost:8080/customers/save", {
        method: "POST",
        body: JSON.stringify({
            firstName: document.getElementById("firstName").value,
            lastName: document.getElementById("lastName").value,
            username: document.getElementById("username").value,
            password: document.getElementById("password").value,
            email: document.getElementById("email").value,
            phoneNumber: document.getElementById("phoneNumber").value
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then(response => {
        if (response.ok) {
            location.href = '/pages/auth/login';
        } else {
            alert('Something went wrong!');
        }
    })
}
