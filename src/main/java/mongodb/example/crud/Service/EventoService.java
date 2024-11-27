package mongodb.example.crud.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mongodb.example.crud.Modelo.Evento;
import mongodb.example.crud.Modelo.Participante;
import mongodb.example.crud.Repository.EventoRepository;

@Service
public class EventoService {
   
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteService participanteService;

    public Evento criar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(String id) {
        return eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public Evento atualizar(String id, Evento eventoAtualizado) {
        Evento existente = buscarPorId(id);
        existente.setNome(eventoAtualizado.getNome());
        existente.setDescricao(eventoAtualizado.getDescricao());
        return eventoRepository.save(existente);
    }

    public void deletar(String id) {
        eventoRepository.deleteById(id);
    }

    public Evento associarParticipante(String eventoId, String participanteId) {
        Evento evento = buscarPorId(eventoId);
        participanteService.buscarPorId(participanteId); // Verifica se o participante existe
        if (!evento.getParticipantesIds().contains(participanteId)) {
            evento.getParticipantesIds().add(participanteId);
            return eventoRepository.save(evento);
        }
        return evento;
    }

    public Evento listarComParticipantes(String id) {
    Evento evento = buscarPorId(id);  // Busca o evento pelo ID

    // Obtém os participantes com base nos IDs dos participantes
    List<Participante> participantes = evento.getParticipantesIds().stream()
            .map(participanteService::buscarPorId)  // Busca cada participante pelo ID
            .collect(Collectors.toList());

    // Preenche a lista de participantes com os dados completos (nome, email, etc)
    evento.setParticipantes(participantes);  // Atualiza a lista de participantes no evento
    
    return evento;  // Retorna o evento com participantes completos
}

}