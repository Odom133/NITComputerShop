package com.computershop.Service;

import com.computershop.Model.Entity.Role;
import com.computershop.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> list() {
        return roleRepository.findAll();
    }
    public Optional<Role> getById(Long id) {

        return roleRepository.findById(id);
    }

    public Role create(Role role) {

        return roleRepository.save(role);
    }

    public Role update(Long id, Role role) {
        Role isExistRole = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found!"));
        isExistRole.setName(role.getName());
        isExistRole.setDescription(role.getDescription());
        return roleRepository.save(isExistRole);
    }

    public void delete(Long id) {
        Role isExistRole = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found!"));
        roleRepository.delete(isExistRole);

    }
}
