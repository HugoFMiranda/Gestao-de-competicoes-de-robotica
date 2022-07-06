function verRegistoEquipa(){
    document.getElementById("registoequipa").style.display = "flex";
    document.getElementById("registojuri").style.display = "none";
    document.getElementById("registoorganizador").style.display = "none";
}

function verRegistoJuri(){
    document.getElementById("registoequipa").style.display = "none";
    document.getElementById("registojuri").style.display = "flex";
    document.getElementById("registoorganizador").style.display = "none";
}

function verRegistoOrganizador(){
    document.getElementById("registoequipa").style.display = "none";
    document.getElementById("registojuri").style.display = "none";
    document.getElementById("registoorganizador").style.display = "flex";
}

function registarEquipa(){

    let form = document.getElementById("registarEquipa");
    let formdata = new FormData(form);

    fetch("/submitEquipa", {
        method: "POST",
        body: formdata,
    })
        .then((res) => {
            if (res.status === 200) {
                //alert("Registado com sucesso!");
                window.location.assign("index.html");
                return res.json();

            } else {
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));


}

function registarJuri(){

    let form = document.getElementById("registarJuri");
    let formdata = new FormData(form);

    fetch("/submitJuri", {
        method: "POST",
        body: formdata,
    })
        .then((res) => {
            if (res.status === 200) {
                //alert("Registado com sucesso!");
                window.location.assign("index.html");
                return res.json();

            } else {
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));

}

function registarOrganizador(){

    let form = document.getElementById("registarOrganizador");
    let formdata = new FormData(form);

    fetch("/submitOrganizador", {
        method: "POST",
        body: formdata,
    })
        .then((res) => {
            if (res.status === 200) {
                //alert("Registado com sucesso!");
                window.location.assign("index.html");
                return res.json();

            } else {
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));


}