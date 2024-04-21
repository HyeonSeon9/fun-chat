package shop.sheepdeny.funchat.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.sheepdeny.funchat.web.dto.ChatMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        log.info("sendMessage");

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public/{id}")
    public ChatMessage addUser(ChatMessage chatMessage) {
        log.info("addUser");
        return chatMessage;
    }

    @GetMapping("/web/chat")
    public String webChat(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(name = "userName", required = false) String userName) {
        if (Objects.nonNull(userName)) {
            model.addAttribute("userName", userName);
        }
        return "web-cli";
    }

    @PostMapping("/web/chat/public")
    public String webChatPublic(RedirectAttributes redirectAttributes, String userName) throws IOException {
        log.info("{}", userName);
        redirectAttributes.addFlashAttribute("userName", userName);
        return "redirect:/web/chat";
    }
}
