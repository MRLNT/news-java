package id.fazzbca.news.payloads.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRequest {
    @NotEmpty(message = "username harus di isi")
    private String username;

    @NotEmpty(message = "password harus di isi")
    private String password;

    @NotEmpty(message = "role harus di isi")
    private String role;

    @NotEmpty(message = "email harus di isi")
    private String email;
}
