package com.cg.usermicroservice.controller;
import com.cg.usermicroservice.dto.APIResponseDto;
import com.cg.usermicroservice.dto.UserDto;
import com.cg.usermicroservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin (origins ="http://localhost:4200")
@RequestMapping("api/users")
@AllArgsConstructor
@Tag(
        name = "User_Controller",
        description = "User_controller Exposes REST API"
)
public class UserController {
    private UserService userService;
    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user details in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    // build create User REST API
    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get all Users REST API",
            description = "Get is used to get all Users"
    )
    @ApiResponse(
            responseCode = "20",
            description = "OK"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @Operation(
            summary = "Get User REST API",
            description = "Get is used to get User"
    )
    @ApiResponse(
            responseCode = "20",
            description = "OK"
    )
    // Build Get User by Code REST API
    @GetMapping("{userId}")
    public ResponseEntity<APIResponseDto> getUser(@PathVariable("userId") Long userId){
        APIResponseDto apiResponseDto = userService.getUserById(userId);
        return ResponseEntity.ok(apiResponseDto);
    }
    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user details in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Update User REST API
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userId, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Delete User REST API
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}