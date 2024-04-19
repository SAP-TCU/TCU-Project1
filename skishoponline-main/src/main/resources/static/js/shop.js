function performBuy(button, cartId, productId) {
    let valueOfInput = button.previousElementSibling.value;
    fetch("http://localhost:8080/cart-items/save", {
        method: "POST",
        body: JSON.stringify({
            cartId: cartId,
            productId: productId,
            quantity: parseInt(valueOfInput)
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
}
