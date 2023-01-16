package JwtAuthenticationForLogin.model;

public class JwtRequest {
    String emailid;
    String password;

    public JwtRequest() {
    }

    public JwtRequest(String emailid, String password) {
        this.emailid = emailid;
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Override
//    public String toString() {
//        return "JwtRequest{" +
//                "email='" + emailid + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
