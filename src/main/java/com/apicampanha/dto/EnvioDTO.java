package com.apicampanha.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EnvioDTO {
	
	private Long id;
	private String celular;
	private int status;
	private LocalDate dt_status;
	private String resposta;
	private LocalDate dt_resposta;

}
