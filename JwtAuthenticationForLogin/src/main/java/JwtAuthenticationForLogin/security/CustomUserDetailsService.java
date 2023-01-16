package JwtAuthenticationForLogin.security;

import JwtAuthenticationForLogin.Repository.UserRepo;
import JwtAuthenticationForLogin.model.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String emailid) throws UsernameNotFoundException {

        JwtAuthenticationForLogin.model.User user = userRepo.findByemailid(emailid).orElseThrow(() -> new UsernameNotFoundException("user is not found" + emailid));
        return new org.springframework.security.core.userdetails.User(user.getEmailid(), user.getPassword(), mapRolesToAuthority(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Set<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if(username.equals("shaik")) {
//            return new User("shaik","shaik123",new ArrayList<>());
//        }else {
//            throw new UsernameNotFoundException("user not found");
//        }
//    }


}
