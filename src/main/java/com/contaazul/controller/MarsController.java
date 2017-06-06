package com.contaazul.controller;

import com.contaazul.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/rest/mars")
public class MarsController {

    @Autowired
    public LocationService locationService;

    @RequestMapping(value = "/{input}", method = RequestMethod.POST)
    public String hello(@PathVariable("input") String input, HttpServletResponse response) {
        try {
            locationService.performMovements(input);
            return locationService.current();
        } catch (IllegalArgumentException e) {
            response.setStatus(BAD_REQUEST.value());
            return null;
        }
    }
}
