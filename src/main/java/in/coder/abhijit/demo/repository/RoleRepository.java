package in.coder.abhijit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.coder.abhijit.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
