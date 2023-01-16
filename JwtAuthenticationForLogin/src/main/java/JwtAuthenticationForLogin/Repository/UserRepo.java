package JwtAuthenticationForLogin.Repository;

import JwtAuthenticationForLogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User>findByemailid(String emailid);
    Boolean existsByemailid(String emailid);

}
