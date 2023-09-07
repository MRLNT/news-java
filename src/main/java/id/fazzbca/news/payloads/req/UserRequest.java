package id.fazzbca.news.payloads.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRequest {
    @NotEmpty(message = "username harus di isi")
    private String username;

    @NotEmpty(message = "email harus di isi")
    private String email;

    @NotEmpty(message = "password harus di isi")
    private String password;
}
