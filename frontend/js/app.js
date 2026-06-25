const API = window.location.hostname === "localhost" && window.location.port !== "8080"
  ? "http://localhost:8080"
  : "";
const $ = (id) => document.getElementById(id);

const NOTAS = {
  RUIM: "Ruim",
  REGULAR: "Regular",
  BOM: "Bom",
  MUITO_BOM: "Muito bom",
  EXCELENTE: "Excelente"
};

function msg(el, texto, tipo) {
  el.textContent = texto;
  el.className = "msg" + (tipo ? " " + tipo : "");
}

function imgFallback(src) {
  return src || "data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='120' height='180' fill='%23ddd'%3E%3Crect width='120' height='180'/%3E%3Ctext x='50%25' y='50%25' text-anchor='middle' dy='.3em' fill='%23999' font-size='12'%3ESem capa%3C/text%3E%3C/svg%3E";
}

async function api(path, opts) {
  const res = await fetch(API + path, opts);
  if (!res.ok) {
    let texto = "Erro " + res.status;
    try {
      const body = await res.json();
      if (body.mensagem) texto = body.mensagem;
    } catch (_) {}
    throw new Error(texto);
  }
  return res.status === 204 ? null : res.json();
}

function renderLivros(livros) {
  const container = $("livros");
  if (!livros.length) {
    container.innerHTML = '<p class="vazio">Nenhum livro encontrado.</p>';
    return;
  }
  container.innerHTML = livros.map((l) =>
    `<article class="livro-card" data-id="${l.googleVolumeId}">
      <img src="${imgFallback(l.img)}" alt="">
      <div class="info">
        <h3>${l.titulo || "Sem título"}</h3>
        <p>${l.autor || "Autor desconhecido"}</p>
      </div>
    </article>`
  ).join("");

  container.querySelectorAll(".livro-card").forEach((card, i) => {
    card.onclick = () => selecionarLivro(livros[i]);
  });
}

function selecionarLivro(livro) {
  $("googleVolumeId").value = livro.googleVolumeId;
  $("titulo").value = livro.titulo || "";
  $("autor").value = livro.autor || "";
  $("img").value = livro.img || "";
  $("livro-selecionado").innerHTML =
    `<img src="${imgFallback(livro.img)}" alt="">
     <div>
       <h3>${livro.titulo || "Sem título"}</h3>
       <p>${livro.autor || "Autor desconhecido"}</p>
     </div>`;
  $("secao-indicar").hidden = false;
  msg($("msg-indicacao"), "");
  $("secao-indicar").scrollIntoView({ behavior: "smooth" });
}

function renderIndicacoes(lista) {
  const container = $("indicacoes");
  if (!lista.length) {
    container.innerHTML = '<p class="vazio">Nenhuma indicação ainda.</p>';
    return;
  }
  container.innerHTML = lista.map((i) =>
    `<article class="indicacao-item">
      <img src="${imgFallback(i.img)}" alt="">
      <div class="conteudo">
        <h3>${i.titulo}</h3>
        <p class="meta">${i.autor} · por <strong>${i.nome}</strong></p>
        <p class="meta"><span class="nota">${NOTAS[i.nota] || i.nota}</span> ${i.criadoEm ? new Date(i.criadoEm).toLocaleString("pt-BR") : ""}</p>
        <blockquote>${i.motivo}</blockquote>
      </div>
    </article>`
  ).join("");
}

async function buscarLivros(e) {
  e.preventDefault();
  const termo = $("termo").value.trim();
  const tipo = document.querySelector('input[name="tipo"]:checked').value;
  const param = tipo === "titulo" ? "titulo" : "autor";
  msg($("msg-busca"), "Buscando...");
  try {
    const livros = await api("/livros?" + param + "=" + encodeURIComponent(termo));
    msg($("msg-busca"), livros.length + " livro(s) encontrado(s).", "ok");
    renderLivros(livros);
  } catch (err) {
    const offline = err.message === "Failed to fetch";
    msg($("msg-busca"), offline ? "API offline. Rode ./gradlew bootRun." : err.message, "erro");
    $("livros").innerHTML = "";
  }
}

async function salvarIndicacao(e) {
  e.preventDefault();
  msg($("msg-indicacao"), "Salvando...");
  const body = {
    googleVolumeId: $("googleVolumeId").value,
    titulo: $("titulo").value,
    autor: $("autor").value,
    img: $("img").value,
    nome: $("nome").value.trim(),
    nota: parseInt($("nota").value, 10),
    motivo: $("motivo").value.trim()
  };
  try {
    await api("/indicacoes", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body)
    });
    msg($("msg-indicacao"), "Indicação salva com sucesso!", "ok");
    $("form-indicacao").reset();
    $("secao-indicar").hidden = true;
    carregarIndicacoes();
  } catch {
    msg($("msg-indicacao"), "Erro ao salvar. Verifique os dados.", "erro");
  }
}

async function carregarIndicacoes() {
  msg($("msg-lista"), "Carregando...");
  try {
    const lista = await api("/indicacoes");
    msg($("msg-lista"), "");
    renderIndicacoes(lista);
  } catch (err) {
    const offline = err.message === "Failed to fetch";
    msg($("msg-lista"), offline ? "API offline. Rode ./gradlew bootRun." : err.message, "erro");
  }
}

$("form-busca").onsubmit = buscarLivros;
$("form-indicacao").onsubmit = salvarIndicacao;
$("btn-atualizar").onclick = carregarIndicacoes;
carregarIndicacoes();
