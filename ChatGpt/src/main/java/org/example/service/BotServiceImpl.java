package org.example.service;


import org.example.config.ChatGptConfig;
import org.example.model.request.BotRequest;
import org.example.model.request.ChatGptRequest;
import org.example.model.response.ChatGptResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BotServiceImpl implements BotService {

    private static RestTemplate restTemplate = new RestTemplate();

    //    Build headers
    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(chatRequest, headers);
    }

    //    Generate response
    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatRequestHttpEntity,
                ChatGptResponse.class);

        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestion(BotRequest botRequest) {
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                ChatGptConfig.MODEL,
                                botRequest.getMessage(),
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TOP_P)));
    }
}






