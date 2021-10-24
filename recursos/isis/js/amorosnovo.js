
const form = document.getElementById('dadosartigo');
const NomeArquivo = form.elements['nomearquivo'];

function VerificaArquivo() {

    let nomeArquivo = NomeArquivo.value;
    let requisicao = new XMLHttpRequest();
    let recurso = "/isis/ler/" + nomeArquivo;
    requisicao.open("GET", recurso, true);
    requisicao.timeout = 2000;
    requisicao.send(null);

    requisicao.onload = function() {
        let statusHTTP = requisicao.status;

        if ((statusHTTP >= 200) && (statusHTTP <= 299)) {
            try {
                let dadosJson = JSON.parse(requisicao.responseText);
                let numParagrafos = dadosJson.length;
                
                for (let i = 0; i < numParagrafos; i++) {

                    let paragrafo = document.createElement("p");
                    let conteudo = document.createTextNode(i + 1 + " - " + dadosJson[i]);
                    paragrafo.append(conteudo);
                    paragrafo.style.backgroundColor = "#e7e5e5";
                    paragrafo.style.fontSize = "18px";
                    paragrafo.style.textAlign = "justify";
                    paragrafo.style.paddingLeft = "30px";
                    paragrafo.style.paddingRight = "30px";

                    let pFilho = document.getElementById("elementoFilho");
                    let divPai = pFilho.parentNode;

                    divPai.insertBefore(paragrafo, pFilho);

                    EscreveTexto("Recebido arquivo do servidor");
                }
            } catch (e) {
                EscreveTexto("Falha ao ler o arquivo");
            }
        }
        else {
          EscreveTexto("Servidor: arquivo não encontrado");
        }
    };

    requisicao.ontimeout = function(e) {
        console.log("Erro: " + e);
    };
}

function EscreveTexto(texto) {
    document.getElementById("info").innerHTML = texto;
}
