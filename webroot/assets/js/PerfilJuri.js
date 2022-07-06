let password;

function abrirEditar(){
    document.getElementById("Perfil").style.display = "none";
    document.getElementById("Editar").style.display = "flex";
}

function voltarPerfil(){
    document.getElementById("Perfil").style.display = "flex";
    document.getElementById("Editar").style.display = "none";
}

function verJuri(){
    fetch("/Juri/getJuri", {
        method:'GET'
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
               document.getElementById("nome").innerHTML = "Perfil de " + data.nome;
            document.getElementById("nomeEditar").innerHTML = "Editar de " + data.nome;
               document.getElementById("email").innerHTML = data.email;
               password = data.password;
        })

}

function editarJuri(){
    let form = document.getElementById("edicao");
    let formdata = new FormData(form);
    fetch("/Juri/updateJuri" ,{
        method:'PUT',
        body: formdata
    })
        .then((res) => {
            if (res.status === 200) {
                alert("O seu perfil foi editado com sucesso!");
                verJuri();
                document.getElementById("Editar").style.display = "none";
                document.getElementById("Perfil").style.display = "flex";
                return res.json();
            } else if (res.status === 404) {
                window.location.assign("/error404.html");
                return res.json();
            } else
                throw Error("Erro no servidor!!");
        })

}