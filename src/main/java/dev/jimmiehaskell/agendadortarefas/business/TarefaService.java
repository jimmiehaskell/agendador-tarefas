package dev.jimmiehaskell.agendadortarefas.business;

import dev.jimmiehaskell.agendadortarefas.business.dto.TarefaDTO;
import dev.jimmiehaskell.agendadortarefas.business.mapper.TarefaConverter;
import dev.jimmiehaskell.agendadortarefas.business.mapper.TarefaUpdateConverter;
import dev.jimmiehaskell.agendadortarefas.infrastructure.entity.TarefaEntity;
import dev.jimmiehaskell.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import dev.jimmiehaskell.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import dev.jimmiehaskell.agendadortarefas.infrastructure.repository.TarefaRepository;
import dev.jimmiehaskell.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final JwtUtil jwtUtil;
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final TarefaUpdateConverter tarefaUpdateConverter;

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

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.fromListaTarefasDTO(
            tarefaRepository.findByDataEventoBetweenAndStatus(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefaEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);
        return tarefaConverter.fromListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                "Erro ao deletar tarefa por id, id inexistente: " + id, e.getCause());
        }
    }

    public TarefaDTO alteraTarefaStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatus(status);
            return tarefaConverter.fromTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TarefaDTO updateTarefa(TarefaDTO dto, String id) {
        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.fromTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar tarefa id: " + id, e.getCause());
        }
    }
}
