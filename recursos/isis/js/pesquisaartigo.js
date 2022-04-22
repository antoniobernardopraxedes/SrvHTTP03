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
const PalavrasChave = document.getElementById('palavraschave');
const SubTitulo01 = document.getElementById('subtitulo01');
const SubTitulo02 = document.getElementById('subtitulo02');
const SubTitulo03 = document.getElementById('subtitulo03');
const SubTitulo04 = document.getElementById('subtitulo04');
const SubTitulo05 = document.getElementById('subtitulo05');

const Artigo = { id: 0,
                 titulo: "",
                 autor: "",
                 dataPublicacao: "",
                 dataRegistro: "",
                 palavrasChave: "",
                 subTitulo01: "",
                 subTitulo02: "",
                 subTitulo03: "",
                 subTitulo04: "",
                 subTitulo05: "",
                 nomeArquivo: "",
                 conteudo: ""  };

const ArtigoRec = { id: 0,
                    titulo: "",
                    autor: "",
                    dataPublicacao: "",
                    dataRegistro: "",
                    palavrasChave: "",
                    subTitulo01: "",
                    subTitulo02: "",
                    subTitulo03: "",
                    subTitulo04: "",
                    subTitulo05: "",
                    nomeArquivo: "",
                    conteudo: ""  };

var NumResultados;
var Indice;

ArrayId = new Array(10);
ArrayTitulo = new Array(10);
ArrayAutor = new Array(10);
ArrayDataPublicacao = new Array(10);
ArrayDataRegistro = new Array(10);
ArrayPalavrasChave = new Array(10);
ArraySubTitulo01 = new Array(10);
ArraySubTitulo02 = new Array(10);
ArraySubTitulo03 = new Array(10);
ArraySubTitulo04 = new Array(10);
ArraySubTitulo05 = new Array(10);
ArrayNomeArquivo = new Array(10);

//*********************************************************************************************************************
// Nome da função: CarregaVariaveisFormulario                                                                         *
//                                                                                                                    *
// Função: carrega as informações do formulário no objeto Artigo                                                      *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CarregaVariaveisFormulario() {

    Artigo.id = Id.value;
    Artigo.titulo = Titulo.value;
    Artigo.autor = Autor.value;
    Artigo.dataPublicacao = Data.value;
    Artigo.palavrasChave = PalavrasChave.value;
    Artigo.subTitulo01 = SubTitulo01.value;
    Artigo.subTitulo02 = SubTitulo02.value;
    Artigo.subTitulo03 = SubTitulo03.value;
    Artigo.subTitulo04 = SubTitulo04.value;
    Artigo.subTitulo05 = SubTitulo05.value;
    let areaDeTexto = document.getElementById("at1");
    Artigo.conteudo = areaDeTexto.value;

}

//*********************************************************************************************************************
// Nome da função: PesquisaId                                                                                         *
//                                                                                                                    *
// Função: envia as informações digitadas para o servidor para pesquisa no banco de dados sobre um artigo             *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
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
              CarregaDadosArtigo(requisicao);
              MostraDadosArtigo();
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
// Nome da função: CarregaDadosArtigo                                                                                 *
//                                                                                                                    *
// Função: faz o parsing do arquivo XML recebido do servidor, lê as informações de um artigo e carrega nas variáveis  *
//                                                                                                                    *
// Entrada: mensagem Json recebida do servidor e o índice do artigo na mensagem                                       *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CarregaDadosArtigo(requisicao) {

    let dadosJson = JSON.parse(requisicao.responseText);
    ArtigoRec.id = dadosJson.id;
    ArtigoRec.titulo = dadosJson.titulo;
    ArtigoRec.autor = dadosJson.autor;
    ArtigoRec.dataPublicacao = dadosJson.dataPublicacao;
    ArtigoRec.dataRegistro = dadosJson.dataRegistro;
    ArtigoRec.palavrasChave = dadosJson.palavrasChave;
    ArtigoRec.subTitulo01 = dadosJson.subTitulo01;
    ArtigoRec.subTitulo02 = dadosJson.subTitulo02;
    ArtigoRec.subTitulo03 = dadosJson.subTitulo03;
    ArtigoRec.subTitulo04 = dadosJson.subTitulo04;
    ArtigoRec.subTitulo05 = dadosJson.subTitulo05;
    ArtigoRec.subTitulo06 = dadosJson.subTitulo06;
    ArtigoRec.nomeArquivo = dadosJson.nomeArquivo;
    ArtigoRec.conteudo = dadosJson.conteudo;
    NumResultados = 1;
}

//*********************************************************************************************************************
// Nome da função: MostraDadosArtigo                                                                                  *
//                                                                                                                    *
// Função: faz o parsing do arquivo XML recebido do servidor, lê as informações do artigo e carrega nas variáveis     *
//                                                                                                                    *
// Entrada: mensagem Json recebida do servidor                                                                        *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function MostraDadosArtigo() {

    document.getElementById("identificador").value = ArtigoRec.id;
    document.getElementById("titulo").value = ArtigoRec.titulo;
    document.getElementById("autor").value = ArtigoRec.autor;
    document.getElementById("data").value = ArtigoRec.dataPublicacao;
    document.getElementById("dataultmod").innerHTML = ArtigoRec.dataRegistro;
    document.getElementById("palavraschave").value = ArtigoRec.palavrasChave;
    document.getElementById("subtitulo01").value = ArtigoRec.subTitulo01;
    document.getElementById("subtitulo02").value = ArtigoRec.subTitulo02;
    document.getElementById("subtitulo03").value = ArtigoRec.subTitulo03;
    document.getElementById("subtitulo04").value = ArtigoRec.subTitulo04;
    document.getElementById("subtitulo05").value = ArtigoRec.subTitulo05;

    let areaDeTexto = document.getElementById("at1");
    areaDeTexto.value = ArtigoRec.conteudo;
    autoGrow(areaDeTexto);

    EscreveTexto("Recebido o resultado da pesquisa");
}

//*********************************************************************************************************************
// Nome da função: PesquisaTitulo                                                                                     *
//                                                                                                                    *
// Função: envia as informações digitadas para o servidor para pesquisa no banco de dados sobre um artigo             *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function PesquisaTitulo() {

    CarregaVariaveisFormulario();

    if (Artigo.titulo == "") {
        EscreveTexto("Entre com pelo menos um nome do título")
    }
    else {
        let requisicao = new XMLHttpRequest();
        let recurso = "/isis/buscatitulo/" + Artigo.titulo;
        requisicao.open("GET", recurso, true);
        requisicao.timeout = 2000;
        requisicao.send(null);

        requisicao.onload = function() {
          let statusHTTP = requisicao.status;

            if ((statusHTTP >= 200) && (statusHTTP <= 299)) {
                CarregaDadosArtigos(requisicao);
                console.log("Indice = " + Indice);
                MostraDadosArtigos(Indice);
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
// Nome da função: PesquisaSubTitulo                                                                                  *
//                                                                                                                    *
// Função: envia as informações digitadas para o servidor para pesquisa no banco de dados sobre um artigo             *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function PesquisaSubTitulo() {

    CarregaVariaveisFormulario();

    if (Artigo.subTitulo01 == "") {
        EscreveTexto("Entre com pelo menos uma palavra chave")
    }
    else {
        let requisicao = new XMLHttpRequest();
        let recurso = "/isis/buscasubtitulo/" + Artigo.subTitulo01;
        requisicao.open("GET", recurso, true);
        requisicao.timeout = 2000;
        requisicao.send(null);

        requisicao.onload = function() {
            let statusHTTP = requisicao.status;

            if ((statusHTTP >= 200) && (statusHTTP <= 299)) {
                CarregaDadosArtigos(requisicao);
                MostraDadosArtigos(Indice);
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
// Nome da função: CarregaDadosArtigos                                                                                *
//                                                                                                                    *
// Função: faz o parsing do arquivo XML recebido do servidor, lê as informações de uma lista de artigos e carrega     *
//         nas variáveis de um artigo                                                                                 *
//                                                                                                                    *
// Entrada: mensagem Json recebida do servidor e o índice do artigo na mensagem                                       *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CarregaDadosArtigos(requisicao) {

    let dadosJson = JSON.parse(requisicao.responseText);
    NumResultados = dadosJson.length;

    for (let i = 0; i < NumResultados; i++) {
        ArrayId[i] = dadosJson[i].id;
        ArrayTitulo[i] = dadosJson[i].titulo;
        ArrayAutor[i] = dadosJson[i].autor;
        ArrayDataPublicacao[i] = dadosJson[i].dataPublicacao;
        ArrayDataRegistro[i] = dadosJson[i].dataRegistro;
        ArrayPalavrasChave[i] = dadosJson[i].palavrasChave;
        ArraySubTitulo01[i] = dadosJson[i].subTitulo01;
        ArraySubTitulo02[i] = dadosJson[i].subTitulo02;
        ArraySubTitulo03[i] = dadosJson[i].subTitulo03;
        ArraySubTitulo04[i] = dadosJson[i].subTitulo04;
        ArraySubTitulo05[i] = dadosJson[i].subTitulo05;
        ArrayNomeArquivo[i] = dadosJson[i].nomeArquivo;
    }
    Indice = 0;

    if (NumResultados > 1) {
        EscreveTexto("Recebidos " + NumResultados + " resultados. Selecionado resultado " + (Indice + 1));
    }
    else {
        EscreveTexto("Recebido o resultado da pesquisa");
    }
}

//*********************************************************************************************************************
// Nome da função: MostraDadosArtigos                                                                                 *
//                                                                                                                    *
// Função: faz o parsing do arquivo XML recebido do servidor, lê as informações dos artigos e carrega cada objeto     *
//         artigo no array de artigos                                                                                 *
//                                                                                                                    *
// Entrada: índice do objeto artigo recebido na mensagem enviada pelo servidor                                        *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function MostraDadosArtigos(indice) {

    document.getElementById("identificador").value = ArrayId[indice];
    document.getElementById("titulo").value = ArrayTitulo[indice];
    document.getElementById("autor").value = ArrayAutor[indice];
    document.getElementById("data").value = ArrayDataPublicacao[indice];
    document.getElementById("dataultmod").innerHTML = ArrayDataRegistro[indice];
    document.getElementById("palavraschave").value = ArrayPalavrasChave[indice];
    document.getElementById("subtitulo01").value = ArraySubTitulo01[indice];
    document.getElementById("subtitulo02").value = ArraySubTitulo02[indice];
    document.getElementById("subtitulo03").value = ArraySubTitulo03[indice];
    document.getElementById("subtitulo04").value = ArraySubTitulo04[indice];
    document.getElementById("subtitulo05").value = ArraySubTitulo05[indice];

    //let areaDeTexto = document.getElementById("at1");
    //areaDeTexto.value = ArtigoRec.conteudo;
    //autoGrow(areaDeTexto);
}

function ProximoResultado() {

    if (Indice < (NumResultados - 1)) {
        Indice = Indice + 1;
        MostraDadosArtigos(Indice);
        EscreveTexto("Recebidos " + NumResultados + " resultados. Selecionado resultado " + (Indice + 1));
    }

}

function ResultadoAnterior() {

    if (Indice > 0) {
        Indice = Indice - 1;
        MostraDadosArtigos(Indice);
        EscreveTexto("Recebidos " + NumResultados + " resultados. Selecionado resultado " + (Indice + 1));
    }

}

//*********************************************************************************************************************
// Nome da função: CadastraArtigo                                                                                     *
//                                                                                                                    *
// Função: efetua o cadastro de um artigo.                                                                            *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
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
                document.getElementById("identificador").value = ArtigoRec.id;
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
// Nome da função: LimpaFormulario                                                                                    *
//                                                                                                                    *
// Função: limpa todos os campos do formulário de entrada de dados                                                    *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function LimpaFormulario() {

    document.getElementById("identificador").value = "";
    document.getElementById("titulo").value = "";
    document.getElementById("autor").value = "";
    document.getElementById("data").value = "";
    document.getElementById("dataultmod").innerHTML = "";
    document.getElementById("palavraschave").value = "";
    document.getElementById("subtitulo01").value = "";
    document.getElementById("subtitulo02").value = "";
    document.getElementById("subtitulo03").value = "";
    document.getElementById("subtitulo04").value = "";
    document.getElementById("subtitulo05").value = "";

    let areaDeTexto = document.getElementById("at1");
    areaDeTexto.value = "";
    autoGrow(areaDeTexto);

}

//*********************************************************************************************************************
// Nome da função: AtualizaArtigo                                                                                     *
//                                                                                                                    *
// Função: efetua a atualização de um artigo.                                                                         *
//                                                                                                                    *
// Entrada: não tem                                                                                                   *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function AtualizaArtigo() {

    if (confirm("Confirma a atualização do artigo " + Artigo.titulo + "?")) {

        CarregaVariaveisFormulario();

        let requisicao = new XMLHttpRequest();
        let recurso = "/isis/atualiza/" + Artigo.id;
        requisicao.open("PUT", recurso, true);
        requisicao.setRequestHeader("Content-Type", "application/json;charset=utf-8");
        requisicao.timeout = 2000;

        requisicao.send(JSON.stringify(Artigo));

        requisicao.onload = function() {
            let statusHTTP = requisicao.status;

            if ((statusHTTP >= 200) && (statusHTTP <= 299)) {
                CarregaDadosArtigo(requisicao);
                EscreveTexto("Servidor: o artigo foi atualizado");
            }
            else {
                EscreveTexto("Servidor: falha ao atualizar o artigo");
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

function EscreveTexto(texto) {
    document.getElementById("info").innerHTML = " => " + texto;
}
