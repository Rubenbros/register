package rubenbros.register.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class IndexRestController {

    // Endpoint que devuelve el usuario en formato JSON
    @GetMapping("/user-info")
    public String getUserInfo(Authentication authentication) {
        return authentication.getName();
    }

    // Endpoint que devuelve una jugada aleatoria (piedra, papel o tijera)
    @GetMapping("/random-move")
    public String getRandomMove() {
        return jugadaRandom();
    }

    private String jugadaRandom() {
        Random random = new Random();
        return switch (random.nextInt(3)) {
            case 1 -> "papel";
            case 2 -> "tijera";
            default -> "piedra";
        };
    }
}
