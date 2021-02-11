package com.patela.marketplace.serviceImpl;

import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.service.SecurityService;
import com.patela.marketplace.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;

    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User foundedUser = loadUser(s);

        if (foundedUser == null) {
            throw new UsernameNotFoundException("User not found! " + s);
        }

        return new org.springframework.security.core.userdetails.User(foundedUser.getUsername(), foundedUser.getPassword(), listAuthorities(foundedUser));
    }

    /**
     * Check if parameter is email or username.
     *
     * @param value value. {@link String}
     * @return user by username or email {@link User}
     */
    public User loadUser(String value) {
        return isEmailValid(value) ? userService.readByEmail(value) : userService.readByUsername(value);
    }

    /**
     * List authorities.
     *
     * @param user founded user {@link User}
     * @return list of grantedAuthority {@link List <  GrantedAuthority  >}
     */
    private List<GrantedAuthority> listAuthorities(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return grantedAuthorities;
    }

    private boolean isEmailValid(String email) {
        final String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
