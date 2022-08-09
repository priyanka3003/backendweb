package CJV805.BackEnd.Services;

import CJV805.BackEnd.Models.UserModel;
import CJV805.BackEnd.Models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    public UserModel addUser(UserModel user){
        UserModel u = repository.findByUsername(user.getUsername());
        if(u==null) {
            String encodedPassword = bcrypt.encode(user.getPassword());
            user.setPassword(encodedPassword);

            UserModel insertedUser = repository.insert(user);

            return insertedUser;
        } else{
            return null;
        }
    }

    public List<UserModel> getUsers(){
        return repository.findAll();
    }

    public Optional<UserModel> getUser(String id){
        return repository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel foundUser = repository.findByUsername(username);

        String userN = foundUser.getUsername();
        String passW = foundUser.getPassword();
        return new User(userN, passW, new ArrayList<>());
    }
}
