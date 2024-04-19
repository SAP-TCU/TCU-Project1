function editRecord() {
    alert('To be added...');
}

function deleteRecord(cartId, productId, quantity) {
    fetch("http://localhost:8080/cart-items/remove", {
        method: "POST",
        body: JSON.stringify({
            cartId: cartId,
            productId: productId,
            quantity: quantity
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    }).then(() => {
        location.reload();
    })
}
