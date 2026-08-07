document.addEventListener('DOMContentLoaded', () => {
    const btnOpenModal = document.getElementById('btnOpenModal');
    const btnCloseModal = document.getElementById('btnCloseModal');
    const btnCancelModal = document.getElementById('btnCancelModal');
    const modalOverlay = document.getElementById('modalOverlay');
    const addBookForm = document.getElementById('addBookForm');
    const booksGrid = document.getElementById('booksGrid');
    const bookCount = document.getElementById('bookCount');

    let totalBooks = 4;

    // Abrir Modal
    btnOpenModal.addEventListener('click', () => {
        modalOverlay.classList.add('active');
    });

    // Fechar Modal
    const closeModal = () => {
        modalOverlay.classList.remove('active');
        addBookForm.reset();
    };

    btnCloseModal.addEventListener('click', closeModal);
    btnCancelModal.addEventListener('click', closeModal);

    // Fechar ao clicar no overlay escuro
    modalOverlay.addEventListener('click', (e) => {
        if (e.target === modalOverlay) {
            closeModal();
        }
    });

    // Submeter Formulário e Adicionar à Lista
    addBookForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const titulo = document.getElementById('titulo').value;
        const autor = document.getElementById('autor').value;
        const genero = document.getElementById('genero').value;
        const status = document.getElementById('status').value;
        const avaliacao = document.getElementById('avaliacao').value || 'N/A';

        let statusClass = 'lido';
        if (status === 'Lendo') statusClass = 'lendo';
        if (status === 'Quero Ler') statusClass = 'quero-ler';

        const newCard = document.createElement('article');
        newCard.className = 'book-card';
        
        const coverClasses = ['cover-1', 'cover-2', 'cover-3', 'cover-4'];
        const randomCover = coverClasses[Math.floor(Math.random() * coverClasses.length)];

        newCard.innerHTML = `
            <div class="book-cover ${randomCover}">
                <span class="status-badge ${statusClass}">${status}</span>
            </div>
            <div class="book-info">
                <span class="book-genre">${genero}</span>
                <h3 class="book-title">${titulo}</h3>
                <p class="book-author">${autor}</p>
                <div class="book-footer">
                    <span class="rating">★ ${avaliacao}</span>
                    <button class="btn-detalhes">Detalhes</button>
                </div>
            </div>
        `;

        booksGrid.prepend(newCard);

        totalBooks++;
        bookCount.textContent = `${totalBooks} Livros`;

        closeModal();
    });
});














const livros = []






async function listarItens() {
  try {
    const response = await fetch("http://localhost:8080/itens");

    if (!response.ok) {
      throw new Error("Erro ao buscar itens");
    }

    const itens = await response.json();
    console.log(itens);
    livros.length = 0;
    livros.push(...itens);
  } catch (error) {
    console.error(error);
  }
}


const booksGrid = document.querySelector(".books-grid");
function renderizarItens() {
    livros.forEach((livro)=>{
        const card = document.createElement("article");
        card.classList.add("book-card");
        card.innerHTML = `
            <div class="book-cover cover-1">
                <span class="status-badge lido">Lido</span>
            </div>
            <div class="book-info">
                <span class="book-genre">${livro.categoria}</span>
                <h3 class="book-title">${livro.titulo}</h3>
                <p class="book-author">${livro.autor}</p>
                <div class="book-footer">
                    <span class="rating">★ ${livro.classificacaoIndicativa}</span>
                    <button class="btn-detalhes">Detalhes</button>
                </div>
            </div>
        `;
        booksGrid.appendChild(card);
    })
}

const idListar = document.getElementById("btn-listar");
idListar.addEventListener("click", async () => {
    await listarItens();
    renderizarItens();
});














const form = document.getElementById("addBookForm");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const item = {
        titulo: document.getElementById("titulo").value,
        autor: document.getElementById("autor").value,
        isbn: document.getElementById("isbn").value,
        editora: document.getElementById("editora").value,
        categoria: document.getElementById("categoria").value,
        tipo: document.getElementById("tipo").value,
        classificacaoIndicativa: document.getElementById("classificacaoIndicativa").value,
        anoPublicacao: Number(document.getElementById("anoPublicacao").value),
        quantidadeDisponivel: Number(document.getElementById("quantidadeDisponivel").value)
    };

    try {
        const response = await fetch("http://localhost:8080/itens", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(item)
        });

        if (!response.ok) {
            throw new Error(await response.text());
        }

        const data = await response.json();

        alert("Item cadastrado com sucesso!");
        console.log(data);

        form.reset();

    } catch (erro) {
        console.error(erro);
        alert("Erro ao cadastrar o item.");
    }
});