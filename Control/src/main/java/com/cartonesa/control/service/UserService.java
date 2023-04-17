package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cartonesa.control.interfaceService.IUsersService;
import com.cartonesa.control.interfaces.IUser;
import com.cartonesa.control.modelo.User;

@Service
public class UserService implements IUsersService{
	
	@Autowired
	private IUser data;

	@Override
	public List<User> listar() {
	
		return (List<User>)data.findAll();
	}
	  
	
	
	@Override
	public int save(User u) {
	int res=0;
	String contra = u.getPassword();
		
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
	//El String que mandamos al metodo encode es el password que queremos encriptar.
	String passecrip = bCryptPasswordEncoder.encode(contra);
	
	u.setPassword(passecrip);				
	User user=data.save(u);
	if(!user.equals(null)) {
		res=1;
	}
	return res;
	}
	  
	 @Override
	 public Optional<User> listarId(int id) {
	  // TODO Auto-generated method stub
	 return data.findById(id);
	  }

	 @Override	
	 public void delete(int id) {
		data.deleteById(id);
		
	 }
		

	@Override
	public List<User> findByUsernamee(String username) {
		// TODO Auto-generated method stub
	return data.findByUsernamee(username);
	}
	    
	    

}
