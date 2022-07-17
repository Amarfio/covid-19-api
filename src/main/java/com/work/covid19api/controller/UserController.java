package com.work.covid19api.controller;

import com.work.covid19api.exception.ResourceNotFoundException;
import com.work.covid19api.model.User;
import com.work.covid19api.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/covid19/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //get users: Get all the user records saved in the database
    @Operation(summary="Get users", description = "Get a list of Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Users",
                         content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Users not found",
                         content = @Content)})
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    //get user details by id : get a particular user details by the specified id
    @Operation(summary="Get user by id", description = "Get user details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found for this id::"+ id));
        return ResponseEntity.ok().body(user);
    }

    //get user details by specified userid
//    @GetMapping("/users/{userid}")
//    public ResponseEntity<User> getUserByUserid(@PathVariable(value="userid") String userid) throws ResourceNotFoundException{
//        User user = userRepository.findUserByUserid(userid);
////                .orElseThrow(()->new ResourceNotFoundException("User not found for this userid::"+ userid));
//
//        return ResponseEntity.ok().body(user);
//    }

    //save user: post new User details
    @Operation(summary="Post user", description = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User added",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content)})
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        //get user id
        String userid = generateUserId();

        //get user email
        String useremail = user.getEmail();

        //for saving to the database
        user.setUserid(userid);

        //setting for posted date
        user.setPosted_date(LocalDateTime.now());


        //method for sending the email
//        sendNewUserEmail(useremail, userid);

        //save details in the database
        return this.userRepository.save(user);

    }

    //code to update user details
    @Operation(summary="Update user details", description = "Update user details using the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content)})
    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable(value="id") Long userId, @Validated @RequestBody User userDetails) throws ResourceNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found for this id::"+ userId));
        user.setEmail(userDetails.getEmail());
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setOthername(userDetails.getOthername());
        user.setContact(userDetails.getContact());
        user.setUpdate_date(LocalDateTime.now());


        return ResponseEntity.ok(this.userRepository.save(user));
    }

    //method to get the number of records in the users table
    public long getUsersCount(){
        return this.userRepository.count();
    }


    //method to generate and return user id
    public String generateUserId(){
        //get the number of records in the table
        long numberofUsers = getUsersCount();

        //add one to the total of the records
        long plusOne = numberofUsers + 1;
        String stringId = String.valueOf(plusOne);

        for(int numLength=stringId.length(); numLength<4; ++numLength ){
            stringId = "0" + stringId;
        }

        return stringId;
    }


}
