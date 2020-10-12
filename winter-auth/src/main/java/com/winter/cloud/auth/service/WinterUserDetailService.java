package com.winter.cloud.auth.service;

import com.winter.cloud.common.core.entity.WinterAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WinterUserDetailService  implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return new User(username, this.passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }

}
