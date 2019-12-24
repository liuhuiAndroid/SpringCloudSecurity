package com.noc.security.repository;

import com.noc.security.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaSpecificationExecutor<User>,
        CrudRepository<User, Long> {

    List<User> findByName(String name);

}
