package compeng.arved.service;

import compeng.arved.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAll();
    Optional<Role> findOne(Long id);
}
