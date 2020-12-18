package ru.eugene.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Data
@AllArgsConstructor
@Table(name = "vouchers")
public class VoucherModel {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "destination_country")
    private String destinationCountry;

    @Column(name = "destination_region")
    private String destinationRegion;
}
