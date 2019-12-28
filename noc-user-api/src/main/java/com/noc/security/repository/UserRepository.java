package com.noc.security.repository;

import com.noc.security.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * JpaSpecificationExecutor:完成多条件查询，并且支持分页与排序。
 */
public interface UserRepository extends JpaSpecificationExecutor<User>,
        CrudRepository<User, Long> {

    List<User> findByName(String name);

    User findByUsername(String username);

}
