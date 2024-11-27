package mongodb.example.crud.Service;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mongodb.example.crud.Modelo.Participante;
import mongodb.example.crud.Repository.ParticipanteRepository;

@Service
public class ParticipanteService {
    @Autowired
    private ParticipanteRepository participanteRepository;

    public Participante criar(Participante participante) {
        return participanteRepository.save(participante);
    }

    public List<Participante> listarTodos() {
        return participanteRepository.findAll();
    }

    public Participante buscarPorId(String id) {
        ObjectId objectId = new ObjectId(id); // Converte String para ObjectId
        return participanteRepository.findById(objectId)
                .orElseThrow(() -> new RuntimeException("Participante não encontrado"));
    }

    public Participante atualizar(String id, Participante participanteAtualizado) {
        Participante existente = buscarPorId(id);
        existente.setNome(participanteAtualizado.getNome());
        existente.setEmail(participanteAtualizado.getEmail());
        return participanteRepository.save(existente);
    }

    public void deletar(String id) {
        ObjectId objectId = new ObjectId(id); // Converte String para ObjectId
        participanteRepository.deleteById(objectId);
    }
}