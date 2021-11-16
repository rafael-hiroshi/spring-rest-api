package br.com.alura.forum.config.security;

import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        boolean isValid = tokenService.isTokenValid(token);

        if (isValid) {
            authenticateUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }

    private void authenticateUser(String token) {
        Long id = tokenService.getUserId(token);
        User user = userRepository.findById(id).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
