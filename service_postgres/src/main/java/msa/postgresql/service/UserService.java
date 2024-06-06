package msa.postgresql.service;

import msa.postgresql.entity.UserEntity;
import msa.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> saveUser(List<UserEntity> userEntities) {
        return userRepository.saveAll(userEntities);
    }

    public UserEntity updateUserById(Long id, String email) {
        return userRepository.findById(id).map(userEntity -> {
            userEntity.setEmail(email);
            return userRepository.save(userEntity);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public UserEntity updateUserByName(String name, String email) {
        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findByName(name));
        if (userOpt.isPresent()) {
            UserEntity userEntity = userOpt.get();
            userEntity.setEmail(email);
            return userRepository.save(userEntity);
        } else {
            throw new RuntimeException("User not found with name " + name);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
