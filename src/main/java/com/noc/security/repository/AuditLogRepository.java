package com.noc.security.repository;

import com.noc.security.log.AuditLog;
import com.noc.security.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuditLogRepository extends JpaSpecificationExecutor<AuditLog>,
        CrudRepository<AuditLog, Long> {

}
