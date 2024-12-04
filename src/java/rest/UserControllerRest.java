package com.aes.eventmanagementsystem.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aes.eventmanagementsystem.constants.UserConstants;
import com.aes.eventmanagementsystem.dto.ResponseDto;
import com.aes.eventmanagementsystem.dto.UserDto;
import com.aes.eventmanagementsystem.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "/api/users", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserControllerRest {

    private IUserService userService;

    public UserControllerRest(IUserService userService) {
        this.userService = userService;
    }

    // @PostMapping("/create")
    // public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) {
    //     userService.createUser(userDto);
    //     return ResponseEntity.status(HttpStatus.CREATED)
    //             .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    // }

    @GetMapping("/fetch")
    public ResponseEntity<UserDto> fetchUserDetails(@RequestParam String email) {
        UserDto userDto = userService.fetchUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUserDetails(@RequestBody UserDto userDto) {

        boolean isUpdated = userService.updateUser(userDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(UserConstants.STATUS_200,
                            UserConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(UserConstants.STATUS_417,
                            UserConstants.MESSAGE_417_UPDATE));
        }
    }

}
