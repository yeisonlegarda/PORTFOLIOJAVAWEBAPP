/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author admin
 */
@ComponentScan("com.zemoga.portfoliowebapp")
@SpringBootApplication
public class PortfolioWebRestApplication {
    public static void main(String[] args) {
        // TODO code application logic here
        SpringApplication.run(PortfolioWebRestApplication.class, args);
    }
}
