// package com.prevalentWare.backend_PR.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;

// import com.prevalentWare.backend_PR.entities.Role;
// import com.prevalentWare.backend_PR.entities.Role.Enum_RoleName;
// import com.prevalentWare.backend_PR.entities.Session;
// import com.prevalentWare.backend_PR.entities.User;
// import com.prevalentWare.backend_PR.repositories.contracts.IRoleRepository;
// import com.prevalentWare.backend_PR.repositories.contracts.ISessionRepository;
// import com.prevalentWare.backend_PR.repositories.contracts.IUserRepository;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class AuthorizationInterceptor implements HandlerInterceptor {

//     @Autowired
//     private ISessionRepository sessionRepository;

//     @Autowired
//     private IUserRepository userRepository;

//     @Autowired
//     private IRoleRepository roleRepository;

//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         String token = request.getHeader("Authorization");

//         Session session = sessionRepository.findBySessionToken(token);
//         if (session == null) {
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             return false;
//         }

//         // Obtener el userId asociado al token
//         User userId = session.getUser();

//         // Buscar el roleId en la tabla "User"
//         User user = userRepository.findById(userId.getId()).orElse(null);
//         if (user == null) {
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             return false;
//         }

//         String roleId = user.getRoleId();
//         Role role = roleRepository.findById(roleId).orElse(null);
//         if (role == null) {
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             return false;
//         }

//         // Verificar si el rol tiene los permisos necesarios
//         // if (role.getName()==Role.Enum_RoleName.Admin) {
//         //     response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//         //     return false;
//         // }
//         if (role.getName() != null) {
//             if (role.getName() == Enum_RoleName.Admin) {
//                 response.setStatus(HttpServletResponse.SC_FORBIDDEN);

//                 Role.Enum_RoleName rolename=role.getName();
//                 return true;
//             }else if (role.getName() == Enum_RoleName.Manager) {
//                 response.setStatus(HttpServletResponse.SC_FORBIDDEN);

//                 Role.Enum_RoleName rolename=role.getName();
//                 return true;
//             }else if (role.getName() == Enum_RoleName.User) {
//                 response.setStatus(HttpServletResponse.SC_FORBIDDEN);

//                 Role.Enum_RoleName rolename=role.getName();
//                 return true;
//             }
//         }

//         return false;
//     }
// }
