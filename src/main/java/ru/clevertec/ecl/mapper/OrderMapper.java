package ru.clevertec.ecl.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.ecl.dto.OrderDTO;
import ru.clevertec.ecl.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toOrderDTO(Order order);
}
