package mongodb.example.crud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mongodb.example.crud.Modelo.Participante;
import mongodb.example.crud.Service.ParticipanteService;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {
    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public Participante criar(@RequestBody Participante participante) {
        return participanteService.criar(participante);
    }

    @GetMapping
    public List<Participante> listarTodos() {
        return participanteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Participante buscarPorId(@PathVariable String id) {
        return participanteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Participante atualizar(@PathVariable String id, @RequestBody Participante participante) {
        return participanteService.atualizar(id, participante);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        participanteService.deletar(id);
    }
}