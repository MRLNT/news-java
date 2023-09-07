package id.fazzbca.news.services.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import id.fazzbca.news.models.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private Boolean isDeleted;

    public static UserDetails build(User user){
        /*
        * menyimpan role lebih dari 1.
        * bisa looping bagian role dan tambahkan ke list authorities
        * 
        * role hanya 1, bisa langsung ditambahkan rolenamenya
        */
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
        return new UserDetailsImpl(authorities, user.getPassword(), user.getEmail(), user.getIsDeleted());
        
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted;
    }
}