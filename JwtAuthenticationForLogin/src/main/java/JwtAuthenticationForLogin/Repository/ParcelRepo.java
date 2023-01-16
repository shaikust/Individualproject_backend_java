package JwtAuthenticationForLogin.Repository;

import JwtAuthenticationForLogin.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParcelRepo extends JpaRepository<Parcel,Integer> {

  public List<Parcel> findBytrackingId(Integer trackingId);

}
