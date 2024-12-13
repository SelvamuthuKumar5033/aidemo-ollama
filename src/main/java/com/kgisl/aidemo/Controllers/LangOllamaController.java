package com.kgisl.aidemo.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import java.time.Duration;

@RestController
public class LangOllamaController {

  @Value("${spring.ai.ollama.chat.option.model}")
  String modelName;

  @GetMapping("/lang")
  public String getLangOllama(String userInput) {  
    // Build the ChatLanguageModel
    ChatLanguageModel model =
        OllamaChatModel.builder().baseUrl("http://Localhost:11434/").modelName(modelName).timeout(Duration.ofSeconds(100)).build();

    // Example usage
    String answer = model.generate(userInput);
    return answer;
  }
}
