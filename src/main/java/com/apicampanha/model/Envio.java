package com.apicampanha.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Envio {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "campanha_id", nullable = false)
    @JsonBackReference
    private Campanha campanha;

    private String celular;
    private int status;
    private LocalDate dt_status;
    private String resposta;
    private LocalDate dt_resposta;

}
