package com.computershop.Controller;

import com.computershop.Model.Entity.Role;
import com.computershop.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    ResponseEntity<Map> getList() {
        Map res = new HashMap();
//        res.put("total",)
        res.put("List",roleService.list());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @PostMapping
    ResponseEntity<Role> create(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.create(role));
    }

    @PutMapping("/{id}")
    ResponseEntity<Role> update(@PathVariable("id") Long id, @RequestBody Role role) {
        return ResponseEntity.ok(roleService.update(id, role));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
