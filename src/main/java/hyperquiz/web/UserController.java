package hyperquiz.web;

import hyperquiz.exceptions.EntityUpdateException;
import hyperquiz.model.User;
import hyperquiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "hyperquiz/users",produces = APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService us;

    @GetMapping
    public List<User> getUsers(){
         return us.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id){
        return us.findUserByID(id);
    }

    @DeleteMapping("/{id}")
    public User deleteUserByID(@PathVariable Long id){
        return us.deleteUserByID(id);
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user){
        return us.addUser(user);
    }

    @PutMapping(path = "/{id}")
    public User updateUser(@Valid @RequestBody User user,@PathVariable Long id){
        if(!id.equals(user.getId())){
            throw new EntityUpdateException("Error updating user with ID: "+user.getId());
        }
       return us.updateUser(user);
    }


}
