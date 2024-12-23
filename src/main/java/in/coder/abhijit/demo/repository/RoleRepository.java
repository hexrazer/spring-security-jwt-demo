package in.coder.abhijit.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import in.coder.abhijit.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByNameIn(List<String> collect);

}
