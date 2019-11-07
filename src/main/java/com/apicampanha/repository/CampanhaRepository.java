package com.apicampanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apicampanha.model.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

}
