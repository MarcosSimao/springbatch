package com.job.migracaodados.step;

import com.job.migracaodados.dominio.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MigrarPessoaStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrarDadosPessoaStep(ItemReader<Pessoa> arquivoPessoaReader,
                                      ItemWriter<Pessoa> bancoPessoaWrite){
        return stepBuilderFactory
                .get("migrarDadosPessoaStep")
                .<Pessoa,Pessoa>chunk(1)
                .reader(arquivoPessoaReader)
                .writer(bancoPessoaWrite)
                .build();
    }
}
