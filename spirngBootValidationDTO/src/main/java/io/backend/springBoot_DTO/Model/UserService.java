package io.backend.springBoot_DTO.Model;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLocationMapper userLocationMapper;
    @Autowired
    private UserMapper userMapper;


    public UserWithOrdersDTO getUserWithOrders(Long userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        return userMapper.userToUserWithOrdersDTO(user);
    }
    public UserDTO createUser(@Valid UserDTO userDTO){
        User user=userMapper.userDTOToUsers(userDTO);
        User savedUser=userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }
    public UserDTO updateUser(Long id,@Valid UserDTO userDTO){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User updatedUser = userMapper.userDTOToUser(userDTO);
        updatedUser.setId(id);

        User savedUser = userRepository.save(updatedUser);
        return userMapper.userToUserDTO(savedUser);
    }




    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }
    private UserLocationDTO convertEntityToDto(User user){
        UserLocationDTO userLocationDTO=new UserLocationDTO();
        userLocationDTO.setUserId(user.getId());
        userLocationDTO.setEmail(user.getEmail());
        userLocationDTO.setPlace(user.getLocation().getPlace());
        userLocationDTO.setLongitude(user.getLocation().getLongitude());
        userLocationDTO.setLatitude(user.getLocation().getLatitude());
        return userLocationDTO;
    }



}
