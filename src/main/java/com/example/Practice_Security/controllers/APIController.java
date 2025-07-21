package com.example.Practice_Security.controllers;

import com.example.Practice_Security.dtos.UserData;
import com.example.Practice_Security.dtos.Page;
import com.example.Practice_Security.dtos.registerUser.RegistrationDto;
import com.example.Practice_Security.dtos.registerUser.RegistrationResponse;
import com.example.Practice_Security.dtos.unknownData.UnknownData;
import com.example.Practice_Security.dtos.userCreate.UserCreateRequest;
import com.example.Practice_Security.dtos.userCreate.UserCreateResponse;
import com.example.Practice_Security.dtos.userPatch.UserPatchRequest;
import com.example.Practice_Security.dtos.userPatch.UserPatchResponse;
import com.example.Practice_Security.services.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {
    private final APIService apiService;

    @Autowired
    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/user/{id}")
    public UserData getUserData(@PathVariable Long id){
        return apiService.getUserData(id);
    }

    @GetMapping("/user/page/{page}")
    public Page getUserDataList(@PathVariable Long page){
        return apiService.getUserDataList(page);
    }

    @GetMapping("/storeUnknowns")
    public ResponseEntity<String> storeUnknownPage(){
        return apiService.storeUnknownPage();
    }

    @GetMapping("/getUnknowns")
    public List<UnknownData> getUnknownData(){
        return apiService.getUnknownData();
    }

    @PostMapping("/create")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest){
        return apiService.createUser(userCreateRequest);
    }

//    @PatchMapping("/update/{id}")
//    public UserPatchResponse updateUser(@PathVariable Long id,
//                                        @RequestBody Map<String, Object> updates){
//        return apiService.updateUser(id, updates);
//    }

    @PostMapping("/registerUser")
    public RegistrationResponse registerUser(@RequestBody RegistrationDto registrationDto){
        return apiService.registerUser(registrationDto);
    }


}
