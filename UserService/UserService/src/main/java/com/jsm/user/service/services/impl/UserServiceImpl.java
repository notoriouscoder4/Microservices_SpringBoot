package com.jsm.user.service.services.impl;

import com.jsm.user.service.entities.Hotel;
import com.jsm.user.service.entities.Rating;
import com.jsm.user.service.entities.User;
import com.jsm.user.service.exceptions.ResourceNotFoundException;
import com.jsm.user.service.external.services.HotelService;
import com.jsm.user.service.repositories.UserRepository;
import com.jsm.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // generate unique user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    // get all user
    @Override
    public List<User> getAllUser() {
        // implement RATING SERVICE CALL: using RestTemplate
        return userRepository.findAll();
    }

    // get single user
    @Override
    public User getUser(String userId) {
        // get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!!! : " + userId));
        // get rating of the above user from RATING SERVICE
        // http://localhost:8083/ratings/users/038ada2a-5eb4-4ecc-95c3-72aef4e73e1b
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        logger.info("{}", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to HOTEL SERVICE to get the hotel
            // http://localhost:8082/hotels/5b33587e-36dd-4976-bfbe-40995a453e0b
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            // set the hotel to rating
            rating.setHotel(hotel);
            // return rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
