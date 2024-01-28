const postKata = (url) => {
    fetch(url, {
        method:"POST",
        headers:{"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => response.json)
    .then(json => showToast('Success', 'Orders will be imported shortly, please wait patiently.'))
    .catch(err => console.log(err))
}

const deleteKata = (url) => {
    fetch(url, {
        method:"DELETE",
        headers:{"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => response.json)
    .then(json => console.log(json))
    .catch(err => console.log(err))
}

const downloadCsv = async (url) => {
    fetch(url, {
        method:"GET"
    })
    .then(response => response.blob())
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = "orders.csv";
        a.click();
    })
};

const showToast = (headerText, bodyText) => {
    const toast = document.querySelector('.toast');
    const header = document.querySelector('.toast-header-text');
    const body = document.querySelector('.toast-body-text');

    header.textContent = headerText;
    body.textContent = bodyText;
    toast.classList.add("show");
}