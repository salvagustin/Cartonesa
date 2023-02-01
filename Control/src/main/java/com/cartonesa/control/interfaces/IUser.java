package com.cartonesa.control.interfaces;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cartonesa.control.modelo.Users;

@Repository
public interface IUser extends JpaRepository<Users, Integer>  {
	
	//REPOSITORIO QUE CONSULTA A LA BASE PARA BUSCAR USUARIOS POR USUARIO
    public Optional<Users> findByUsername(String username);

	//List<Users>findByUsername(String username);
}