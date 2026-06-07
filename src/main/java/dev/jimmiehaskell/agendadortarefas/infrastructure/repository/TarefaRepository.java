package dev.jimmiehaskell.agendadortarefas.infrastructure.repository;

import dev.jimmiehaskell.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {
}
