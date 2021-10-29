//*********************************************************************************************************************
//                       Programa Javascript para pesquisa de artigos no banco de dados                               *
//                                                                                                                    *
// Data: 27/10/2021                                                                                                   *
//                                                                                                                    *
// Função: permite pesquisar por artigos no banco de dados do servidor                                                *
//*********************************************************************************************************************
//
const form = document.getElementById('dadosartigo');

const Id = document.getElementById('identificador');
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

const Artigo = { id: 0,
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
                 subTitulo10: "",
                 conteudo: ""  };

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
                    subTitulo10: "",
                    conteudo: ""  };

//*********************************************************************************************************************
// Nome da função: CarregaVariaveisFormulario                                                                         *
//                                                                                                                    *
// Data: 27/10/2021                                                                                                   *
//                                                                                                                    *
// Função: carrega as informações do formulário no objeto Artigo                                                      *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CarregaVariaveisFormulario() {

    Artigo.id = Id.value;
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
    let areaDeTexto = document.getElementById("at1");
    Artigo.conteudo = areaDeTexto.value;

}

//*********************************************************************************************************************
// Nome da função: PesquisaId                                                                                         *
//                                                                                                                    *
// Data: 29/10/2021                                                                                                   *
//                                                                                                                    *
// Função: envia as informações digitadas para o servidor para pesquisa no banco de dados sobre um artigo             *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function PesquisaId() {

    CarregaVariaveisFormulario();

    if (Artigo.id == "") {
        EscreveTexto("Entre com o identificador do artigo");
    }
    else {
        let requisicao = new XMLHttpRequest();
        let recurso = "/isis/buscaid/" + Artigo.id;
        requisicao.open("GET", recurso, true);
        requisicao.timeout = 2000;
        requisicao.send(null);

        requisicao.onload = function() {
            let statusHTTP = requisicao.status;

            if ((statusHTTP >= 200) && (statusHTTP <= 299)) {
              let dadosJson = JSON.parse(requisicao.responseText);
              let numResultados = dadosJson.length;
              CarregaDadosArtigo(dadosJson);
              MostraDadosArtigo(1);
            }
            else {
              EscreveTexto("Não foram encontrados resultados da pesquisa");
              LimpaFormulario();
            }
        };

        requisicao.ontimeout = function(e) {
            console.log("Erro: " + e);
        };
    }
}

//*********************************************************************************************************************
// Nome da função: PesquisaArtigo                                                                                     *
//                                                                                                                    *
// Data: 29/10/2021                                                                                                   *
//                                                                                                                    *
// Função: envia as informações digitadas para o servidor para pesquisa no banco de dados sobre um artigo             *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function PesquisaTitulo() {

    CarregaVariaveisFormulario();

    let requisicao = new XMLHttpRequest();
    let recurso = "/isis/buscatitulo/" + Artigo.titulo;
    requisicao.open("GET", recurso, true);
    requisicao.timeout = 2000;
    requisicao.send(null);

    requisicao.onload = function() {
        let statusHTTP = requisicao.status;

        if ((statusHTTP >= 200) && (statusHTTP <= 299)) {
            let dadosJson = JSON.parse(requisicao.responseText);
            let numResultados = dadosJson.length;
            CarregaDadosArtigos(dadosJson, 0);
            MostraDadosArtigo(numResultados);
      }
      else {
          EscreveTexto("Não foram encontrados resultados da pesquisa");
          LimpaFormulario();
      }
  };

    requisicao.ontimeout = function(e) {
        console.log("Erro: " + e);
    };

}

//*********************************************************************************************************************
// Nome da função: CarregaDadosArtigo                                                                                 *
//                                                                                                                    *
// Data: 29/10/2021                                                                                                   *
//                                                                                                                    *
// Função: faz o parsing do arquivo XML recebido do servidor, lê as informações de um artigo e carrega nas variáveis  *
//                                                                                                                    *
// Entrada: mensagem Json recebida do servidor e o índice do artigo na mensagem                                       *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CarregaDadosArtigo(dadosJson) {

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
    ArtigoRec.conteudo = dadosJson.conteudo;

}

//*********************************************************************************************************************
// Nome da função: CarregaDadosArtigos                                                                                *
//                                                                                                                    *
// Data: 25/10/2021                                                                                                   *
//                                                                                                                    *
// Função: faz o parsing do arquivo XML recebido do servidor, lê as informações de uma lista de artigos e carrega     *
//         nas variáveis de um artigo                                                                                 *
//                                                                                                                    *
// Entrada: mensagem Json recebida do servidor e o índice do artigo na mensagem                                       *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CarregaDadosArtigos(dadosJson, indice) {

    ArtigoRec.id = dadosJson[indice].id;
    ArtigoRec.titulo = dadosJson[indice].titulo;
    ArtigoRec.autor = dadosJson[indice].autor;
    ArtigoRec.data = dadosJson[indice].data;
    ArtigoRec.subTitulo01 = dadosJson[indice].subTitulo01;
    ArtigoRec.subTitulo02 = dadosJson[indice].subTitulo02;
    ArtigoRec.subTitulo03 = dadosJson[indice].subTitulo03;
    ArtigoRec.subTitulo04 = dadosJson[indice].subTitulo04;
    ArtigoRec.subTitulo05 = dadosJson[indice].subTitulo05;
    ArtigoRec.subTitulo06 = dadosJson[indice].subTitulo06;
    ArtigoRec.subTitulo07 = dadosJson[indice].subTitulo07;
    ArtigoRec.subTitulo08 = dadosJson[indice].subTitulo08;
    ArtigoRec.subTitulo09 = dadosJson[indice].subTitulo09;
    ArtigoRec.subTitulo10 = dadosJson[indice].subTitulo10;
    ArtigoRec.conteudo = "";

}

//*********************************************************************************************************************
// Nome da função: MostraDadosArtigo                                                                                  *
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
function MostraDadosArtigo(numResultados) {

    document.getElementById("identificador").value = ArtigoRec.id;
    document.getElementById("titulo").value = ArtigoRec.titulo;
    document.getElementById("autor").value = ArtigoRec.autor;
    document.getElementById("data").value = ArtigoRec.data;
    document.getElementById("subtitulo01").value = ArtigoRec.subTitulo01;
    document.getElementById("subtitulo02").value = ArtigoRec.subTitulo02;
    document.getElementById("subtitulo03").value = ArtigoRec.subTitulo03;
    document.getElementById("subtitulo04").value = ArtigoRec.subTitulo04;
    document.getElementById("subtitulo05").value = ArtigoRec.subTitulo05;
    document.getElementById("subtitulo06").value = ArtigoRec.subTitulo06;
    document.getElementById("subtitulo07").value = ArtigoRec.subTitulo07;
    document.getElementById("subtitulo08").value = ArtigoRec.subTitulo08;
    document.getElementById("subtitulo09").value = ArtigoRec.subTitulo09;
    document.getElementById("subtitulo10").value = ArtigoRec.subTitulo10;

    let areaDeTexto = document.getElementById("at1");
    areaDeTexto.value = ArtigoRec.conteudo;
    autoGrow(areaDeTexto);

    if (numResultados > 1) {
        EscreveTexto("Recebidos " + numResultados + " resultados da pesquisa");
    }
    else {
        EscreveTexto("Recebido o resultado da pesquisa");
    }

}

//*********************************************************************************************************************
// Nome da função: LimpaFormulario                                                                                    *
//                                                                                                                    *
// Data: 29/10/2021                                                                                                   *
//                                                                                                                    *
// Função: limpa todos os campos do formulário de entrada de dados                                                    *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
//                                                                                                                    *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function LimpaFormulario() {

    document.getElementById("identificador").value = "";
    document.getElementById("titulo").value = "";
    document.getElementById("autor").value = "";
    document.getElementById("data").value = "";
    document.getElementById("subtitulo01").value = "";
    document.getElementById("subtitulo02").value = "";
    document.getElementById("subtitulo03").value = "";
    document.getElementById("subtitulo04").value = "";
    document.getElementById("subtitulo05").value = "";
    document.getElementById("subtitulo06").value = "";
    document.getElementById("subtitulo07").value = "";
    document.getElementById("subtitulo08").value = "";
    document.getElementById("subtitulo09").value = "";
    document.getElementById("subtitulo10").value = "";

    let areaDeTexto = document.getElementById("at1");
    areaDeTexto.value = "";
    autoGrow(areaDeTexto);

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

function autoGrow (oField) {
    if (oField.scrollHeight > oField.clientHeight) {
      oField.style.height = oField.scrollHeight + "px";
    }
}

function EnviaArtigo() {
    let areaDeTexto = document.getElementById("at1");
    console.log(areaDeTexto.value);

}

function EscreveTexto(texto) {
    document.getElementById("info").innerHTML = " => " + texto;
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
//function RecebeConteudoArtigo(nomeArquivo) {

//    let requisicao = new XMLHttpRequest();
//    let recurso = "/isis/ler/" + nomeArquivo;
//    requisicao.open("GET", recurso, true);
//    requisicao.timeout = 2000;
//    requisicao.send(null);

//    requisicao.onload = function() {
//        let statusHTTP = requisicao.status;

//        if ((statusHTTP >= 200) && (statusHTTP <= 299)) {

//                let dadosJson = JSON.parse(requisicao.responseText);
//                let numParagrafos = dadosJson.length;
//                let conteudo = dadosJson[0];

//                for (let i = 1; i < numParagrafos; i++) {

//                    conteudo = conteudo + "\n" + "\n" + dadosJson[i];

//                  }

//                  let areaDeTexto = document.getElementById("at1");
//                  areaDeTexto.value = conteudo;
//                  autoGrow(areaDeTexto);

//                  EscreveTexto("Recebido arquivo do servidor");


//      }
//      else {
//        EscreveTexto("Servidor: arquivo não encontrado");
//      }
//  };

//    requisicao.ontimeout = function(e) {
//        console.log("Erro: " + e);
//    };

//}
