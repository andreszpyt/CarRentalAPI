package com.car.infra.presentation;

import com.car.core.entities.Costumer;
import com.car.core.usecases.costumer.commands.RegisterCostumerUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final RegisterCostumerUseCase registerCostumerUseCase;

    public UserController(RegisterCostumerUseCase registerCostumerUseCase) {
        this.registerCostumerUseCase = registerCostumerUseCase;
    }

    @PostMapping
    public Costumer registerCostumer(@RequestBody Costumer costumer) {
        return registerCostumerUseCase.execute(costumer);
    }
}
