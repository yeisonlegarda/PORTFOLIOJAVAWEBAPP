/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.portfoliowebapp.dto.TweetDTO;
import java.io.IOException;
import java.util.List;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

/**
 * Client for consumes twitter information
 * @author Yeison David Sanchez Legarda
 */
@Service
public class TwitterClient {
    @Value("${twitter.AccessToken:'NA'}")
    private String accessToken;
    
    @Value("${twitter.AccessTokenSecret:'NA'}")
    private String AccessTokenSecret;
    
    @Value("${twitter.APIKey:'NA'}")
    private String APIKey;
    
    @Value("${twitter.APISecretKey:'NA'}")
    private String APISecretKey;
    
    @Value("${twitter.APIURL:'NA'}")
    private String apiURL;
    
    @Autowired
    private RestTemplateBuilder builder;
    
    ObjectMapper mapper;
    
    /*
    *Consumes tweeter service
    * @param user
    * @return List with information about tweets
    */
    public List<TweetDTO> getUserTweets(String user) throws OAuthMessageSignerException, 
            OAuthExpectationFailedException, 
            OAuthCommunicationException,
            IOException{
        mapper = new ObjectMapper();
        String url = apiURL + "statuses/user_timeline.json?screen_name="+user+"&count=5";
        HttpGet request = new HttpGet(url);
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(APIKey,APISecretKey);
        consumer.setTokenWithSecret(accessToken, AccessTokenSecret);
        consumer.sign(request);
        HttpResponse response = HttpClientBuilder.
                create().
                build().execute(request);
   
        return mapper.readValue(response.getEntity().getContent(),mapper.getTypeFactory().constructCollectionType(List.class, TweetDTO.class));
        
    }
}
