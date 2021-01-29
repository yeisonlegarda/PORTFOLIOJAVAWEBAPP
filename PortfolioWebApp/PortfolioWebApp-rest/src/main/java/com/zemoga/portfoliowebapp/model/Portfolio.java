/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Table where its stored data
 * @author Yeison David Sanchez Legarda
 */
@Data
@Entity
@Table
public class Portfolio implements Serializable {
  
  @Id
  @Column(name="idportfolio")
  private Long idPortfolio;
  
  private String description;
  
  @Column(name="image_url")
  private String imageURL;
  
  @Column(name="twitter_user_name")
  private String twitterUserName;
  
  private String title;
}
