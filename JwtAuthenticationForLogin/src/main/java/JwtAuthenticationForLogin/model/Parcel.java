package JwtAuthenticationForLogin.model;

import javax.persistence.*;

@Entity
@Table(name = "parcellist")
public class Parcel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "trackingId")
    private Integer trackingId;
//    private Integer id;
    private String senderLocation;
    private String receiverLocation;

    private String currentLocation;
    private String deliveryStatus;
    private String createdBy;

    public String getSenderLocation() {
        return senderLocation;
    }

    public void setSenderLocation(String senderLocation) {
        this.senderLocation = senderLocation;
    }

    public String getReceiverLocation() {
        return receiverLocation;
    }

    public void setReceiverLocation(String receiverLocation) {
        this.receiverLocation = receiverLocation;
    }

    public Integer getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Integer trackingId) {
        this.trackingId = trackingId;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    @Override
    public String toString() {
        return "Parcel{" +
//                "id=" + id +
                ", senderLocation='" + senderLocation + '\'' +
                ", receiverLocation='" + receiverLocation + '\'' +
                ", trackingId=" + trackingId +
                ", currentLocation='" + currentLocation + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
