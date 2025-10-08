package org.example.demospringsecurityjsonwebtoken.service;

import org.example.demospringsecurityjsonwebtoken.model.Role;
import org.example.demospringsecurityjsonwebtoken.model.User;
import org.example.demospringsecurityjsonwebtoken.model.UserPrinciple;
import org.example.demospringsecurityjsonwebtoken.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    public static List<User> listUser = new ArrayList<>();
    public static List<Role> listRole = new ArrayList<>();

    public UserService() {
        listRole.add(new Role(1L, "ROLE_ADMIN"));
        listRole.add(new Role(2L, "ROLE_USER"));

        String password = "$2a$10$xMq9EwZvdKUuvgiaM2T1Iuw9A1EGXVZaCIUPEwn1Isa9ffvPqNabe";
        User userKai = new User(1L, "tuananh", password);
        Set<Role> roleKai =  new HashSet<>();
        roleKai.add(listRole.get(0));
        userKai.setRoles(roleKai);
        User userSena = new User(2L, "phuonglinh", password);
        Set<Role> roleSena =  new HashSet<>();
        roleSena.add(listRole.get(1));
        userSena.setRoles(roleSena);
        listUser.add(userKai);
        listUser.add(userSena);
    }

    public List<UserDTO> findAll() {
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (User user : listUser) {
            listUserDTO.add(toDTO(user));
        }
        return listUserDTO;
    }

    public UserDTO findById(Long id) {
        for (User user : listUser) {
            if (Objects.equals(user.getId(), id)) {
                return toDTO(user);
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        for (User user : listUser) {
            if (Objects.equals(user.getUsername(), username)) {
                return user;
            }
        }
        return null;
    }

    public boolean add(User user) {
        for (User userExist : listUser) {
            if (Objects.equals(user.getId(), userExist.getId()) || Objects.equals(userExist.getUsername(), user.getUsername())) {
                return false;
            }
        }
        listUser.add(user);
        return true;
    }

    public UserDetails loadUserByUsername(String username) {
        for (User user : listUser) {
            if (Objects.equals(user.getUsername(), username)) {
                return UserPrinciple.build(user);
            }
        }
        return null;
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }

    public void delete(int id) {
        listUser.removeIf(user -> user.getId() == id);
    }
}
