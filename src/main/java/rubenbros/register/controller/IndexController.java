package rubenbros.register.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/indexPlantilla")
    public String index(Model model, Authentication authentication) {
        // Extrae los detalles del usuario autenticado.
        // Aquí asumimos que el principal es de tipo UserDetails.
        String username = authentication.getName();

        // Puedes agregar más información, por ejemplo, consultando la base de datos.
        // Supongamos que agregamos el username al modelo.
        model.addAttribute("username", username);

        //Tambien añadimos aleatoriamente piedra papel o tijera
        model.addAttribute("jugada",jugadaRandom());

        return "indexPlantilla";
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
