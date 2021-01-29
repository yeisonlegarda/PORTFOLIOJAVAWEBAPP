/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.service;

import com.zemoga.portfoliowebapp.dto.ProfileInformationDTO;
import com.zemoga.portfoliowebapp.exception.TechnicalException;
import com.zemoga.portfoliowebapp.model.Portfolio;
import com.zemoga.portfoliowebapp.repository.PortfolioRepository;
import com.zemoga.portfoliowebapp.restclient.TwitterClient;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles portfolio information
 * @author Yeison David Sanchez Legarda
 */
@Service
public class PortfolioService {
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private TwitterClient tweeterClient;
   
    
    /**
        * Find portfolio information by twitter user name 
        * @param twitterUsrName
        * @return Portfolio information
    */
    public Portfolio findByTwiterUserName(String twitterUsrName){
        System.out.println("twitterUserName "+twitterUsrName);
        return portfolioRepository.findByTwitterUserName(twitterUsrName).
                orElseThrow(()-> new TechnicalException("Cant find user name "+twitterUsrName));
    }
    
    
    /**
        * Calculates next portfolio id
        * @return portfolio next sequence
    */
    private Long calculatesPortfolioId(){
        Optional<Portfolio> portfolioIdMax = portfolioRepository.findFirstByOrderByIdPortfolioDesc();
        if(!portfolioIdMax.isPresent()){
            return 0L;
        }
        return portfolioIdMax.get().getIdPortfolio() + 1;
    }
    
    /**
        * Find portfolio information by id
        * @param portFolioId
        * @return Portfolio information
    */
    public Portfolio findByPortfolioId(Long portFolioId){
        return portfolioRepository.findById(portFolioId).
                orElseThrow(()-> new TechnicalException("Cant find portfolio with id "+portFolioId));
    }
    
    /**
        * Changes portfolio to DTO allowing charging tweeter info
        * @param portfolio
        * @param loadTweets
        * @return Portfolio information
    */
    public ProfileInformationDTO PortfolioToDTO(Portfolio portfolio,boolean loadTweets){
        ProfileInformationDTO profileInformationDTO = new ProfileInformationDTO();
        profileInformationDTO.setId(portfolio.getIdPortfolio());
        profileInformationDTO.setUserTwitterName(portfolio.getTwitterUserName());
        profileInformationDTO.setTitle(portfolio.getTitle());
        profileInformationDTO.setImageUrl(portfolio.getImageURL());
        profileInformationDTO.setUserExperience(portfolio.getDescription());
        try{
            profileInformationDTO.setUsertTweets(tweeterClient.getUserTweets(portfolio.getTwitterUserName()));
        }catch(Exception e){
            throw new TechnicalException("error getting information ",e);
        }
        return profileInformationDTO;
    }
    
    
    /**
        * Changes  DTO to portfolio entity
        * @param profileInformationDTO
        * @return Portfolio entity
    */
    public Portfolio PortfolioDTOToEntity(ProfileInformationDTO profileInformationDTO){
        Portfolio portfolio = new Portfolio();
        portfolio.setIdPortfolio(profileInformationDTO.getId());
        portfolio.setImageURL(profileInformationDTO.getImageUrl());
        portfolio.setDescription(profileInformationDTO.getUserExperience());
        portfolio.setTitle(profileInformationDTO.getTitle());
        portfolio.setTwitterUserName(profileInformationDTO.getUserTwitterName());
        return portfolio;
    }
    
    public Portfolio savePortfolioInfo(Portfolio portfolio){
        if(portfolio.getIdPortfolio()==null){
            portfolio.setIdPortfolio(this.calculatesPortfolioId());
        }
        return portfolioRepository.save(portfolio);
    }
}
