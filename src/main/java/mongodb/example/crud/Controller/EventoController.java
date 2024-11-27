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

import mongodb.example.crud.Modelo.Evento;
import mongodb.example.crud.Service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @PostMapping
    public Evento criar(@RequestBody Evento evento) {
        return eventoService.criar(evento);
    }

    @GetMapping
    public List<Evento> listarTodos() {
        return eventoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Evento buscarPorId(@PathVariable String id) {
        return eventoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Evento atualizar(@PathVariable String id, @RequestBody Evento evento) {
        return eventoService.atualizar(id, evento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        eventoService.deletar(id);
    }

    @PostMapping("/{eventoId}/participantes/{participanteId}")
    public Evento associarParticipante(@PathVariable String eventoId, @PathVariable String participanteId) {
        return eventoService.associarParticipante(eventoId, participanteId);
    }

    @GetMapping("/{id}/com-participantes")
    public Evento listarComParticipantes(@PathVariable String id) {
        return eventoService.listarComParticipantes(id);
    }
}