package id.fazzbca.news.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.fazzbca.news.models.User;
import id.fazzbca.news.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    
    // username ini sebagai username pada saat login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // find by username untuk cari username sebagai username ada usernya atau tidak
        // kalau ada, kita build user details yang nantinya akan dibuat untuk security context holder
        // kalau tidak, kita throw user not found
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException(username + "is not found");
        }

        User user = userRepository.findByUsername(username);

        // validasi dari obj user atau creator atau admin mana yang ngga null
        // yang ngga null adalah admin -> role = "ROLE_ADMIN"
        return UserDetailsImpl.build(user);
    }
}