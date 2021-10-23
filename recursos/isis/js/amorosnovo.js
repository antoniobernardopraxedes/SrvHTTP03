
//const form = document.getElementById('signup');
//const userName = form.elements['username'];

function VerificaArquivo() {

    let requisicao = new XMLHttpRequest();
    let nomeArquivo = "mariaeareformaintima.txt";
    let recurso = "/isis/ler/" + nomeArquivo;
    requisicao.open("GET", recurso, true);
    requisicao.timeout = 2000;
    requisicao.send(null);

    requisicao.onload = function() {
        let dadosJson = JSON.parse(requisicao.responseText);
        let numParagrafos = dadosJson.length;
        for (let i = 0; i < 20; i++) {
            document.getElementById("p" + i).innerHTML = dadosJson[i];
        }
    };

    requisicao.ontimeout = function(e) {
        console.log("Erro: " + e);
    };
}
