package JwtAuthenticationForLogin.services;

import JwtAuthenticationForLogin.model.Parcel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IParcelService {
 ResponseEntity<List> getAllParcel();
 ResponseEntity<String> addParcel(Parcel parcel);
 ResponseEntity<Parcel> getParcel(Integer trackingId);
 ResponseEntity<String> updateParcel(Integer trackingId,Parcel parcel);
 ResponseEntity<Parcel> getParcelForUser(Integer trackingId);
 ResponseEntity<String> deleteParcel(Integer trackingId);
}
