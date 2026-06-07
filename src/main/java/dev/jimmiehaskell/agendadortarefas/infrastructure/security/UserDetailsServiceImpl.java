package dev.jimmiehaskell.agendadortarefas.infrastructure.security;


import dev.jimmiehaskell.agendadortarefas.business.dto.UsuarioDTO;
import dev.jimmiehaskell.agendadortarefas.infrastructure.security.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {
    private final UsuarioClient usuarioClient;

    public UserDetails carregaDadosUsuario(String email, String token) {
        UsuarioDTO usuarioDTO = usuarioClient.buscaUsuarioPorEmail(email, token);
        return User
            .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
            .password(usuarioDTO.getSenha()) // Define a senha do usuário
            .build();
    }
}