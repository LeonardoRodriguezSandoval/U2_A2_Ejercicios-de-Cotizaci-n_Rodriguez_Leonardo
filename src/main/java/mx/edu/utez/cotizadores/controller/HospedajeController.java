package mx.edu.utez.cotizadores.controller;

import jakarta.validation.Valid;
import mx.edu.utez.cotizadores.controller.dtos.RequestHospedajeDTO;
import mx.edu.utez.cotizadores.controller.dtos.ResponseHospedajeDTO;
import mx.edu.utez.cotizadores.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/hospedajes")
public class HospedajeController {

    private final HospedajeService service;

    public HospedajeController(HospedajeService service) {
        this.service = service;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseHospedajeDTO> cotizar(@RequestBody @Valid RequestHospedajeDTO payload) {
        return ResponseEntity
                .status(200)
                .body(service.cotizar(payload));
    }
}
