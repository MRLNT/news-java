package id.fazzbca.news.payloads.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RoleRequest {
    @NotEmpty(message = "role harus di isi")
    private String name;
}