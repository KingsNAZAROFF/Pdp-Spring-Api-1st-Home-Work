package uz.pdp.pdpspringapi1sthomework.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse {
    private String message;
    private boolean success;
}
