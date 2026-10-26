package com.aira.store.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "aira_app_state")
public class AppState {
    @Id private Long id = 1L;
    @Column(name = "state_json", columnDefinition = "LONGTEXT", nullable = false)
    private String stateJson;
    public AppState() {}
    public AppState(Long id, String stateJson) { this.id = id; this.stateJson = stateJson; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStateJson() { return stateJson; }
    public void setStateJson(String stateJson) { this.stateJson = stateJson; }
}
