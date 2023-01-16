package JwtAuthenticationForLogin.dto;

public class JwtResponse {
   private String accessToken;
  private   String tokenType;

    public JwtResponse() {
    }

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
