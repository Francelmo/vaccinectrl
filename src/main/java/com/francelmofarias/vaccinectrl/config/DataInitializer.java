package com.francelmofarias.vaccinectrl.config;

import com.francelmofarias.vaccinectrl.model.Role;
import com.francelmofarias.vaccinectrl.model.RoleName;
import com.francelmofarias.vaccinectrl.model.Usuario;
import com.francelmofarias.vaccinectrl.repository.RoleRepository;
import com.francelmofarias.vaccinectrl.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository, UsuarioRepository usuarioRepository) {
        return args -> {
            if (roleRepository.findAll().isEmpty()) {
                Role roleUser = new Role();
                roleUser.setName(RoleName.ROLE_USER);
                roleRepository.save(roleUser);

                Role roleGerente = new Role();
                roleGerente.setName(RoleName.ROLE_GERENTE);
                roleRepository.save(roleGerente);

                Role roleAdmin = new Role();
                roleAdmin.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(roleAdmin);
            }

            if (usuarioRepository.findAll().isEmpty()) {
                Set<Role> rolesAdmin = new HashSet<>();
                rolesAdmin.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(rolesAdmin);
                usuarioRepository.save(admin);

                Set<Role> rolesGerente = new HashSet<>();
                rolesGerente.add(roleRepository.findByName(RoleName.ROLE_GERENTE).get());

                Usuario gerente = new Usuario();
                gerente.setUsername("gerente");
                gerente.setPassword(passwordEncoder.encode("gerente123"));
                gerente.setRoles(rolesGerente);
                usuarioRepository.save(gerente);

                Set<Role> rolesUser = new HashSet<>();
                rolesUser.add(roleRepository.findByName(RoleName.ROLE_USER).get());

                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRoles(rolesUser);
                usuarioRepository.save(user);
            }
        };
    }
}

