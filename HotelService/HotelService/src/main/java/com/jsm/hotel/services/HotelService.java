package com.jsm.hotel.services;

import com.jsm.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    // create hotel
    Hotel create(Hotel hotel);

    // get single hotel
    Hotel getSingleHotel(String hotelId);

    // get all hotels
    List<Hotel> getAllHotels();
}
