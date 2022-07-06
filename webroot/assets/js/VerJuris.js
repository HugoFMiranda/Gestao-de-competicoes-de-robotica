function voltarJuris(){
    document.getElementById("Juris").style.display = "flex";
    document.getElementById("VerJuri").style.display = "none";
}

function verJuris(){
    fetch('/Organizador/getJuris', {
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
            if(data.length > 0){
                let listaJuris = "";

                for (let i = 0; i < data.length; i++) {
                    if(data[i].organizadorLogadoAssociado === false){
                        listaJuris += "<tr name='organizadornaoassociado'>";
                    } else {
                        listaJuris += "<tr name='organizadorassociado'>";
                    }
                    listaJuris += "<td class='text-center'>" + data[i].id + "</td>";
                    listaJuris += "<td class='text-center'>" + data[i].nome + "</td>";
                    listaJuris += "<td class='text-center'>";
                    for(let a = 0; a < data[i].competicoes.length; a++){
                        listaJuris += " * " + data[i].competicoes[a] + "  ";
                    }
                    listaJuris += "</td>";
                    listaJuris += "<td class='text-center d-lg-flex justify-content-lg-end'><button class='btn btn-primary d-lg-flex justify-content-lg-center align-items-lg-center' onclick='verJuri(" + data[i].id + ")' type='button' style='height: 25px;width: 25px;background: var(--bs-teal);margin-right: 100px;'><i class='far fa-eye d-lg-flex justify-content-lg-center align-items-lg-center'></i></button></td>";
                    listaJuris += "</tr>";

                }

                document.getElementById("ListaJuris").innerHTML = listaJuris;
            }

        })
}

function verJuri(id){

    fetch('/Organizador/' + id + "/getJuri", {
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
            document.getElementById("nome").innerHTML = "Juri " + data.nome;
            document.getElementById("email").innerHTML = data.email;
        })


    document.getElementById("Juris").style.display = "none";
    document.getElementById("VerJuri").style.display = "flex";
}


function verJurisAssociados(){
    console.log(document.getElementsByName("organizadornaoassociado"));
    if(document.getElementById("jurisAssociados").checked == true) {
        for(let i = 0; i < document.getElementsByName("organizadornaoassociado").length; i++){
            document.getElementsByName("organizadornaoassociado")[i].style.display = 'none';
        }
    } else{
        for(let i = 0; i < document.getElementsByName("organizadornaoassociado").length; i++) {
            document.getElementsByName("organizadornaoassociado")[i].style.display = 'table-row';
        }
    }
}