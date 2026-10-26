package com.aira.store.service;
import com.aira.store.entity.AppState;
import com.aira.store.repository.AppStateRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
@Service
public class StateService {
    private static final long STATE_ID = 1L;
    private final AppStateRepository repository;
    private final ObjectMapper mapper;
    public StateService(AppStateRepository repository, ObjectMapper mapper) { this.repository = repository; this.mapper = mapper; }
    @Transactional(readOnly = true)
    public JsonNode getState() {
        AppState state = repository.findById(STATE_ID).orElse(null);
        if (state == null) return null;
        try { return mapper.readTree(state.getStateJson()); }
        catch (Exception e) { throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Saved AIRA data could not be read."); }
    }
    @Transactional
    public JsonNode saveState(JsonNode payload) {
        if (payload == null || !payload.isObject()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A valid AIRA data object is required.");
        try {
            String json = mapper.writeValueAsString(payload);
            repository.save(new AppState(STATE_ID, json));
            return mapper.readTree(json);
        } catch (Exception e) { throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "AIRA data could not be saved."); }
    }
}
