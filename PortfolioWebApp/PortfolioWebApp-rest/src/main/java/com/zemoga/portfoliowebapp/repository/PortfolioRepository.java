/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zemoga.portfoliowebapp.repository;

import com.zemoga.portfoliowebapp.model.Portfolio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manage repository
 * @author Yeison David Sanchez Legarda
 */
public interface PortfolioRepository extends JpaRepository<Portfolio,Long>{
    Optional<Portfolio> findByTwitterUserName(String twitterUserName);
    Optional<Portfolio> findFirstByOrderByIdPortfolioDesc();
}
