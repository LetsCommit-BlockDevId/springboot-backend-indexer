package id.co.awan.hackathon1.dto;

import lombok.Getter;

@Getter
public enum EventState {

    ON_SALE("onsale"), ON_GOING("ongoing"), FINISHED("finished");

    final String value;

    EventState(String value) {
        this.value = value;
    }
}
