/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.api;

import com.zemoga.portfoliowebapp.dto.ProfileInformationDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Exposes access to profile information
 * @author Yeison David Sanchez Legarda
 */
@Api(tags = {"PortfolioApi"})
@SwaggerDefinition(tags = {
    @Tag(name = "PortfolioApi", description = "EndPoints set to get and save profile information")
})
@RequestMapping("profile")
public interface Profile {
    
    /**
     * Provides and decodes information on satellites about a carrier
     * @param tweeterUserName
     * @return Profile Information
     */
    @ApiOperation(httpMethod = "GET", 
                    value = "Gets profile information given the tweeter user name", 
                    response = ProfileInformationDTO.class,
                    notes = "From tweeter user name get profile information"
                    )
    @GetMapping("/information/{tweeterUserName}")
    ProfileInformationDTO getProfileInformation(@ApiParam("Tweeter user name")
                                                        @PathVariable String tweeterUserName);
    
    /**
     * Provides and decodes information on satellites about a carrier
     * @param portfolioId
     * @return Profile Information
     */
    @ApiOperation(httpMethod = "GET", 
                    value = "Gets profile information given the portfolio id", 
                    response = ProfileInformationDTO.class,
                    notes = "From portfolio Id profile information"
                    )
    @GetMapping("/informationByPortfolioId/{portfolioId}")
    ProfileInformationDTO getProfileInformation(@ApiParam("Portfolio id")
                                                        @PathVariable Long portfolioId);
    
    /**
     * Provides and decodes information on satellites about a carrier
     * @param profileInformation
     * @return Profile Information
     */
    @ApiOperation(httpMethod = "POST", 
                    value = "Saves profile information", 
                    response = ProfileInformationDTO.class,
                    notes = "From tweeter user name get profile information"
                    )
    @PostMapping("/information")
    ProfileInformationDTO saveUpdateProfileInformation(@ApiParam("Information profile")
                                                        @RequestBody ProfileInformationDTO profileInformation);
    
}
