package engineer.abf.agegadordeinvestimentos.controller;

import engineer.abf.agegadordeinvestimentos.controller.dto.UserDto;
import engineer.abf.agegadordeinvestimentos.entity.UserEntity;
import engineer.abf.agegadordeinvestimentos.model.UserFilter;
import engineer.abf.agegadordeinvestimentos.model.UserModel;
import engineer.abf.agegadordeinvestimentos.model.adapter.UserAdapter;
import engineer.abf.agegadordeinvestimentos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser (@RequestBody UserDto userDto) {
        final var userModel = UserAdapter.adptDtoToModel(userDto);
        final var userSaved = userService.createUser(userModel);
       // final var userEntitySaved = UserAdapter.adptModelToEntity(userSaved);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto>update(@PathVariable Long id, @RequestBody UserDto userDto) {
        var resp = userService.updateUser(id, userDto);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<UserDto>> listarTodos (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size){

        return ResponseEntity.status(HttpStatus.OK).body(userService.listarUser(page, size));
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> findUserByFilter(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size) {

            final var userFilter = UserFilter.builder()
                    .userId(userId)
                    .username(username)
                    .email(email)
                    .password(password)
                    .build();
           final var user = userService.find(userFilter, PageRequest.of(page, size))
                   .map(UserDto::new);
        return ResponseEntity.ok(user);

          //  return ResponseEntity.ok(userService.find(userFilter, PageRequest.of(page, size)));

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void>deleteById(@PathVariable("userId") Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
