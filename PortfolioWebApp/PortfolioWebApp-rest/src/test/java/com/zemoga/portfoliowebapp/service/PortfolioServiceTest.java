/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.portfoliowebapp.PortfolioWebRestApplication;
import com.zemoga.portfoliowebapp.dto.ProfileInformationDTO;
import com.zemoga.portfoliowebapp.dto.TweetDTO;
import com.zemoga.portfoliowebapp.model.Portfolio;
import com.zemoga.portfoliowebapp.repository.PortfolioRepository;
import com.zemoga.portfoliowebapp.restclient.TwitterClient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

/**
 *
 * @author admin
 */
@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {
    
    private ObjectMapper mapper;
    
    @Mock
    private PortfolioRepository portfolioRepository;
    
    @Mock
    private TwitterClient tweeterClient;
    
    @InjectMocks
    private PortfolioService portfolioService;
    
    private List<Portfolio> portfolios;
    private List<TweetDTO> usertTweets;
    
    public PortfolioServiceTest() throws IOException {
        mapper = new ObjectMapper();
        String entidadesJson = new String(Files.readAllBytes(Paths.get("src/test/resources/portfolioEntities.json")));
        portfolios = mapper.readValue(entidadesJson, mapper.getTypeFactory().constructCollectionType(List.class, Portfolio.class));
        String tweetsJson = new String(Files.readAllBytes(Paths.get("src/test/resources/tweets.json")));
        usertTweets = mapper.readValue(tweetsJson, mapper.getTypeFactory().constructCollectionType(List.class, TweetDTO.class));
    }
    
    @Before
    public static void setUp() {
    }


    /**
     * Test of findByTwiterUserName method, of class PortfolioService.
     */
    @DisplayName("Test normal behaoviur of getting profile info")
    @Test
    public void testFindByTwiterUserName() {
        Mockito.when(portfolioRepository.findByTwitterUserName(anyString())).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock iom) throws Throwable {
                return portfolios.stream().filter(t -> t.getTwitterUserName().equals(iom.getArgument(0))).findFirst();
            }
        });
        Portfolio result = portfolioService.findByTwiterUserName("LordSnow");
        
        Portfolio expResult = new Portfolio();
        expResult.setIdPortfolio(3L);
       
        assertEquals(expResult.getIdPortfolio(), result.getIdPortfolio());

    }

    /**
     * Test of findByPortfolioId method, of class PortfolioService.
     */
    @Test
    public void testFindByPortfolioId() {
        Mockito.when(portfolioRepository.findById(anyLong())).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock iom) throws Throwable {
                return portfolios.stream().filter(t -> t.getIdPortfolio().equals(iom.getArgument(0))).findFirst();
            }
        });
        Long portFolioId = 3l;
        Portfolio result = portfolioService.findByPortfolioId(portFolioId);
        Portfolio expResult = new Portfolio();
        expResult.setIdPortfolio(3l);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult.getIdPortfolio(), result.getIdPortfolio());
    }

    /**
     * Test of savePortfolioInfo method, of class PortfolioService.
     */
    @Test
    public void testSavePortfolioInfo() throws Exception {
        Mockito.when(portfolioRepository.findFirstByOrderByIdPortfolioDesc()).thenReturn(Optional.of(portfolios.get(0)));
        Mockito.when(portfolioRepository.save(any())).thenReturn(portfolios.get(0));
        Portfolio expResult = portfolios.get(0);
        Portfolio portfolio = new Portfolio();
        Portfolio result = portfolioService.savePortfolioInfo(portfolio);
        assertEquals(expResult, result);
    }
    
}
