// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RestController;

// import com.prevalentWare.backend_PR.entities.Role;
// import com.prevalentWare.backend_PR.entities.User;
// import com.prevalentWare.backend_PR.repositories.contracts.IRoleRepository;
// import com.prevalentWare.backend_PR.repositories.contracts.ISessionRepository;
// import com.prevalentWare.backend_PR.repositories.contracts.IUserRepository;

// @RestController
// public class MyController {

//     @Autowired
//     private ISessionRepository sessionRepository;

//     @Autowired
//     private IUserRepository userRepository;

//     @Autowired 
//     private IRoleRepository roleRepository;

//     @GetMapping("/api/users")
//     public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token) {
//         // Verificar si el token existe y tiene el rol adecuado
//         if (!isAuthorized(token, "manager", "admin")) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//         }

//         // Obtener todos los usuarios
//         List<User> users = userRepository.findAll();

//         return ResponseEntity.ok(users);
//     }



//     private boolean isAuthorized(String token, String... allowedRoles) {
//         // Verificar si el token existe en la tabla "Session"
//         Session session = sessionRepository.findByToken(token);
//         if (session == null) {
//             return false;
//         }

//         // Obtener el userId asociado al token
//         Long userId = session.getUserId();

//         // Buscar el roleId en la tabla "User"
//         User user = userRepository.findById(userId).orElse(null);
//         if (user == null) {
//             return false;
//         }

//         // Obtener el rol del usuario
//         Long roleId = user.getRoleId();
//         Role role = roleRepository.findById(roleId).orElse(null);
//         if (role == null) {
//             return false;
//         }

//         // Verificar si el rol del usuario está permitido
//         for (String allowedRole : allowedRoles) {
//             if (role.getName().equalsIgnoreCase(allowedRole)) {
//                 return true;
//             }
//         }

//         return false;
//     }
// }
