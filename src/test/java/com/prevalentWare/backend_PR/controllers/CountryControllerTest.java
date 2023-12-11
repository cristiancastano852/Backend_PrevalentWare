package com.prevalentWare.backend_PR.controllers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prevalentWare.backend_PR.config.AuthorizationUtil;
import com.prevalentWare.backend_PR.entities.Country;
import com.prevalentWare.backend_PR.services.contracts.ICountryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CountryControllerTest {

    @Mock
    private ICountryService countryService;

    @Mock
    private AuthorizationUtil authorizationUtil;

    @InjectMocks
    private CountryController countryController;

    @Test
    public void shouldReturnUnauthorizedWhenTokenIsNull() {
        ResponseEntity<?> response = countryController.getAllCountries(null, 0, 10);

        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
        assertEquals(response.getBody(),"Unauthorized: No token | User is not authorized to access this resource ");
    }

    @Test
    public void shouldReturnUnauthorizedWhenUserIsNotAuthorized() {
        String token = "invalid-token";
        when(authorizationUtil.isAuthorized(token, "Admin", "Manager")).thenReturn(false);

        ResponseEntity<?> response = countryController.getAllCountries(token, 0, 10);

        assertEquals(response.getStatusCode(),HttpStatus.UNAUTHORIZED);
        assertEquals(response.getBody(),"Unauthorized: User is not authorized to access this resource");
    }

    @Test
    public void shouldReturnCountriesListWhenAuthorized() {
        String token = "valid-token";
        when(authorizationUtil.isAuthorized(token, "Admin", "Manager")).thenReturn(true);
        List<Country> mockCountries = Arrays.asList(new Country(), new Country());
        when(countryService.findAllPaginated(0, 10)).thenReturn(ResponseEntity.ok(mockCountries));

        ResponseEntity<?> response = countryController.getAllCountries(token, 0, 10);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
        assertEquals(response.getBody(),mockCountries);
    }

    @Test
    public void shouldReturnInternalServerErrorWhenExceptionOccurs() {
        String token = "valid-token";
        when(authorizationUtil.isAuthorized(token, "Admin", "Manager")).thenReturn(true);
        when(countryService.findAllPaginated(0, 10)).thenThrow(new RuntimeException());

        ResponseEntity<?> response = countryController.getAllCountries(token, 0, 10);

        assertEquals(response.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody(),"Internal Server Error");
    }
}
