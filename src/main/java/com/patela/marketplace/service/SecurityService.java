package com.patela.marketplace.service;

import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.exception.ServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SecurityService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;


    /**
     * Check if parameter is email or username.
     *
     * @param value value. {@link String}
     * @return user by username or email {@link User}
     */
     User loadUser(String value);


}
