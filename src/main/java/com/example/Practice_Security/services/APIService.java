package com.example.Practice_Security.services;

import com.example.Practice_Security.dtos.UnknownPage;
import com.example.Practice_Security.dtos.UserData;
import com.example.Practice_Security.dtos.Page;
import com.example.Practice_Security.dtos.registerUser.RegistrationDto;
import com.example.Practice_Security.dtos.registerUser.RegistrationResponse;
import com.example.Practice_Security.dtos.unknownData.UnknownData;
import com.example.Practice_Security.dtos.userCreate.UserCreateRequest;
import com.example.Practice_Security.dtos.userCreate.UserCreateResponse;
import com.example.Practice_Security.repos.UnknownDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Service
public class APIService {

    private final RestTemplate restTemplate;

    private final UnknownDetailsRepository unknownDetailsRepository;

    private final String base_url = "https://reqres.in/";

    @Value("${x-api-key}")
    private String secret_key;

    @Autowired
    public APIService(RestTemplate restTemplate, UnknownDetailsRepository unknownDetailsRepository) {
        this.restTemplate = restTemplate;
        this.unknownDetailsRepository = unknownDetailsRepository;
    }

    public UserData getUserData(Long id){
        String url = base_url + "api/users/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", secret_key);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UserData> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                UserData.class
        );
        return response.getBody();
    }

    public Page getUserDataList(Long page){
        String uri = UriComponentsBuilder
                .fromUriString(base_url + "api/users")
                .queryParam("page", page)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", secret_key);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Page> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                Page.class
        );
        System.out.println(response.getBody());
        return response.getBody();
    }

    public ResponseEntity<String> storeUnknownPage(){
        String uri = UriComponentsBuilder
                .fromUriString(base_url +"api/unknown")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", secret_key);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UnknownPage> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                UnknownPage.class
        );
        UnknownPage unknownPage = response.getBody();
        UnknownData[] unknownData = unknownPage.getData();
        if (unknownData != null){
            for (int i = 0; i < unknownPage.getData().length; i++){
                unknownDetailsRepository.save(unknownData[i]);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Transfer completed!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer Failed");
    }

    public List<UnknownData> getUnknownData() {
        return unknownDetailsRepository.findAll().stream().toList();
    }

    public UserCreateResponse createUser(UserCreateRequest request){
        String uri = UriComponentsBuilder
                .fromUriString(base_url + "api/users")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", secret_key);

        HttpEntity<UserCreateRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<UserCreateResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                entity,
                UserCreateResponse.class
        );

        return response.getBody();
    }

    /*
    public UserPatchResponse updateUser(Long id, Map<String, Object> updates) {
        String uri = UriComponentsBuilder
                .fromUriString(base_url + "api/users/" + id)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", secret_key);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(updates, headers);

        try {
            ResponseEntity<UserPatchResponse> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    entity,
                    UserPatchResponse.class
            );
            log.info("######## Inside APIService.userUpdate, api response >>>----> {} #########", response);

            return response.getBody();
        } catch (Exception e) {
            return new UserPatchResponse(e.getMessage(), null, null);
        }
    }
     */

    public RegistrationResponse registerUser(RegistrationDto registrationDto){
        String url = UriComponentsBuilder
                .fromUriString(base_url + "api/register")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", secret_key);

        HttpEntity<RegistrationDto> entity = new HttpEntity<>(registrationDto, headers);

        ResponseEntity<RegistrationResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                RegistrationResponse.class
        );
        System.out.println(response.getBody());
        log.info("Response Body: {}", response.getBody());
        return response.getBody();

    }
}
