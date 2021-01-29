/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Profile information to return
 * @author Yeison David Sanchez Legarda
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInformationDTO {
    private Long id;
    private List<TweetDTO> usertTweets;
    private String userTwitterName;
    private String title;
    private String userExperience;
    private String imageUrl;
}
