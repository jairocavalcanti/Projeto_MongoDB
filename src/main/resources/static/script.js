const apiBase = 'http://localhost:8080'; // Base da API

// Função para listar eventos
async function listarEventos() {
    const response = await fetch(`${apiBase}/eventos`);
    const eventos = await response.json();

    const listaEventos = document.getElementById('listaEventos');
    listaEventos.innerHTML = eventos.map(evento => 
        `<div class="evento-item">
            <h3>${evento.nome} - ${evento.descricao}</h3>
            <p><strong>ID:</strong> ${evento.id}</p>
           </p>
        </div>`
    ).join('');
}


// Função para listar participantes
async function listarParticipantes() {
    const response = await fetch(`${apiBase}/participantes`);
    const participantes = await response.json();

    const listaParticipantes = document.getElementById('listaParticipantes');
    listaParticipantes.innerHTML = participantes.map(participante => 
        `<div class="participante-item">
            <h3>${participante.nome} - ${participante.email}</h3>
            <p><strong>ID:</strong> ${participante.id ? participante.id.toString() : 'N/A'}</p>
        </div>`
    ).join('');
}

// Função para adicionar um evento
async function adicionarEvento(event) {
    event.preventDefault();

    const nome = document.getElementById('eventoNome').value;
    const descricao = document.getElementById('eventoDescricao').value;

    const response = await fetch(`${apiBase}/eventos`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, descricao })
    });

    if (response.ok) {
        listarEventos(); // Recarregar eventos
    }
}

// Função para editar um evento
async function editarEvento(event) {
    event.preventDefault();

    const id = document.getElementById('eventoIdEditar').value;
    const nome = document.getElementById('eventoNomeEditar').value;
    const descricao = document.getElementById('eventoDescricaoEditar').value;

    const response = await fetch(`${apiBase}/eventos/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, descricao })
    });

    if (response.ok) {
        listarEventos(); // Recarregar eventos
    }
}

// Função para excluir um evento
async function excluirEvento(event) {
    event.preventDefault();

    const id = document.getElementById('eventoIdDeletar').value;

    const response = await fetch(`${apiBase}/eventos/${id}`, {
        method: 'DELETE',
    });

    if (response.ok) {
        listarEventos(); // Recarregar eventos
    }
}

// Função para adicionar um participante
async function adicionarParticipante(event) {
    event.preventDefault();

    const nome = document.getElementById('nomeParticipante').value;
    const email = document.getElementById('emailParticipante').value;

    const response = await fetch(`${apiBase}/participantes`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, email })
    });

    if (response.ok) {
        listarParticipantes(); // Recarregar participantes
    }
}

// Função para editar um participante
async function editarParticipante(event) {
    event.preventDefault();

    const id = document.getElementById('participanteIdEditar').value;
    const nome = document.getElementById('participanteNomeEditar').value;
    const email = document.getElementById('participanteEmailEditar').value;

    const response = await fetch(`${apiBase}/participantes/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, email })
    });

    if (response.ok) {
        listarParticipantes(); // Recarregar participantes
    }
}

// Função para excluir um participante
async function excluirParticipante(event) {
    event.preventDefault();

    const id = document.getElementById('participanteIdDeletar').value;

    const response = await fetch(`${apiBase}/participantes/${id}`, {
        method: 'DELETE',
    });

    if (response.ok) {
        listarParticipantes(); // Recarregar participantes
    }
}

// Função para associar participante a evento
async function associarParticipanteAoEvento(event) {
    event.preventDefault(); // Previne o envio normal do formulário

    const eventoId = document.getElementById('eventoIdAssociar').value;
    const participanteId = document.getElementById('participanteIdAssociar').value;

    try {
        const response = await fetch(`${apiBase}/eventos/${eventoId}/participantes/${participanteId}`, {
            method: 'POST',
        });

        if (response.ok) {
            listarEventosComParticipantes(); // Recarregar eventos com participantes
        } else {
            // Mostrar uma mensagem de erro se a resposta não for bem-sucedida
            alert("Falha ao associar participante ao evento. Tente novamente.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Erro ao tentar associar o participante. Verifique a conexão e tente novamente.");
    }
}

// Adicionar um event listener para o envio do formulário
document.getElementById('formAssociarParticipante').addEventListener('submit', associarParticipanteAoEvento);

// Função para listar eventos com seus participantes associados
// Função para listar eventos com seus participantes associados
// Função para listar eventos com seus participantes associados
async function listarEventosComParticipantes() {
    const response = await fetch(`${apiBase}/eventos`);
    const eventos = await response.json();

    const eventosComParticipantes = await Promise.all(eventos.map(async (evento) => {
        const participantes = await Promise.all(evento.participantesIds.map(async (id) => {
            try {
                const res = await fetch(`${apiBase}/participantes/${id}`);
                if (!res.ok) throw new Error('Participante não encontrado');
                const participante = await res.json();
                return participante ? `${participante.nome} (${participante.id})` : null;
            } catch {
                return null; // Retorna null se o participante não for encontrado
            }
        }));

        // Filtra participantes válidos (remove nulls ou undefined)
        const participantesFiltrados = participantes.filter(p => p);

        return {
            nome: evento.nome,
            descricao: evento.descricao,
            participantes: participantesFiltrados
        };
    }));

    const listaEventosComParticipantes = document.getElementById('eventosComParticipantes');
    listaEventosComParticipantes.innerHTML = eventosComParticipantes.map(ep => 
        `<div class="evento-item">
            <h3>${ep.nome} - ${ep.descricao}</h3>
            <p><strong>Participantes:</strong> ${ep.participantes.join(', ') || 'Nenhum participante'}</p>
        </div>`
    ).join('');
}



// Inicializar o sistema e carregar dados
document.getElementById('formEvento').addEventListener('submit', adicionarEvento);
document.getElementById('formEditarEvento').addEventListener('submit', editarEvento);
document.getElementById('formDeletarEvento').addEventListener('submit', excluirEvento);
document.getElementById('formParticipante').addEventListener('submit', adicionarParticipante);
document.getElementById('formEditarParticipante').addEventListener('submit', editarParticipante);
document.getElementById('formDeletarParticipante').addEventListener('submit', excluirParticipante);
document.getElementById('formAssociarParticipante').addEventListener('submit', associarParticipanteAoEvento);

// Carregar as listas de eventos e participantes
listarEventos();
listarParticipantes();
listarEventosComParticipantes();
