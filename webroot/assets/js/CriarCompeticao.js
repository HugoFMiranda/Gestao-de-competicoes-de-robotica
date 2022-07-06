function registarCompeticao(){
    let form = document.getElementById("registarCompeticao");
    let formdata = new FormData(form);

    fetch("/Organizador/addCompeticao", {
        method: "POST",
        body: formdata,
    })
        .then((res) => {
            if (res.status === 200) {
                //alert("Registo efetuado com sucesso da competicao!");
                window.location.assign("/PaginaInicialOrganizador.html");
                return res.json();
            } else {
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));
}