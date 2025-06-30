package id.co.awan.hackathon1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.awan.hackathon1.model.dto.EventState;
import id.co.awan.hackathon1.model.dto.GetEvent;
import id.co.awan.hackathon1.model.dto.GetEventDetailEO;
import id.co.awan.hackathon1.model.dto.GetEventDetailP;
import id.co.awan.hackathon1.repository.EventRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

//    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;

    // DONE
    @Operation(
            summary = "Mendapatkan data event berdasarkan state"
    )
    @GetMapping
    public ResponseEntity<List<GetEvent>> getEvents(
            @RequestParam(name = "state")
            EventState eventState
    ) {
        try {
            List<GetEvent> getEvents = objectMapper.readValue("""
                      [{
                        "eventId": 0,
                        "title": "DeFi Fundamental Mastercllass",
                        "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
                        "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
                        "priceAmount": 60,
                        "commitmentAmount": 50,
                        "totalAmount": 110,
                        "startSaleDate": 1751295986,
                        "endSaleDate": 1751299986,
                        "organizer": "string",
                        "location": "string",
                        "participant": 10,
                        "maxParticipant": 20,
                        "status": "ON_SALE"
                      }]
                    """, new TypeReference<List<GetEvent>>() {
            });

            return ResponseEntity.ok(getEvents);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Operation(
            summary = "Data event pada participant view"
    )
    @GetMapping(path = "{eventId}")
    public ResponseEntity<GetEventDetailEO> getEventDetailViewEventOrganizer(
            @PathVariable(name = "eventId")
            BigInteger eventId
    ) {
        try {
            GetEventDetailEO getEventDetailEO = objectMapper.readValue("""
                      {
                      "eventId": 0,
                      "title": "DeFi Fundamental Mastercllass",
                      "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
                      "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
                      "priceAmount": 60,
                      "commitmentAmount": 50,
                      "totalAmount": 110,
                      "startSaleDate": 1751295986,
                      "endSaleDate": 1751299986,
                      "organizer": "string",
                      "location": "string",
                      "participant": 10,
                      "maxParticipant": 20,
                      "status": "ONGOING",
                      "canWithdraw" : false,
                      "session": [
                        {
                          "sessionNumber": 0,
                          "title": "DeFi Fundamental Mastercllass",
                          "startSession": 1751295986,
                          "endSession": 1751295986,
                          "durationInHours": 1,
                          "durationInMinute": 60,
                          "peopleAttend": 0,
                          "status": "UPCOMING|RUNNING|COMPLETE|UNATENDED",
                          "activeQrButton": true
                        }
                      ],
                      "statistic": {
                        "totalRevenue": 1,
                        "sessionCompleted": 1,
                        "sessionLength": 5,
                        "avgAttendanceRatePercent": 10
                      }
                    }
                    """, new TypeReference<GetEventDetailEO>() {
            });

            return ResponseEntity.ok(getEventDetailEO);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Operation(
            summary = "Data event pada event organizer view"
    )
    @GetMapping(path = "{eventId}/{participantAddress}")
    public ResponseEntity<GetEventDetailP> getEventDetailViewParticipant(
            @PathVariable(name = "eventId")
            BigInteger eventId,

            @PathVariable(name = "participantAddress")
            String participantAddress
    ) {
        try {
            GetEventDetailP getEventDetailP = objectMapper.readValue("""
                        {
                        "eventId": 0,
                        "title": "DeFi Fundamental Mastercllass",
                        "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
                        "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
                        "priceAmount": 60,
                        "commitmentAmount": 50,
                        "totalAmount": 110,
                        "startSaleDate": 1751295986,
                        "endSaleDate": 1751299986,
                        "organizer": "string",
                        "location": "string",
                        "participant": 10,
                        "maxParticipant": 20,
                        "status": "ONGOING",
                        "session": [
                          {
                            "sessionNumber": 0,
                            "title": "DeFi Fundamental Mastercllass",
                            "startSession": 1751295986,
                            "endSession": 1751295986,
                            "durationInHours": 1,
                            "durationInMinute": 60,
                            "peopleAttend": 0,
                            "status": "UPCOMING|RUNNING|COMPLETE|UNATENDED"
                          }
                        ],
                        "statistic": {
                          "sessionAttend": 1,
                          "sessionLength": 5,
                          "availableCommitment": 10
                        }
                      }
                    """, new TypeReference<GetEventDetailP>() {
            });

            return ResponseEntity.ok(getEventDetailP);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


//    @Operation(
//            summary = "Mendapatkan detail dari event"
////            description = "Ini Deskripsi"
////            responses = {
////                    @ApiResponse(
////                            description = "Successful Operation",
////                            responseCode = "200",
////                            content = @Content(
////                                    mediaType = "application/json",
////                                    schema = @Schema(implementation = GetEventResponse.class)
////                            )
////                    )
////            }
//    )
//    @GetMapping(path = "{eventId}")
//    public ResponseEntity<List<GetEvent>> getEventDetail(
//            @PathVariable(name = "eventId")
//            @Parameter(description = "Berisi Event ID")
//            BigInteger eventId
//    ) {
//
//
//        try {
//            List<GetEvent> getEvents = objectMapper.readValue("""
//                      [{
//                        "eventId": 0,
//                        "title": "DeFi Fundamental Mastercllass",
//                        "description": "Learn the basics of decentralized finance, liquidity pools, and yield farming in this comprehensive 3-day workshop.",
//                        "imageUri": "https://images.unsplash.com/photo-1605810230434-7631ac76ec81?w=400&h=300&fit=crop",
//                        "priceAmount": 60,
//                        "commitmentAmount": 50,
//                        "totalAmount": 110,
//                        "startSaleDate": 1751295986,
//                        "endSaleDate": 1751299986,
//                        "organizer": "string",
//                        "location": "string",
//                        "participant": 10,
//                        "maxParticipant": 20,
//                        "status": "ON_SALE",
//                        "hasRegist": true
//                      }]
//                    """, new TypeReference<List<GetEvent>>() {
//            });
//
//            return ResponseEntity.ok(getEvents);
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

//    }


//    @Operation(
//            summary = "Mendapatkan data event berdasarkan organizer"
//    )
//    @GetMapping(path = "{organizerAddress}")
//    public ResponseEntity<List<Event>> getEventByOrganizer(
//            @PathVariable(name = "organizerAddress")
//            String organizerAddress
//    ) {
//
//        List<Event> collect = eventRepository.findAll()
//                .stream()
//                .filter(event -> event.getOrganizer().equals(organizerAddress))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(collect);
//    }


//    @Operation(
//            summary = "Mendapatkan data event berdasarkan state"
//    )
//    @GetMapping
//    public ResponseEntity<List<GetEvent>> getEvents(
//            @RequestParam(name = "state")
//            EventState eventState
//    ) {
//
//        List<GetEvent> events = eventRepository.findAll()
//                .stream()
//                .filter(event -> {
//
//                    if (eventState.equals(EventState.FINISHED)) {
//
//                        return true;
//                    } else if (eventState.equals(EventState.ON_GOING)) {
//                        // On Going
//                        return Instant.ofEpochSecond(event.getEndSaleDate().longValue())
//                                .isBefore(Instant.now());
//
//                    } else {
//                        // On Sale
//                        return Instant.ofEpochSecond(event.getEndSaleDate().longValue())
//                                .isAfter(Instant.now());
//                    }
//                })
//                .map(EventMapper.INSTANCE::toDTO).collect(Collectors.toList());
//
//        return ResponseEntity.ok(events);
//    }

}
