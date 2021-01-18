package com.patela.marketplace.service;


import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.exception.ServiceException;

import java.util.List;

public interface UserService {

    /**
     * Read user by username
     *
     * @param username username parameter {@link String}
     * @return founded user {@link User}
     */

    User readByUsername(String username);

    /**
     * Read user by email.
     *
     * @param email email parameter {@link String}
     * @return founded user {@link User}
     */
    User readByEmail(String email);

    /**
     * List all users.
     *
     * @return
     */
    List<User> getAll();


    /**
     * Create user.
     *
     * @param user user to be created {@link User}
     * @return new user {@link User}
     */
    User createUser(User user) throws ServiceException;


    /**
     * Verify user when email has been confirmed.
     *
     * @param user user to be verified. {@link User}
     * @return verified User. {@link User}
     */
    User verifyUser(User user);

    /**
     * Delete user
     *
     * @param id if of user
     * @throws ServiceException
     */

    void deleteUser(Integer id) throws ServiceException;

    /**
     * Reset password.
     *
     * @param user user to be reset {@link User}
     * @return updated user.
     * @throws ServiceException
     */
    User resetPassword(User user) throws ServiceException;

}
