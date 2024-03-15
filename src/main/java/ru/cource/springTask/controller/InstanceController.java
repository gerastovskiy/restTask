package ru.cource.springTask.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.cource.springTask.dto.Response;
import ru.cource.springTask.dto.InstanceRequest;
import ru.cource.springTask.dto.InstanceResponse;
import ru.cource.springTask.service.InstanceService;


@RestController
public class InstanceController {
    private InstanceService instanceService;

    @Autowired
    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    @GetMapping(value = "/corporate-settlement-instance/create")
    public ResponseEntity<Response<InstanceResponse>> create(@RequestBody @Valid InstanceRequest request) {

        var response = new Response<InstanceResponse>(instanceService.processRequest(request));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
