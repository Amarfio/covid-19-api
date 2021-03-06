package com.work.covid19api.controller;

import com.work.covid19api.EmailSenderService;
import com.work.covid19api.exception.ResourceNotFoundException;
import com.work.covid19api.model.CovidTest;
import com.work.covid19api.model.User;
import com.work.covid19api.repository.CovidTestRepository;
import com.work.covid19api.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/covid19/api/v1")

public class CovidTestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CovidTestRepository covidTestRepository;

    @Autowired
    EmailSenderService senderService;

    //get covid tests: get all covid tests in the database
    @Operation(summary="Get covid tests", description = "Get a list of covid tests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the tests",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Tests not found",
                    content = @Content)})
    @GetMapping("/covidtests")
    public List<CovidTest> getAllCovidTests(){return this.covidTestRepository.findAll();}

    //save covidtest by userid: future change: change to save covid test by user's unique email
    @Operation(summary="Add covid test", description = "Add a new covid test")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Test",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Tests not found",
                    content = @Content)})
    @PostMapping("/covidtests")
    public CovidTest createTest(@RequestBody CovidTest covidTest){
        double userTemp = covidTest.getTemperature();
        covidTest.setCovidresult(covidResult(userTemp));
        covidTest.setPostingdate(LocalDateTime.now());
        String userid = covidTest.getUserid();

        //use the userid to find user's details
        User user = userRepository.findUserByUserid(userid);

        //get user email
        String userEmail = user.getEmail();

        //get user's first name as well
        String userFristName = user.getFirstname();

        //get the covid result
        String result = covidTest.getCovidresult();

        //send email to user with the test result...
        sendCovidTestEmail(userEmail, userFristName,result,"Good luck");

        return this.covidTestRepository.save(covidTest);
    }

    //method to check for covid status
    private String covidResult(double temperature){
        while(temperature > 39 ){

            if (temperature >= 40){
                return "A Suspected COVID Case";
            }

        }


        return " Normal Temperature";
    }

    //code to update user details
    @Operation(summary="Update covid test using test id", description = "Updates user's covid test by details provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Test",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Test not found",
                    content = @Content)})
    @PutMapping("/covidtests/{id}")
    public ResponseEntity updateCovidTest(@PathVariable(value="id") Long covidId, @Validated @RequestBody CovidTest covidDetails) throws ResourceNotFoundException {
        CovidTest covidTest = covidTestRepository.findById(covidId).orElseThrow(()-> new ResourceNotFoundException("CovidTest not found for this id::"+ covidId));
        covidTest.setTestdate(covidTest.getTestdate());
        covidTest.setUpdateddate(LocalDateTime.now());

        return ResponseEntity.ok(this.covidTestRepository.save(covidTest));
    }

    //method to set up and send email
    public void sendCovidTestEmail(String toEmail, String userFirstName, String process, String goodWish){

        //commented for debugging purposes
        // toEmail = "joshuaamarfio1@gmail.com";

        //subject of the email
        String subject="Covid Test Update";

        //stuff for check in
        //user name, time of check in and process

        //message of the email
        String body = "Hello " +userFirstName+", "+
                //end the line
                "\nYour covid test result indicates that "+process+
                //end the line
                "\n\n"
                +goodWish+"!!!";

        senderService.sendEmail(toEmail, subject, body);
    }
}
