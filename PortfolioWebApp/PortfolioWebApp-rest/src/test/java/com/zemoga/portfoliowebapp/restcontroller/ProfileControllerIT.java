/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import com.zemoga.portfoliowebapp.PortfolioWebRestApplication;
import com.zemoga.portfoliowebapp.dto.ProfileInformationDTO;
import com.zemoga.portfoliowebapp.dto.TweetDTO;
import com.zemoga.portfoliowebapp.model.Portfolio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author admin
 */
@SpringBootTest(classes = PortfolioWebRestApplication.class)
@ExtendWith(MockitoExtension.class)
public class ProfileControllerIT {
    
     private MockMvc restMockMvc;
    
    @Autowired
    private WebApplicationContext wac;
    
    private WireMockServer wireMockTweeterApi;
    
    @Autowired
    private ProfileController profileController;
    
    private ObjectMapper mapper;
    
    private List<TweetDTO> usertTweets;
    
    
    private ResultMatcher ok;
    private ResultMatcher badRequest;
    private ResultMatcher serverError;
    
    
    public ProfileControllerIT() throws IOException {
        wireMockTweeterApi = new WireMockServer();
        mapper = new ObjectMapper();
        String tweetsJson = new String(Files.readAllBytes(Paths.get("src/test/resources/tweets.json")));
        usertTweets = mapper.readValue(tweetsJson, mapper.getTypeFactory().constructCollectionType(List.class, TweetDTO.class));
    }
    
    @BeforeEach
    public void setUp() {
        ok = MockMvcResultMatchers.status().isOk();
        badRequest = MockMvcResultMatchers.status().isBadRequest();
        serverError = MockMvcResultMatchers.status().isInternalServerError();
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
        this.restMockMvc = builder.build(); 
    }
    

    /**
     * Test of getProfileInformation method, of class ProfileController.
     */
    @Test
    public void testGetProfileInformation_String() throws JsonProcessingException, Exception {
        
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.
                get("/profile/information/AngelaM_queen");
        
        wireMockTweeterApi.start();
        wireMockTweeterApi.stubFor(get(urlMatching("/1.1/statuses/.*")).
                willReturn(aResponse().withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(mapper.writeValueAsString(usertTweets))));
    
        MvcResult result = this.restMockMvc.perform(request).andExpect(ok).andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ProfileInformationDTO profileInfo =  mapper.readValue(contentAsString,ProfileInformationDTO.class);
        assertNotNull(profileInfo);
        wireMockTweeterApi.stop();
    }
    
}
