package com.apicampanha.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Campanha {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	@OneToMany(mappedBy="campanha")
	@JsonManagedReference
	private List<Envio> envios;

    private Long id_cli;
    private String nome_campanha;
    private LocalDate dt_inicio;
    private LocalDate dt_fim;
    private LocalTime hr_inicio;
    private LocalTime hr_fim;
    private int status_campanha;

}
