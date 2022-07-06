function verDados(){
    document.getElementById("Robos").style.display = "none";
    document.getElementById("Dados").style.display = "flex";
}

function voltarRobos(){
    document.getElementById("Robos").style.display = "flex";
    document.getElementById("Dados").style.display = "none";
}

function verRobots() {

    fetch('/Equipa/getRobos', {
        method: 'GET'
    })
        .then((res) => {
            if (res.status === 200) {
                return res.json();
            } else if (res.status === 404) {
                window.location.assign("/error404.html");
                return res.json();
            } else
                throw Error("Erro no servidor!!");
        })
        .then((data) => {
            if (data.length > 0) {
                let listaRobots = "";

                for (let i = 0; i < data.length; i++) {
                    listaRobots += "<a class='list-group-item list-group-item-action flex-column align-items-start' href='#'>";
                    listaRobots += "<div class='d-lg-flex align-items-lg-center d-flex w-100 justify-content-between'>";
                    listaRobots += "<h5 class='mb-1'>" + data[i].nome + "</h5><small>" + data[i].data_creacao + "</small>";
                    listaRobots += "<button class='btn btn-primary btn-sm fw-normal d-lg-flex d-xl-flex justify-content-lg-center align-items-lg-center justify-content-xl-center align-items-xl-center' onClick='verDados(" + data[i].id + ")' type='button' style='background: var(--bs-purple);width: 65px;height: 25px;text-align: center;margin: -292px -292px -292px 0;'>Dados</button>";
                    listaRobots += "<button class='btn btn-primary btn-sm fw-normal d-lg-flex d-xl-flex justify-content-lg-center align-items-lg-center justify-content-xl-center align-items-xl-center' onClick='eliminar(" + data[i].id + ")' type='button' style='background: var(--bs-danger);width: 65px;height: 25px;text-align: center;margin-left: 0;'>Eliminar</button>";
                    listaRobots += "</div></a>";
                }
                document.getElementById("listaRobots").innerHTML = listaRobots;
            }
        })
        .catch((err) => console.log(err));
}

function eliminar(id){
    fetch('/Equipa/' + id + "/removerRobot", {
        method: 'DELETE'
    })
        .then((res) => {
            if (res.status === 200) {
                verRobots()
                return res.json();
            } else if (res.status === 404) {
                window.location.assign("/error404.html");
                return res.json();
            } else
                throw Error("Erro no servidor!!");
        })
}