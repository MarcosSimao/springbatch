package com.job.migracaodados.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DadosBancarios implements Serializable {

    private int id;
    private int pessoaId;
    private int agencia;
    private int conta;
    private int banco;
}
