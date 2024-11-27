package mongodb.example.crud.Modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "eventos")
public class Evento {
    @Id
    private String id;
    private String nome;
    private String descricao;
    private List<String> participantesIds = new ArrayList<>(); // Armazena IDs dos participantes
    private List<Participante> participantes = new ArrayList<>();
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<String> getParticipantesIds() {
        return participantesIds;
    }
    public void setParticipantesIds(List<String> participantesIds) {
        this.participantesIds = participantesIds;
    }
    public List<Participante> getParticipantes() {
        return participantes;
    }
    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    
    
}