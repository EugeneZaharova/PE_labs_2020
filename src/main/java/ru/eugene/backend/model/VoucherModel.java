package ru.eugene.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "vouchers")
@NoArgsConstructor
@AllArgsConstructor
public class VoucherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

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
