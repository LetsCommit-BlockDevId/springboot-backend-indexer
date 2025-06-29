package id.co.awan.hackathon1.model;

import java.time.Instant;
import java.util.List;

public record SessionModel(
        String eventAddress,
        Integer number,
        Instant sessionStart,
        Instant sessionEnd,
        List<String> attendances
) {
}
