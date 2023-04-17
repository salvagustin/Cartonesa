package com.cartonesa.control.interfaceService;

import java.util.List;
import java.util.Optional;

import com.cartonesa.control.modelo.User;

public interface IUsersService {
	
	//METODOS CRUD DE LOS USUARIOS
	public List<User>listar();
	public Optional<User>listarId(int id);	
	public int save(User u);	
	public void delete(int id);	
	
	//METODOS PARA BUSCAR REGISTROS POR NOMBRE DE USUARIO
	public List<User> findByUsernamee(String username);
}
