package com.job.migracaodados.reader;

import com.job.migracaodados.dominio.DadosBancarios;
import com.job.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivosDadosBancariosReaderConfig {
    @Bean
    public FlatFileItemReader<DadosBancarios> arquivosDadosBancariosReader(){
        return new FlatFileItemReaderBuilder<DadosBancarios>()
                .name("arquivoPessoaReader")
                .resource(new FileSystemResource("files/dados_bancarios.csv"))
                .delimited()
                .names("pessoa_id","agencia","conta","banco","id")
                .addComment("--")
                .targetType(DadosBancarios.class)
                .build();
    }
}
