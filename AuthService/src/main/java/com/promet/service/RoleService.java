package com.promet.service;


import com.promet.repository.IRoleRepository;
import com.promet.repository.entity.Role;
import com.promet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceManager<Role, Long> {
    private final IRoleRepository roleRepository;
    public RoleService(IRoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    public String getRoleNames(Long roleId){
        System.out.println(findById(roleId).get());
        System.out.println(findById(roleId).get().getRoleName());
       return findById(roleId).get().getRoleName();
    }
}
