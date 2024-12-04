package com.aes.eventmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import com.aes.eventmanagementsystem.dto.UserDto;
import com.aes.eventmanagementsystem.model.Event;
import com.aes.eventmanagementsystem.model.User;

public interface IUserService {

    /**
     * Save user into db
     * 
     * @param user
     * @return boolean
     */
    boolean createUser(User user);

    /**
     * Fetch User by email
     * 
     * @param email
     * @return UserDto
     */
    UserDto fetchUser(String email);

    /**
     * Fetch Optional user for null check by email
     * 
     * @param email
     * @return Optional Email
     */
    Optional<User> readUser(String email);

    /**
     * Update user
     * 
     * @param userDto
     * @return boolean
     */
    boolean updateUser(UserDto userDto);

    /**
     * Update user
     * 
     * @param user
     */
    void updateUser(User user);

    /**
     * Delete user by email
     * 
     * @param email
     * @return boolean
     */
    boolean deleteUser(String email);

    /**
     * Check user is admin or not
     * 
     * @return boolean
     */
    boolean isAdmin();

    /**
     * Find events that participated by userId
     * 
     * @param id
     * @return Event List
     */
    List<Event> findEventsByUserId(int id);

}
