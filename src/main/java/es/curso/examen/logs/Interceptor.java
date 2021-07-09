package es.curso.examen.logs;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import es.curso.examen.modelo.Empleado;

@Aspect
@Component
public class Interceptor {
	
	Empleado e;
	
	@Value("src/logs/mi_log_annotation.txt")
	String path;
	
	

	public Empleado getE() {
		return e;
	}

	public void setE(Empleado e) {
		this.e = e;
	}
	
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@After("execution(* es.curso.examen.dao.IEmpleadoDAO.create(..)) and args(e)")
	public void createdUser(Empleado e) {
		FicherosLog.grabarLog("Creado " + e.toString(), path);
	}

	@After("execution(* es.curso.examen.dao.IEmpleadoDAO.update(..)) and args(e)")
	public void updatedUser(Empleado e) {
		FicherosLog.grabarLog("Modificado " + e.toString(), path);
	}
	
	@After("execution(* es.curso.examen.dao.IEmpleadoDAO.delete(..)) and args(codigo)")
	public void deletedUser(int codigo) {
		FicherosLog.grabarLog("Eliminado usuario con codigo:"  + codigo , path);
	}
	
}
