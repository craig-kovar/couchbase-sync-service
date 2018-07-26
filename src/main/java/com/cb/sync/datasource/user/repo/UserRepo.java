package com.cb.sync.datasource.user.repo;

import com.cb.sync.datasource.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
