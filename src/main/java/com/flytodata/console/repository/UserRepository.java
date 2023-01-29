package com.flytodata.console.repository;

import com.flytodata.console.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long user_id);
    User findUserByUserCode(String user_code);
    User findUserByEmail(String email);

    User save(User user);

}
