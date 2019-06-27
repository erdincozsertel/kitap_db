package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.User;

public class UserDao implements Dao<User> {
	//This will be database instead of list
	private List<User> users = new ArrayList<>();
    
//    public UserDao() {
//        users.add(new User("kullaniciadi", "sifre"));
//                
//    }

	@Override
	public Optional<User> get(long id) {
		//Anlamadim
		return Optional.ofNullable(users.get((int) id));
	}

	@Override
	public List<User> getAll() {
		//return all users in db
		return users;
	}

	@Override
	public void save(User user) {
		//will move from Register.java
		users.add(user);
	}

	@Override
	public void update(User user, String[] params) {
		//This function will update user data in db
		user.setUsername(Objects.requireNonNull(
				params[0], "Name cannot be null"));
		user.setPassword(Objects.requireNonNull(
				params[1], "Password cannot be null"));
              users.remove(user); 
              users.add(user);
				
	}

	@Override
	public void delete(User user) {
		//This function will delete user from db
		users.remove(user);		
	}
     


}
