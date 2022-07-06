function verCompeticao(){
    document.getElementById("Competicoes").style.display = "none";
    document.getElementById("VerCompeticao").style.display = "flex";
}

function voltarCompeticoes(){
    document.getElementById("Competicoes").style.display = "flex";
    document.getElementById("VerCompeticao").style.display = "none";
    document.getElementById("Ranking").style.display = "none";
    document.getElementById("Rondas").style.display = "none";
}
function verRondas(id) {
    fetch("Equipa/" + id + "/getRondas", {
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
                let listaRondas = "";

                for (let i = 0; i < data.length; i++) {
                    listaRondas += "<tr style='width: 623px;'>";
                    listaRondas += "<td onclick='verDados(" + data[i].id + ")' style='text-align: center;'>" + data[i].numero + "</td>";
                    listaRondas += "<td onclick='verDados(" + data[i].id + ")' style='text-align: center;'>" + data[i].tipo + "</td>";
                    listaRondas += "</tr>";
                }
                document.getElementById("ListaRondas").innerHTML = listaRondas;
            }
        })
    document.getElementById("Competicoes").style.display = "none";
    document.getElementById("Rondas").style.display = "flex";
}

function voltarRondas() {
    document.getElementById("Rondas").style.display = "flex";
    document.getElementById("Dados").style.display = "none";
}

function verDados(id) {
    idRonda = id;
    fetch("Equipa/" + id + "/getDados", {
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
                let listaDados = "";

                for (let i = 0; i < data.length; i++) {
                    listaDados += "<tr style=width: 623px;'>";
                    listaDados += "<td style='text-align: center;'>" + data[i].robot + "</td>";
                    listaDados += "<td style='text-align: center;'>" + data[i].velocidade + "</td>";
                    listaDados += "<td style='text-align: center;'>" + data[i].tempo + "</td>";
                    listaDados += "<td style='text-align: center;'>" + data[i].pontos + "</td>";
                    listaDados += "</tr>";
                }
                document.getElementById("ListaDados").innerHTML = listaDados;
            }
        })

    document.getElementById("Rondas").style.display = "none";
    document.getElementById("Dados").style.display = "flex";
}

function verCompeticoes(){
fetch('/Equipa/getCompeticoes', {
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
            let listaCompeticao = "";

            for (let i = 0; i < data.length; i++) {
                if(data[i].equipalogadoAssociado === false){
                    listaCompeticao += "<tr name='naoassociado' style='display:table-row;width: 623px;'>";
                } else {
                    listaCompeticao += "<tr name='associado' style='display:table-row;width: 623px;'>";
                }
                listaCompeticao += "<td onclick='verRondas(" + data[i].id + ")' style='text-align: center;'>" + data[i].id + "</td>";
                listaCompeticao += "<td onclick='verRondas(" + data[i].id + ")' style='text-align: center;'>" + data[i].nome + "</td>";
                listaCompeticao += "<td onclick='verRondas(" + data[i].id + ")' style='text-align: center;'>" + data[i].desc + "</td>";
                listaCompeticao += "<td onclick='verRondas(" + data[i].id + ")' style='text-align: center;'>" + data[i].nrondas + "</td>";
                listaCompeticao += "<td class='d-md-flex justify-content-md-center align-items-md-center' style='text-align: center;'><button class='btn btn-primary d-md-flex d-lg-flex justify-content-md-center align-items-md-center justify-content-lg-center align-items-lg-center' type='button' onclick='verCompeticao(" + data[i].id + ")' style='height: 25px;width: 25px;background: var(--bs-teal);'><i class='far fa-eye d-lg-flex justify-content-lg-center align-items-lg-center'></i></button><button class='btn btn-primary d-md-flex d-lg-flex justify-content-md-center align-items-md-center justify-content-lg-center align-items-lg-center' type='button' onclick='verRankingCompeticao(" + data[i].id + ")' style='height: 25px;width: 25px;background: var(--bs-gray-dark);margin-left: 10px;'><i class='fas fa-trophy d-lg-flex justify-content-lg-center align-items-lg-center'></i></button>"
                if(data[i].equipalogadoAssociado === false){
                    listaCompeticao += "<button class='btn btn-primary d-md-flex d-lg-flex justify-content-md-center align-items-md-center justify-content-lg-center align-items-lg-center' type='button' onclick='Inscrever(" + data[i].id + ")' style='height: 25px;width: 75px;background: #5bbf21;margin-left: 20px;'>Inscrever<br></button></td>";
                } else {
                    listaCompeticao += "<button class='btn btn-primary d-md-flex d-lg-flex justify-content-md-center align-items-md-center justify-content-lg-center align-items-lg-center' type='button' onclick='Sair(" + data[i].id + ")' style='height: 25px;width: 75px;background: firebrick;margin-left: 20px;'>Sair<br></button></td>"
                }
                listaCompeticao += "</tr>";
            }
            document.getElementById("ListaCompeticoes").innerHTML = listaCompeticao;
        }
    })
    .catch((err) => console.log(err));
}

function verMinhasCompeticoes(){

    if(document.getElementById("minhascompeticoes").checked == true) {
        for(let i = 0; i < document.getElementsByName("naoassociado").length; i++){
            document.getElementsByName("naoassociado")[i].style.display = 'none';
        }
    } else{
        for(let i = 0; i < document.getElementsByName("naoassociado").length; i++) {
            document.getElementsByName("naoassociado")[i].style.display = 'table-row';
        }
    }
}

function verCompeticao(id) {
    fetch("/Equipa/getCompeticao/" + id, {
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
            let equipas = "";
            let juris = "";

            let nome = "Competição " + data.nome;

            document.getElementById("nome").innerHTML = nome;
            let nomeOrganizador = "<label className='form-label' style='margin-top: 15px;'>Organizador:<br></label> <label className='form-label' style='margin-left: 15px;'>" + data.nome_organizador + "</label>"

            document.getElementById("organizador").innerHTML = nomeOrganizador;

            equipas = "<label className='form-label' style='margin-top: 15px;'>Equipas registadas:<br></label>"
            for (let i = 0; i < data.equipasRegistadas.length; i++) {
                equipas += "<label className='form-label' style='margin-left: 15px;'> * " + data.equipasRegistadas[i] + "</label>";
            }

            document.getElementById("equipas").innerHTML = equipas;

            juris = "<label class='form-label' style='margin-top: 15px;'>Juris:<br></label>";
            for (let i = 0; i < data.jurisRegistados.length; i++) {
                juris += "<label class='form-label' style='margin-left: 15px;'> * " + data.jurisRegistados[i] + "</label>";
            }

            document.getElementById("juris").innerHTML = juris;

            let dataCriacao = "<label class='form-label' style='margin-top: 15px;'>Data de criação:<br></label><label id='datacriacao' class='form-label' style='margin-left: 15px;'>" + data.data_criacao + "</label>"

            document.getElementById("datacriacao").innerHTML = dataCriacao;

            let estado = "<label class='form-label' style='margin-top: 15px;'>Estado:<br></label><label id='estado' class='form-label' style='margin-left: 15px;'>" + data.estado + "</label>"

            document.getElementById("estado").innetHTML = estado;


        })

    document.getElementById("Competicoes").style.display = "none";
    document.getElementById("VerCompeticao").style.display = "flex";
}

function verRankingCompeticao(id){
    fetch("/Equipa/" + id + "/getRankings", {
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
            if(data.length > 0) {
                document.getElementById("pontuacaoPrimeiro").innerHTML = data[0].pontos;
                document.getElementById("pontuacaoSegundo").innerHTML = data[1].pontos;
                document.getElementById("pontuacaoTerceiro").innerHTML = data[2].pontos;

                document.getElementById("melhorTempoPrimeiro").innerHTML = data[0].besttempo;
                document.getElementById("melhorTempoSegundo").innerHTML = data[1].besttempo;
                document.getElementById("melhorTempoTerceiro").innerHTML = data[2].besttempo;

                document.getElementById("velocidadeMaximaPrimeiro").innerHTML = data[0].velmax;
                document.getElementById("velocidadeMaximaSegundo").innerHTML = data[1].velmax;
                document.getElementById("velocidadeMaximaTerceiro").innerHTML = data[2].velmax;

                document.getElementById("equipaPrimeiro").innerHTML = data[0].nome_equipa;
                document.getElementById("equipaSegundo").innerHTML = data[1].nome_equipa;
                document.getElementById("equipaTerceiro").innerHTML = data[2].nome_equipa;

                let listaRankings = "";
                for (let i = 0; i < data.length; i++){
                    listaRankings += "<tr style=width: 680px;'>";
                    listaRankings += "<td style='text-align: center;'>" + (i+1) + "</td>";
                    listaRankings += "<td style='text-align: center;'>" + data[i].nome_equipa + "</td>";
                    listaRankings += "<td style='text-align: center;'>" + data[i].nome_robo + "</td>";
                    listaRankings += "<td style='text-align: center;'>" + data[i].besttempo + "</td>";
                    listaRankings += "<td style='text-align: center;'>" + data[i].velmax + "</td>";
                    listaRankings += "<td style='text-align: center;'>" + data[i].pontos + "</td>";
                    listaRankings += "</tr>";
                }

                document.getElementById("ListaRankings").innerHTML = listaRankings;
            }
        })
    document.getElementById("Competicoes").style.display = "none";
    document.getElementById("Ranking").style.display = "flex";
}

function Inscrever(id){
    fetch("/Equipa/" + id + "/inscreverEquipa", {
        method: 'POST'
    })
        .then((res) => {
            if (res.status === 200) {
                verCompeticoes();
                return res.json();
            } else if (res.status === 404) {
                window.location.assign("/error404.html");
                return res.json();
            } else
                throw Error("Erro no servidor!!");
        })
}

function Sair(id){
    fetch("/Equipa/" + id + "/removerEquipa", {
        method: 'DELETE'
    })
        .then((res) => {
            if (res.status === 200) {
                verCompeticoes();
                return res.json();
            } else if (res.status === 404) {
                window.location.assign("/error404.html");
                return res.json();
            } else
                throw Error("Erro no servidor!!");
        })
}