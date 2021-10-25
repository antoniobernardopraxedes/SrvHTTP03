//*********************************************************************************************************************
//                                                                                                                    *
//                       Programa Javascript para cadastro de artigos no banco de dados                               *
//                                                                                                                    *
// Data: 25/10/2021                                                                                                   *
//                                                                                                                    *
// Função: permite cadastrar artigos no banco de dados do servidor                                                    *
//                                                                                                                    *
//*********************************************************************************************************************
//
const form = document.getElementById('dadosartigo');

const Titulo = document.getElementById('titulo');
const Autor = document.getElementById('autor');
const Data = document.getElementById('data');
const SubTitulo01 = document.getElementById('subtitulo01');
const SubTitulo02 = document.getElementById('subtitulo02');
const SubTitulo03 = document.getElementById('subtitulo03');
const SubTitulo04 = document.getElementById('subtitulo04');
const SubTitulo05 = document.getElementById('subtitulo05');
const SubTitulo06 = document.getElementById('subtitulo06');
const SubTitulo07 = document.getElementById('subtitulo07');
const SubTitulo08 = document.getElementById('subtitulo08');
const SubTitulo09 = document.getElementById('subtitulo09');
const SubTitulo10 = document.getElementById('subtitulo10');

const Artigo = { titulo: "",
                 autor: "",
                 data: "",
                 subTitulo01: "",
                 subTitulo02: "",
                 subTitulo03: "",
                 subTitulo04: "",
                 subTitulo05: "",
                 subTitulo06: "",
                 subTitulo07: "",
                 subTitulo08: "",
                 subTitulo09: "",
                 subTitulo10: ""  };

const ArtigoRec = { id: 0,
                    titulo: "",
                    autor: "",
                    data: "",
                    subTitulo01: "",
                    subTitulo02: "",
                    subTitulo03: "",
                    subTitulo04: "",
                    subTitulo05: "",
                    subTitulo06: "",
                    subTitulo07: "",
                    subTitulo08: "",
                    subTitulo09: "",
                    subTitulo10: ""  };

//const Paragrafos[];

//*********************************************************************************************************************
// Nome da função: VerificaArtigo                                                                                     *
//                                                                                                                    *
// Data: 25/10/2021                                                                                                   *
//                                                                                                                    *
// Função: envia as informações digitadas para o servidor para pesquisa no banco de dados sobre um artigo             *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function VerificaArtigo() {

    CarregaVariaveisFormulario();

}

function CarregaVariaveisFormulario() {

    Artigo.titulo = Titulo.value;
    Artigo.autor = Autor.value;
    Artigo.data = Data.value;
    Artigo.subTitulo01 = SubTitulo01.value;
    Artigo.subTitulo02 = SubTitulo02.value;
    Artigo.subTitulo03 = SubTitulo03.value;
    Artigo.subTitulo04 = SubTitulo04.value;
    Artigo.subTitulo05 = SubTitulo05.value;
    Artigo.subTitulo06 = SubTitulo06.value;
    Artigo.subTitulo07 = SubTitulo07.value;
    Artigo.subTitulo08 = SubTitulo08.value;
    Artigo.subTitulo09 = SubTitulo09.value;
    Artigo.subTitulo10 = SubTitulo10.value;
}

//*********************************************************************************************************************
// Nome da função: CadastraArtigo                                                                                     *
//                                                                                                                    *
// Data: 25/10/2021                                                                                                   *
//                                                                                                                    *
// Função: efetua o cadastro de um artigo.                                                                            *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CadastraArtigo() {

    if (confirm("Confirma o cadastro do artigo " + Artigo.titulo + "?")) {

        CarregaVariaveisFormulario();

        let requisicao = new XMLHttpRequest();
        let recurso = "/isis/cadastro";
        requisicao.open("POST", recurso, true);
        requisicao.setRequestHeader("Content-Type", "application/json;charset=utf-8");
        requisicao.timeout = 2000;

        requisicao.send(JSON.stringify(Artigo));

        requisicao.onload = function() {
            let statusHTTP = requisicao.status;

            if ((statusHTTP >= 200) && (statusHTTP <= 299)) {
                CarregaDadosArtigo(requisicao);
                EscreveTexto("Servidor: o artigo foi cadastrado");
            }
            else {
                EscreveTexto("Servidor: falha ao cadastrar o artigo");
            }
        };

        requisicao.ontimeout = function(e) {
            EscreveTexto("O servidor não respondeu à requisição");
            console.log("Erro: " + e);
        };
    }
}

//*********************************************************************************************************************
// Nome da função: RecebeArtigo                                                                                       *
//                                                                                                                    *
// Data: 25/10/2021                                                                                                   *
//                                                                                                                    *
// Função: solicita ao servidor um arquivo texto, recebe a mensagem Json com os parágrafos, guarda em um array e      *
//         mostra na tela criando elementos html.                                                                     *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function RecebeArtigo(nomeArquivo) {

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

//*********************************************************************************************************************
// Nome da função: CarregaDadosArtigo                                                                                 *
//                                                                                                                    *
// Data: 25/10/2021                                                                                                   *
//                                                                                                                    *
// Função: faz o parsing do arquivo XML recebido do servidor, lê as informações do artigo e carrega nas variáveis     *
//                                                                                                                    *
// Entrada: mensagem Json recebida do servidor                                                                        *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CarregaDadosArtigo(respostaJson) {
    let statusHTTP = respostaJson.status;
    ArtigoRec.id = 0;
    let resultado = false;

    if ((statusHTTP >= 200) && (statusHTTP <= 299)) {
        resultado = true;
        try {
            let dadosJson = JSON.parse(respostaJson.responseText);
            let numElementos = dadosJson.length;

            ArtigoRec.id = dadosJson.id;
            ArtigoRec.titulo = dadosJson.titulo;
            ArtigoRec.autor = dadosJson.autor;
            ArtigoRec.data = dadosJson.data;
            ArtigoRec.subTitulo01 = dadosJson.subTitulo01;
            ArtigoRec.subTitulo02 = dadosJson.subTitulo02;
            ArtigoRec.subTitulo03 = dadosJson.subTitulo03;
            ArtigoRec.subTitulo04 = dadosJson.subTitulo04;
            ArtigoRec.subTitulo05 = dadosJson.subTitulo05;
            ArtigoRec.subTitulo06 = dadosJson.subTitulo06;
            ArtigoRec.subTitulo07 = dadosJson.subTitulo07;
            ArtigoRec.subTitulo08 = dadosJson.subTitulo08;
            ArtigoRec.subTitulo09 = dadosJson.subTitulo09;
            ArtigoRec.subTitulo10 = dadosJson.subTitulo10;

        } catch (e) {
            EscreveTexto("Falha ao ler o artigo");
        }
    }
    else {
        EscreveTexto("Servidor: artigo não encontrado");
    }
    return resultado;
}

function EscreveTexto(texto) {
    document.getElementById("info").innerHTML = texto;
}
