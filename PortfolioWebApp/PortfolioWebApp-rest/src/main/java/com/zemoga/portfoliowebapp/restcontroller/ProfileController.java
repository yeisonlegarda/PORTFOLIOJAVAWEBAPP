/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.restcontroller;

import com.zemoga.portfoliowebapp.api.Profile;
import com.zemoga.portfoliowebapp.dto.ProfileInformationDTO;
import com.zemoga.portfoliowebapp.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides access to profile 
 * @author Yeison David Sanchez Legarda
 */
@RestController
public class ProfileController  implements Profile {
    @Autowired
    private PortfolioService portfolioService;
    
    
    @Override
    public ProfileInformationDTO getProfileInformation(String tweeterUserName) {
       return portfolioService.PortfolioToDTO(
               portfolioService.findByTwiterUserName(tweeterUserName),Boolean.TRUE); 
    }

    @Override
    public ProfileInformationDTO saveUpdateProfileInformation(ProfileInformationDTO profileInformation) {
       return  portfolioService.PortfolioToDTO(
               portfolioService.savePortfolioInfo(
                       portfolioService.PortfolioDTOToEntity(
                               profileInformation)),Boolean.FALSE);
    }

    @Override
    public ProfileInformationDTO getProfileInformation(Long portfolioId) {
        return portfolioService.PortfolioToDTO(
               portfolioService.findByPortfolioId(portfolioId),Boolean.FALSE); 
    }
    
}
