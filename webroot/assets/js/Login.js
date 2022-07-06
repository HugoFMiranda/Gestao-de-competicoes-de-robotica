function login(){
    let form = document.getElementById("Login");
    let formdata = new FormData(form);

    fetch("/submitlogin", {
        method: "POST",
        body: formdata,
    })
        .then((res) => {
            if (res.status === 200) {
                //alert("Login efetuado com sucesso como equipa!");
                window.location.assign("/PaginaInicialEquipa.html");
                return res.json();
            } else if (res.status === 201) {
                //alert("Login efetuado com sucesso como juri!");
                window.location.assign("/PaginaInicialJuri.html");
                return res.json();
            } else if (res.status === 202) {
                //alert("Login efetuado com sucesso como organizador!");
                window.location.assign("/PaginaInicialOrganizador.html");
                return res.json();
            } else if(res.status === 404) {
                alert("Utilizador não encontrado. Tente novamente!");
                window.location.assign("/Login.html");
                return res.json();
            } else{
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));
}