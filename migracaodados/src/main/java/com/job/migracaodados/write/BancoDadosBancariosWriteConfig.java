package com.job.migracaodados.write;

import com.job.migracaodados.dominio.DadosBancarios;
import com.job.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BancoDadosBancariosWriteConfig {
    @Bean
    public JdbcBatchItemWriter<DadosBancarios> bancoDedadosBancariosWriter(
            @Qualifier("appDataSource") DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<DadosBancarios>()
                .dataSource(dataSource)
                .sql("INSERT INTO dados_bancarios (id,pessoa_id,agencia,conta,banco) values (?,?,?,?,?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();

    }

    private ItemPreparedStatementSetter<DadosBancarios> itemPreparedStatementSetter(){
        return ((db, ps) -> {
            ps.setInt(1,db.getId());
            ps.setInt(2,db.getPessoaId());
            ps.setInt(3,db.getAgencia());
            ps.setInt(4,db.getConta());
           ps.setInt(5,db.getBanco());

        });
    }
}
