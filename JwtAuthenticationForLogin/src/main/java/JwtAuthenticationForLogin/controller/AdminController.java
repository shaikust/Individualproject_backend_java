package JwtAuthenticationForLogin.controller;

import JwtAuthenticationForLogin.model.Parcel;
import JwtAuthenticationForLogin.services.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//  (origins="http://localhost:3000")
//  (origins="http://127.0.0.1:5501")
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:3000")
//@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AdminController {
@Autowired
    IParcelService iParcelService;
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("getparcel")

    public ResponseEntity<List> getallParcel(){
    System.out.println(1999);
    return iParcelService.getAllParcel();
}
@GetMapping("getparcel1/{trackingId}")

    public ResponseEntity<Parcel> getparcel(@PathVariable Integer trackingId){
  System.out.println(1999);
    return iParcelService.getParcel(trackingId);
}
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("addparcel")
    public ResponseEntity<String> addparcel(@RequestBody Parcel parcel){
    return iParcelService.addParcel(parcel);
}
@PreAuthorize("hasRole('ADMIN')")
@PutMapping("updateparcel/{trackingId}")
    public ResponseEntity<String> updateparcel(@PathVariable Integer trackingId,@RequestBody Parcel parcel){
    return  iParcelService.updateParcel(trackingId,parcel);

}
    @PreAuthorize("hasRole('USER')")
    @GetMapping("getparcelforuser/{trackingId}")
    public ResponseEntity<Parcel> getparcelForUser(@PathVariable Integer trackingId){
        return iParcelService.getParcelForUser(trackingId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("deleteparcel/{trackingId}")
    public ResponseEntity<String> deleteParcel(@PathVariable Integer trackingId){
    return iParcelService.deleteParcel(trackingId);
    }

}
