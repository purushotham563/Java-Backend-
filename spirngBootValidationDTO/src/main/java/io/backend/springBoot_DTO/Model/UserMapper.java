package io.backend.springBoot_DTO.Model;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
    LocationDTO locationToLocationDTO(Location location);
    Location locationDTOToLocation(LocationDTO locationDTO);
    UserWithOrdersDTO userToUserWithOrdersDTO(User user);
    User userWithOrdersDTOToUser(UserWithOrdersDTO userWithOrdersDTO);
    OrderDTO orderToOrderDTO(Order order);
    Order orderDTOToOrder(OrderDTO orderDTO);
    List<UserDTO> usersToUsersDTO(List<User>users);
    User userDTOToUsers( UserDTO userDTOS);
    List<OrderDTO> ordersToOrderDTOs(List<Order> orders);
    List<Order> orderDTOsToOrders(List<OrderDTO> orderDTOs);

}
