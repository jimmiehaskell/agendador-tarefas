package dev.jimmiehaskell.agendadortarefas.infrastructure.repository;

import dev.jimmiehaskell.agendadortarefas.infrastructure.entity.TarefaEntity;
import dev.jimmiehaskell.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {
    List<TarefaEntity> findByDataEventoBetweenAndStatus(LocalDateTime dataInicial,
                                                        LocalDateTime dataFinal,
                                                        StatusNotificacaoEnum status);
    List<TarefaEntity> findByEmailUsuario(String email);
}
