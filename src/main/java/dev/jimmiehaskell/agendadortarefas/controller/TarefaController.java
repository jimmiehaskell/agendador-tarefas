package dev.jimmiehaskell.agendadortarefas.controller;

import dev.jimmiehaskell.agendadortarefas.business.TarefaService;
import dev.jimmiehaskell.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO dto,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaListaTarefasPorPeriodo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }
}
