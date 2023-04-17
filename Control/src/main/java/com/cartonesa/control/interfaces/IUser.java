package com.cartonesa.control.interfaces;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.User;
import java.util.List;

@Repository
public interface IUser extends JpaRepository<User, Integer>  {
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR USUARIOS POR USUARIO
	public Optional<User> findByUsername(String username);
    
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR USUARIOS POR USUARIO
	@Query("SELECT u FROM User u WHERE u.username =  :username")
	public List<User> findByUsernamee(@Param("username") String username);

	//REPOSITORIO QUE LISTA LOS USUARIOS EXISTENTES
	public List <User>findAll();
	
}