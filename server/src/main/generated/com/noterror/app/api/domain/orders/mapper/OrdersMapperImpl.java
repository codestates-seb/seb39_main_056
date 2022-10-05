package com.noterror.app.api.domain.orders.mapper;

import com.noterror.app.api.domain.entity.order.Orders;
import com.noterror.app.api.domain.orders.dto.OrderResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-03T18:55:42+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Azul Systems, Inc.)"
)
@Component
public class OrdersMapperImpl implements OrdersMapper {

    @Override
    public List<OrderResponseDto> ordersToOrderResponseDtos(List<Orders> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponseDto> list = new ArrayList<OrderResponseDto>( orders.size() );
        for ( Orders orders1 : orders ) {
            list.add( ordersToOrdersResponseDto( orders1 ) );
        }

        return list;
    }
}
