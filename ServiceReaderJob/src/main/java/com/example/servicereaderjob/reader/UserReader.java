package com.example.servicereaderjob.reader;

import com.example.servicereaderjob.domain.ResponseUser;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.servicereaderjob.domain.User;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserReader implements ItemReader<User> {
  @Autowired
  private RestTemplate restTemplate;
  private int page=1;
  private List<User> users= new ArrayList<>();
  private int userIndex=0;
  @Value("${chunkSize}")
  private int pageSize;
  @Value("${pageSize}")
  private int chunkSize;
  @Value("${limit}")
  private int limit;
  private int total=0;

  @Override
  public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    User user;
    if (userIndex < users.size()) {
      user = users.get(userIndex);
    } else {
      user = null;
    }
    userIndex++;
    return user;
  }

  private List<User> acessandoApiExterna(){
 return restTemplate
         .getForEntity(String.format("https://gorest.co.in/public/v1/users?page=%d",page), ResponseUser.class)
         .getBody()
         .getData();
  }
  @BeforeChunk
  private void beforeChunck(ChunkContext chunkContext){
    for(int i=0;i<chunkSize; i+= pageSize){
      if(total>=limit) return;
      users.addAll(acessandoApiExterna());
      total+=users.size();
      page++;
    }
  }
  @AfterChunk
  private void afterChunck(ChunkContext chunkContext){
    System.out.println("fim chunck");
    userIndex=0;
    users=new ArrayList<>();
  }

}
