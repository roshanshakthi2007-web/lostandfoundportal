document.getElementById("reportBtn").addEventListener("click", reportItem);

async function reportItem() {

    const item = {
        itemName: document.getElementById("itemName").value,
        category: document.getElementById("category").value,
        location: document.getElementById("location").value
    };

    const response = await fetch("/report", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(item)
    });

    const message = await response.text();

    alert(message);

}
loadItems();

loadItems();

async function loadItems() {

    const response = await fetch("/items");

    const items = await response.json();

    const itemsDiv = document.getElementById("items");

    itemsDiv.innerHTML = "";

    for (const item of items) {

        itemsDiv.innerHTML += `
            <div class="item">

    <h3>${item.itemName}</h3>

    <p><b>Category:</b> ${item.category}</p>

    <p><b>Location:</b> ${item.location}</p>
    
    <button onclick="UpdateItem(${item.id})">Update</button>
    <button onclick="deleteItem(${item.id})">Delete</button>

</div>
        `;

    }

}
async function deleteItem(id) {

    const password = prompt("Enter Password");

    if (password == null)
        return;

    const response = await fetch("/delete?id=" + id + "&password=" + encodeURIComponent(password), {
        method: "DELETE"
    });

    const message = await response.text();

    alert(message);

    loadItems();
}
async function updateItem(id) {

    const location = prompt("Enter New Location");

    if (location == null)
        return;

    const password = prompt("Enter Password");

    if (password == null)
        return;

    const response = await fetch(
        "/update?id=" + id +
        "&location=" + encodeURIComponent(location) +
        "&password=" + encodeURIComponent(password),
        {
            method: "PUT"
        });

    const message = await response.text();

    alert(message);

    loadItems();
}