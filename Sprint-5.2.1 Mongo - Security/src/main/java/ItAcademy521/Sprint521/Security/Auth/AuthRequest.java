package ItAcademy521.Sprint521.Security.Auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthRequest {

    private String username;
    private String password;
}
