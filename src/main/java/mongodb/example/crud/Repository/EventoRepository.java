package mongodb.example.crud.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mongodb.example.crud.Modelo.Evento;

public interface EventoRepository extends MongoRepository<Evento, String> {

}
