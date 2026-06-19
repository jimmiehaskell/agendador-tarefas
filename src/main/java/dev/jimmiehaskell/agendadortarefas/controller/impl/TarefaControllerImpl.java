package dev.jimmiehaskell.agendadortarefas.controller.impl;

import dev.jimmiehaskell.agendadortarefas.business.TarefaService;
import dev.jimmiehaskell.agendadortarefas.business.dto.TarefaDTO;
import dev.jimmiehaskell.agendadortarefas.controller.TarefaController;
import dev.jimmiehaskell.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaControllerImpl implements TarefaController {
    private final TarefaService tarefaService;

    @Override
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO dto,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }

    @Override
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @Override
    public ResponseEntity<List<TarefaDTO>> buscaListaTarefasPorPeriodo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @Override
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id) {
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum status,
                                                             @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraTarefaStatus(status, id));
    }

    @Override
    public ResponseEntity<TarefaDTO> updateTarefa(@RequestBody TarefaDTO dto, @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateTarefa(dto, id));
    }
}
