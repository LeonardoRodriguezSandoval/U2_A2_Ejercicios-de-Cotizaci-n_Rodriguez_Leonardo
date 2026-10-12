package mx.edu.utez.cotizadores.controller;

import jakarta.validation.Valid;
import mx.edu.utez.cotizadores.controller.dtos.RequestEnvioDTO;
import mx.edu.utez.cotizadores.controller.dtos.ResponseEnvioDTO;
import mx.edu.utez.cotizadores.service.EnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService service;

    public EnvioController(EnvioService service) {
        this.service = service;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseEnvioDTO> cotizar(@RequestBody @Valid RequestEnvioDTO payload) {
        return ResponseEntity
                .status(200)
                .body(service.cotizar(payload));
    }
}
