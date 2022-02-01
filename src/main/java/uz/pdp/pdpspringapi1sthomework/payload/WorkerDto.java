package uz.pdp.pdpspringapi1sthomework.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.pdpspringapi1sthomework.entity.Address;
import uz.pdp.pdpspringapi1sthomework.entity.Department;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak")
    private String phoneNumber;

    @NotNull(message = "address bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "address bo'sh bo'lmasligi kerak")
    private String homeNumber;

    @NotNull(message = "departmentId bo'sh bo'lmasligi kerak")
    private Integer departmentId;
}
