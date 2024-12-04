package com.aes.eventmanagementsystem.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aes.eventmanagementsystem.model.Event;
import com.aes.eventmanagementsystem.model.Role;
import com.aes.eventmanagementsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    User readByEmail(String email);

    @Query("SELECT u.roles FROM User u WHERE u.userId = :userId")
    Set<Role> findRolesByUserId(@Param("userId") int userId);

    @Query("SELECT u.events FROM User u WHERE u.userId = :userId")
    List<Event> findEventsByUserId(@Param("userId") int userId);
}
