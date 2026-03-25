    package com.example.fitness_saas.security;

    import com.example.fitness_saas.repository.UserRepository;
    import com.example.fitness_saas.security.jwt.JwtUtils;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;
    //verifica quem tem o token e se ele é valido
    @Component
    public class SecurityFilter  extends OncePerRequestFilter {
        @Autowired
        JwtUtils jwtUtils;
        @Autowired
        UserRepository userRepository;

    //oncePerResquestFilter garante que o spring faça o filtro de token apenas uma vez em casa requisição
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            var token = this.recoverToken(request);// tenta achar o token na url
            if (token != null) {
                String email = jwtUtils.validateToken(token);// valida o token junto ao email
                if(!email.isEmpty()){ // se o email estiver vazio, faz a busca no banco
                    UserDetails user = userRepository.findByEmail(email);

                    if(user!=null){
                        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());//permite a entrada

                        SecurityContextHolder.getContext().setAuthentication(authentication);//usuario authenticado pode acessar as urls permitidas
                    } else {
                        System.out.println("Email not found" );
                    }



                }

            }

            filterChain.doFilter(request, response);
        }
        private String recoverToken(HttpServletRequest request) {
            var header = request.getHeader("Authorization");
            if (header == null) return null;

            return  header.replace("Bearer ", "");


        }
    }
