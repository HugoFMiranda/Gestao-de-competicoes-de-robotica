function verEquipa(id){

    fetch('/Organizador/' + id + "/getEquipa", {
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
            document.getElementById("nome").innerHTML = "Equipa " + data.nome;
            let listaElementos = "";
            for(let i = 0; i<data.elementos.length; i++){
                listaElementos  += "<div style='display: flex;flex-direction: column; justify-content: center;align-items: center;margin-top:10%; margin-right: 5%'><img alt='' class='rounded-circle flex-shrink-0 me-3 fit-cover' width='100' height='100' src='https://cdn.bootstrapstudio.io/placeholders/1400x800.png' style='margin-bottom: 10px;'><h5 class='fw-bold text-primary mb-0'>" + data.elementos[i].nome + "</h5></div>"
            }
            document.getElementById("elementos").innerHTML = listaElementos;
        })


    document.getElementById("Equipas").style.display = "none";
    document.getElementById("VerEquipa").style.display = "flex";
}

function voltarEquipas(){
    document.getElementById("Equipas").style.display = "flex";
    document.getElementById("VerEquipa").style.display = "none";
}

function verEquipas(){
    fetch('/Organizador/getEquipas', {
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
                let listaEquipas = "";

                for (let i = 0; i < data.length; i++) {
                    if(data[i].organizadorLogadoAssociado === false){
                        listaEquipas += "<tr name='organizadornaoassociado'>";
                    } else {
                        listaEquipas += "<tr name='organizadorassociado'>";
                    }
                    listaEquipas += "<td class='text-center'>" + data[i].id + "</td>";
                    listaEquipas += "<td class='text-center'>" + data[i].nome + "</td>";
                    listaEquipas += "<td class='text-center'>";
                    for(let a = 0; a < data[i].competicoes.length; a++){
                        listaEquipas += " * " + data[i].competicoes[a] + "  ";
                    }
                    listaEquipas += "</td>";
                    listaEquipas += "<td class='text-center d-lg-flex justify-content-lg-end'><button class='btn btn-primary d-lg-flex justify-content-lg-center align-items-lg-center' onclick='verEquipa(" + data[i].id + ")' type='button' style='height: 25px;width: 25px;background: var(--bs-teal);margin-right: 100px;'><i class='far fa-eye d-lg-flex justify-content-lg-center align-items-lg-center'></i></button></td>";
                    listaEquipas += "</tr>";
                }

                document.getElementById("ListaEquipas").innerHTML = listaEquipas;
            }

        })
}

function verEquipasAssociadas(){
    console.log(document.getElementsByName("organizadornaoassociado"));
    if(document.getElementById("equipasAssociadas").checked == true) {
        for(let i = 0; i < document.getElementsByName("organizadornaoassociado").length; i++){
            document.getElementsByName("organizadornaoassociado")[i].style.display = 'none';
        }
    } else{
        for(let i = 0; i < document.getElementsByName("organizadornaoassociado").length; i++) {
            document.getElementsByName("organizadornaoassociado")[i].style.display = 'table-row';
        }
    }
}