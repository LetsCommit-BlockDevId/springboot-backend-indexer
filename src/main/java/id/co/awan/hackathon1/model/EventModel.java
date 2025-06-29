package id.co.awan.hackathon1.model;

import java.time.Instant;
import java.util.List;

public record EventModel(
        String eventAddress,
        String title,
        String imageUri,
        String description,
        String priceAmount,
        String commitmentAmount,
        Instant saleStart,
        Instant saleEnd,
        List<String> session
) {
}
