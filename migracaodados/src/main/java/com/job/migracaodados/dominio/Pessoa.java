package com.job.migracaodados.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable {
    private int id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private int idade;
}
