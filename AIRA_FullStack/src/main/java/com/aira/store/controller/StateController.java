package com.aira.store.controller;
import com.aira.store.service.StateService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/state")
public class StateController {
    private final StateService stateService;
    public StateController(StateService stateService) { this.stateService = stateService; }
    @GetMapping public JsonNode getState() { return stateService.getState(); }
    @PutMapping public JsonNode saveState(@RequestBody JsonNode payload) { return stateService.saveState(payload); }
}
