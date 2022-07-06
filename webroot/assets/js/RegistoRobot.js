function registarRobot(){

    let form = document.getElementById("registarRobot");
    let formdata = new FormData(form);

    fetch("/Equipa/addRobot", {
        method: "POST",
        body: formdata,
    })
        .then((res) => {
            if (res.status === 200) {
                alert("Robot registado com sucesso!");
                window.location.assign("PaginaInicialEquipa.html");
                return res.json();
            }else if(res.status === 404){
                window.location.assign("error404.html");
                return res.json();
            } else {
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));


}