package mx.edu.utez.cotizadores.controller;

import jakarta.validation.Valid;
import mx.edu.utez.cotizadores.controller.dtos.RequestRentaDTO;
import mx.edu.utez.cotizadores.controller.dtos.ResponseRentaDTO;
import mx.edu.utez.cotizadores.service.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/rentas")
public class RentaController {

    private final RentaService service;

    public RentaController(RentaService service) {
        this.service = service;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseRentaDTO> cotizar(@RequestBody @Valid RequestRentaDTO payload) {
        return ResponseEntity
                .status(200)
                .body(service.cotizar(payload));
    }
}
