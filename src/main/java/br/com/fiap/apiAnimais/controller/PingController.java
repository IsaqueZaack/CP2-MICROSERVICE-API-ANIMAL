package br.com.fiap.apiAnimais.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class PingController {

    @GetMapping("ping")
    public String ping() {
        return "pong"; // Resposta padrão
    }

    @GetMapping("rota1")
    public String rota1() {
        return "rota1"; // Resposta padrão
    }

    @GetMapping("rota2")
    public String rota2() {
        return "rota2"; // Resposta padrão
    }

    @GetMapping("rota3/test")
    public String rota3() {
        return "rota3"; // Resposta padrão
    }
}
