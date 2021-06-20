package hyperquiz.web;

import com.fasterxml.jackson.annotation.JsonView;
import hyperquiz.exceptions.EntityUpdateException;
import hyperquiz.model.User;
import hyperquiz.services.UserService;
import hyperquiz.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "hyperquiz/users",produces = APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService us;

    @GetMapping
    @JsonView(View.UserView.External.class)
    public List<User> getUsers(){
         return us.getAllUsers();
    }

    @GetMapping("/internal")
    @JsonView(View.UserView.Internal.class)
    public List<User> getUsersInternal(){
        return us.getAllUsers();
    }

    @JsonView(View.UserView.External.class)
    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id){
        return us.findUserByID(id);
    }

    @DeleteMapping("/{id}")
    public User deleteUserByID(@PathVariable Long id){
        return us.deleteUserByID(id);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User created = us.addUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .pathSegment("{id}")
                        .buildAndExpand(created.getId())
                        .toUri() ).body(created);
    }

    @PutMapping(path = "/{id}")
    public User updateUser(@Valid @RequestBody User user,@PathVariable Long id){
        if(!id.equals(user.getId())){
            throw new EntityUpdateException("IDs don't match: "+user.getId()+" != "+id);
        }

       return us.updateUser(user);
    }

    @GetMapping("/usernames/{username}")
    @JsonView(View.UserView.External.class)
    public User getUserByUsername(@PathVariable String username){
        return us.findUserByUsername(username);
    }
}
