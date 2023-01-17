package com.jsm.hotel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "micro_hotels")
public class Hotel {
    @Id
    private String hotelId;
    private String hotelName;
    private String hotelLocation;
    private String hotelAbout;
}
