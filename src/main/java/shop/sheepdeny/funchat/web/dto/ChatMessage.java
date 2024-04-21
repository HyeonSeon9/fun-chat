package shop.sheepdeny.funchat.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatMessage {
    private String content;
    private String sender;
    private LocalDateTime timestamp;
}
