package mongodb.example.crud.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import mongodb.example.crud.Modelo.Participante;

public interface ParticipanteRepository extends MongoRepository<Participante, ObjectId> {

}
