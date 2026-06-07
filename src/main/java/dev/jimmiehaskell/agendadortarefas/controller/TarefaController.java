package dev.jimmiehaskell.agendadortarefas.controller;

import dev.jimmiehaskell.agendadortarefas.business.TarefaService;
import dev.jimmiehaskell.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
