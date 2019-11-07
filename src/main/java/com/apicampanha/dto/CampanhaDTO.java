package com.apicampanha.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.apicampanha.model.Envio;

import lombok.Data;

@Data
public class CampanhaDTO {
	
	private Long id_cli;
    private String nome_campanha;
    private LocalDate dt_inicio;
    private LocalDate dt_fim;
    private LocalTime hr_inicio;
    private LocalTime hr_fim;
    
    private List<EnvioDTO> envios;

}
