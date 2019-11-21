package io.jupeng.bitcoin.push;

;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ScokjsConfig implements  WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/bitcoin");
        config.setApplicationDestinationPrefixes("app");
    }


    public void  registerStompEndopints(StompEndpointRegistry registry){
        registry.addEndpoint("bitcoinexp;orerpush").setAllowedOrigins("*").withSockJS();
    }

}
