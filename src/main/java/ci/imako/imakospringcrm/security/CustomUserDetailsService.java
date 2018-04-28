package ci.imako.imakospringcrm.security;

import ci.imako.imakospringcrm.domain.User;
import ci.imako.imakospringcrm.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Slf4j
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Email" + username + "n'existe pas dans" +
                            "notre base"));
        log.debug(user.getEmail());
        log.debug(user.getPassword());
        log.debug(user.getRoles().get(0).getNom());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAutorization(user)
        );
    }

    private static Collection<? extends GrantedAuthority> getAutorization(User user) {
        String[] userRoles = user.getRoles()
                                .stream().map((role) -> role.getNom())
                                .toArray(String[]::new);
        Collection<GrantedAuthority> autorizations = AuthorityUtils.createAuthorityList(userRoles);
        return autorizations;
    }
}
