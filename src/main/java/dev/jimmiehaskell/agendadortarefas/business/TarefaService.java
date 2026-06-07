package dev.jimmiehaskell.agendadortarefas.business;

import dev.jimmiehaskell.agendadortarefas.business.dto.TarefaDTO;
import dev.jimmiehaskell.agendadortarefas.business.mapper.TarefaConverter;
import dev.jimmiehaskell.agendadortarefas.infrastructure.entity.TarefaEntity;
import dev.jimmiehaskell.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import dev.jimmiehaskell.agendadortarefas.infrastructure.repository.TarefaRepository;
import dev.jimmiehaskell.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final JwtUtil jwtUtil;
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;

    public TarefaDTO gravarTarefa(String token, TarefaDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatus(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefaEntity entity = tarefaConverter.fromTarefaEntity(dto);
        return tarefaConverter.fromTarefaDTO(
            tarefaRepository.save(entity)
        );
    }
}
