// package com.prevalentWare.backend_PR.config;

// import java.io.IOException;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.token.TokenService;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;


// public class TokenValidationFilter extends OncePerRequestFilter {

//     private final TokenService tokenService; 

//     public TokenValidationFilter(TokenService tokenService) {
//         this.tokenService = tokenService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         final String authorizationHeader = request.getHeader("Authorization");

//         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//             final String token = authorizationHeader.substring(7); // Remueve el prefijo "Bearer "
//             if (tokenService.validateToken(token)) { // Validar el token
//                 // Si el token es válido, configura la autenticación en el contexto de seguridad
//                 Authentication authentication = tokenService.getAuthentication(token);
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//         }

//         filterChain.doFilter(request, response);
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
//     }
// }