package com.yash.FMS.service.impl;

import com.yash.FMS.dao.AdminDAO;
import com.yash.FMS.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * AdminDetailsServiceImpl: Loads Admin details for Spring Security.
 */
@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminDAO adminDAO;

    /**
     * Loads user details by username.
     * @param username The username to load.
     * @return UserDetails object.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminDAO.findByUsername(username);

        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                admin.getUsername(),
                admin.getPassword(),
                new ArrayList<>()
        );
    }
}