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

const Artigo = { id: 0,
                 titulo: "",
                 autor: "",
                 data: "",
                 nomeArquivo: "",
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
                    nomeArquivo: "",
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
    Artigo.data = Data.value;
    Artigo.subTitulo01 = SubTitulo01.value;
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

    if (Artigo.titulo == "") {
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
                let dadosJson = JSON.parse(requisicao.responseText);
                CarregaDadosArtigo(dadosJson);
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
// Nome da função: CarregaDadosArtigo                                                                                 *
//                                                                                                                    *
// Função: faz o parsing do arquivo XML recebido do servidor, lê as informações de um artigo e carrega nas variáveis  *
//                                                                                                                    *
// Entrada: mensagem Json recebida do servidor e o índice do artigo na mensagem                                       *
// Saída: não tem                                                                                                     *
//*********************************************************************************************************************
//
function CarregaDadosArtigo(dadosJson) {

    ArtigoRec.id = dadosJson.id;
    ArtigoRec.titulo = dadosJson.titulo;
    ArtigoRec.autor = dadosJson.autor;
    ArtigoRec.data = dadosJson.data;
    ArtigoRec.nomeArquivo = dadosJson.nomeArquivo;
    ArtigoRec.subTitulo01 = dadosJson.subTitulo01;
    ArtigoRec.conteudo = dadosJson.conteudo;

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
function CarregaDadosArtigos(dadosJson, indice) {

    ArtigoRec.id = dadosJson[indice].id;
    ArtigoRec.titulo = dadosJson[indice].titulo;
    ArtigoRec.autor = dadosJson[indice].autor;
    ArtigoRec.data = dadosJson[indice].data;
    ArtigoRec.subTitulo01 = dadosJson[indice].subTitulo01;
    ArtigoRec.conteudo = "";
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
function MostraDadosArtigo(numResultados) {

    document.getElementById("identificador").value = ArtigoRec.id;
    document.getElementById("titulo").value = ArtigoRec.titulo;
    document.getElementById("autor").value = ArtigoRec.autor;
    document.getElementById("data").value = ArtigoRec.data;
    document.getElementById("subtitulo01").value = ArtigoRec.subTitulo01;

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
    document.getElementById("subtitulo01").value = "";

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

function EnviaArtigo() {
    let areaDeTexto = document.getElementById("at1");
    console.log(areaDeTexto.value);

}

function EscreveTexto(texto) {
    document.getElementById("info").innerHTML = " => " + texto;
}
