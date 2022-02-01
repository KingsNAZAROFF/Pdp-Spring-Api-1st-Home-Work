package uz.pdp.pdpspringapi1sthomework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "street bo'sh bo'lmasligi kerak")
    @Column(nullable = false)
    private String street;
    @NotNull(message = "homeNumber bo'sh bo'lmasligi kerak")
    @Column(nullable = false)
    private String homeNumber;
}
