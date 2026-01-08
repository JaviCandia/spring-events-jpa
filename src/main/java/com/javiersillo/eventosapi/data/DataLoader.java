package com.javiersillo.eventosapi.data;

import com.javiersillo.eventosapi.domain.Role;
import com.javiersillo.eventosapi.domain.User;
import com.javiersillo.eventosapi.repository.RoleRepository;
import com.javiersillo.eventosapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    // Esta es una clase seeder o inicializadora
    // Se ejecuta después de que se inicie la aplicación y se han creado los roles y usuarios en la base de datos

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 1. Fase de Roles
        Role adminRole = getOrCreateRole("ROLE_ADMIN");
        Role userRole  = getOrCreateRole("ROLE_USER");

        // 2. Fase de Usuarios
        createUserIfNotFound("Administrador","admin", "admin1324", Set.of(adminRole, userRole));
        createUserIfNotFound("Usuario normal","user",  "user1234",  Set.of(userRole));
    }

    private Role getOrCreateRole(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    return roleRepository.save(newRole);
                });
    }

    private void createUserIfNotFound(String name, String username, String password, Set<Role> roles) {
        if (userRepository.findByUsername(username).isPresent()) {
            return;
        }

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(username + "@example.com");
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);

        userRepository.save(user);
        System.out.println("Usuario creado: " + username);
    }
}