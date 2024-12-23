package in.coder.abhijit.demo.service;

import in.coder.abhijit.demo.repository.RoleRepository;

public class RoleService {
    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


}
