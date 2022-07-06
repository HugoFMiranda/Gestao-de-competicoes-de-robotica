function editarPerfil(){
    document.getElementById("Perfil").style.display = "none";
    document.getElementById("Editar").style.display = "flex";
}

function voltarPerfil(){
    document.getElementById("Perfil").style.display = "flex";
    document.getElementById("Editar").style.display = "none";
}

function verEquipa(){
    fetch("/Equipa/getEquipa", {
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
               document.getElementById("nome").innerHTML = data.nome;
               let listaElementos = "";
               let listaEditarElementos = "";
               for(let i = 0; i<data.elementos.length ;i++){
                   listaElementos += "<div class='card border-0 shadow-none d-xl-flex flex-row justify-content-xl-center align-items-xl-center'>";
                   listaElementos += "<div class='card-body d-flex flex-column align-items-center justify-content-lg-center justify-content-xl-center p-0'>";
                   listaElementos += "<img alt='' class='rounded-circle flex-shrink-0 me-3 fit-cover' width='100' height='100' src='https://cdn.bootstrapstudio.io/placeholders/1400x800.png' style='margin-bottom: 10px;'>";
                   listaElementos += "<div><h5 class='fw-bold text-primary mb-0'>" + data.elementos[i].nome + "</h5></div></div></div>";
                   listaEditarElementos += "<div class='d-md-flex justify-content-md-start align-items-md-center form-group mb-3' style='margin-top: 10px'><label class='form-label' style='margin-top: 0;margin-right: 20px;margin-bottom: 0;'0>" + data.elementos[i].nome + "</label><button class='btn btn-primary d-lg-flex justify-content-lg-center align-items-lg-center' onclick='eliminar(" + data.elementos[i].id + ")' type='button' style='height: 25px;width: 25px;background: var(--bs-danger);margin-left: 25px;'><i class='fas fa-trash-alt d-lg-flex justify-content-lg-center align-items-lg-center'></i></button></div>"
               }
               document.getElementById("Elementos").innerHTML = listaElementos;
               document.getElementById("EditarElementos").innerHTML = listaEditarElementos;
               document.getElementById("nomeEditar").innerHTML = "Editar perfil de " + data.nome;

        })
}

function inserirMembro(){
    document.getElementById("novosMembros").innerHTML = "<div style='display:flex; flex-direction: column'>Nome:<input class='form-control' name='membro' type='text' style='margin-left: 10px;'>Email:<input class='form-control' name='email' type='text' style='margin-left: 10px;'><button onclick='guardarMembro()' class='btn btn-primary d-lg-flex justify-content-lg-center align-items-lg-center' type='button' style='height: 25px;width: 25px;background: var(--bs-teal);margin-top: 10px;'><i class='fas fa-save d-lg-flex justify-content-lg-center align-items-lg-center'></i></div>"
}

function guardarMembro(){
    let form = document.getElementById("EditarEquipa");
    let formdata = new FormData(form);
    fetch("/Equipa/adicionarMembro",{
        method: 'POST',
        body: formdata
    })
      .then((res) => {
        if (res.status === 200) {
            verEquipa();
            return res.json();
        } else if (res.status === 404) {
            window.location.assign("/error404.html");
            return res.json();
        } else
            throw Error("Erro no servidor!!");
    })
    document.getElementById("novosMembros").innerHTML = "<button onClick='inserirMembro()' class='btn btn-primary d-lg-flex justify-content-lg-center align-items-lg-center' type='button' style='height: 25px;width: 25px;background: var(--bs-teal);margin-left: 25px;'><i class='fas fa-plus-circle d-lg-flex justify-content-lg-center align-items-lg-center'></i>";
}

function editarEquipa(){
    let form = document.getElementById("EditarEquipa");
    let formdata = new FormData(form);
    fetch("/Equipa/updateEquipa",{
        method: 'PUT',
        body: formdata
    })
        .then((res) => {
            if (res.status === 200) {
                verEquipa();
                voltarPerfil();
                return res.json();
            } else if (res.status === 404) {
                window.location.assign("/error404.html");
                return res.json();
            } else
                throw Error("Erro no servidor!!");
        })
}

function eliminar(id){
    fetch("/Equipa/" + id + "/removerMembro",{
        method: 'DELETE',
    })
        .then((res) => {
            if (res.status === 200) {
                verEquipa();
                return res.json();
            } else if (res.status === 404) {
                window.location.assign("/error404.html");
                return res.json();
            } else
                throw Error("Erro no servidor!!");
        })
}