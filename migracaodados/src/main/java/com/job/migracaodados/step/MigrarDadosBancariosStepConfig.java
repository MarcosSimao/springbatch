package com.job.migracaodados.step;

import com.job.migracaodados.dominio.DadosBancarios;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MigrarDadosBancariosStepConfig {
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrarDadosBancariosStep(ItemReader<DadosBancarios>arquivosDadosBancariosReader,
                                         ItemWriter<DadosBancarios>bancoDedadosBancariosWriter){
        return stepBuilderFactory
                .get("migrarDadosBancariosStep")
                .<DadosBancarios,DadosBancarios>chunk(1)
                .reader(arquivosDadosBancariosReader)
                .writer(bancoDedadosBancariosWriter)
                .build();
    }
}
