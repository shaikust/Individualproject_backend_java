package JwtAuthenticationForLogin.services;

import JwtAuthenticationForLogin.Repository.ParcelRepo;
import JwtAuthenticationForLogin.exception.ResourceNotFoundException;
import JwtAuthenticationForLogin.model.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParcelService implements IParcelService{
    @Autowired
    ParcelRepo parcelRepo;
    @Override
    public ResponseEntity<List> getAllParcel() {
        try {
            System.out.println(2);
            return new ResponseEntity<List>(parcelRepo.findAll(), HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> addParcel(Parcel parcel) {
        parcelRepo.save(parcel);
        return new ResponseEntity<String>("["+parcel.getTrackingId()+"]"+"parcel added successfully",HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Parcel> getParcel(Integer trackingId) {

        return new ResponseEntity<Parcel>(parcelRepo.findById(trackingId).orElseThrow(()->new ResourceNotFoundException("parcel","id",trackingId)), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<String> updateParcel(Integer trackingId, Parcel updateparcel) {
        try {
            Parcel parcel=parcelRepo.findById(trackingId).orElseThrow(()->new UsernameNotFoundException("Parcel "+updateparcel.toString()+"with trackingId "+trackingId+"not found"));
            parcel.setCurrentLocation(updateparcel.getCurrentLocation());
            parcel.setDeliveryStatus(updateparcel.getDeliveryStatus());
            parcelRepo.save(parcel);
            return new ResponseEntity<String>("updated successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public ResponseEntity<Parcel> getParcelForUser(Integer trackingId) {
        return new ResponseEntity<Parcel>(parcelRepo.findById(trackingId).orElseThrow(()->new ResourceNotFoundException("parcel","id",trackingId)), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<String> deleteParcel(Integer trackingId) {
       try {
           Parcel parcel=parcelRepo.findById(trackingId).orElseThrow(()->new ResourceNotFoundException("parcel","trackinId",trackingId));
           parcelRepo.delete(parcel);
           return new ResponseEntity<>("deleted successfully",HttpStatus.OK);

       }catch (Exception e){
           return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);

       }
    }
}
