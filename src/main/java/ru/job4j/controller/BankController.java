package ru.job4j.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.service.BankService;

import java.util.Map;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;


    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public void transfer(@RequestBody Map<String, String> body) {
        var srcPassport = body.get("srcPassport");
        var srcRequisite = body.get("srcRequisite");
        var destPassport = body.get("destPassport");
        var destRequisite = body.get("destRequisite");
        var amount = Double.parseDouble(body.get("amount"));
        bankService.transferMany(srcPassport, srcRequisite, destPassport, destRequisite, amount);
    }
}
