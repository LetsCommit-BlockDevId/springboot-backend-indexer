package id.co.awan.hackathon1.mapper;

import id.co.awan.hackathon1.dto.GetEventResponse;
import id.co.awan.hackathon1.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface  EventMapper {

    EventMapper INSTANCE = Mappers.getMapper( EventMapper.class );

//    @Mapping(source = "numberOfSeats", target = "seatCount")
    GetEventResponse toDTO(Event user);
}
