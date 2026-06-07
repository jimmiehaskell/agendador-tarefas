package dev.jimmiehaskell.agendadortarefas.business.mapper;

import dev.jimmiehaskell.agendadortarefas.business.dto.TarefaDTO;
import dev.jimmiehaskell.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    TarefaEntity fromTarefaEntity(TarefaDTO dto);
    TarefaDTO fromTarefaDTO(TarefaEntity entity);
    List<TarefaEntity> fromListaTarefasEntity(List<TarefaDTO> dtos);
    List<TarefaDTO> fromListaTarefasDTO(List<TarefaEntity> entities);
}
