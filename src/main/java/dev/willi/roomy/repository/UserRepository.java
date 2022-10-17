package dev.willi.roomy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.willi.roomy.model.entity.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.enabled = TRUE WHERE u.email = ?1")
    int enableUser(String email);
	
}
