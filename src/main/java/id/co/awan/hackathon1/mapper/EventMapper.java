package id.co.awan.hackathon1.mapper;

import id.co.awan.hackathon1.model.dto.GetEvent;
import id.co.awan.hackathon1.model.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    //    @Mapping(source = "numberOfSeats", target = "seatCount")
    GetEvent toDTO(Event user);
}
