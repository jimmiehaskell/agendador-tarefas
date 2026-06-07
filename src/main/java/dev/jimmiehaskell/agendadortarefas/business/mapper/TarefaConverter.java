package dev.jimmiehaskell.agendadortarefas.business.mapper;

import dev.jimmiehaskell.agendadortarefas.business.dto.TarefaDTO;
import dev.jimmiehaskell.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    TarefaEntity fromTarefaEntity(TarefaDTO dto);

    TarefaDTO fromTarefaDTO(TarefaEntity entity);
}
