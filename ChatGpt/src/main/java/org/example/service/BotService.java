package org.example.service;

import org.example.model.request.BotRequest;
import org.example.model.response.ChatGptResponse;

public interface BotService {

    ChatGptResponse askQuestion(BotRequest botRequest);
}
