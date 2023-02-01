package com.cartonesa.control.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.cartonesa.control.interfaceService.IUsersService;
import com.cartonesa.control.interfaces.IUser;
import com.cartonesa.control.modelo.Users;

public class UserService implements IUsersService{
	
	@Autowired
	private IUser data;
/*
	  @Override
		public List<Users> listar() {
			return (List<Users>)data.findAll();
		}
		

		@Override
		public Optional<Users> listarId(int id) {
			// TODO Auto-generated method stub
			return data.findById(id);
		}
	
		
		@Override
		public int save(Users a) {
			int res=0;
			Users user=data.save(a);
			if(!user.equals(null)) {
				res=1;
			}
			return res;
		}

		
		@Override
		public void delete(int id) {
			data.delete(null);
		}

		@Override
		public Optional<Users> listarId(int id) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}


		@Override
		public List<Users> findByUsername(String username) {
			// TODO Auto-generated method stub
			return data.findByUsername(username);
		}*/
	    
	    

}
