package dao;

// es la implementación de métodos abstractos de diversas familias
import java.util.List;

public interface ICRUD<Generic> {

    void registrar(Generic obj) throws Exception;

    void modificar(Generic obj) throws Exception;

    void eliminar(Generic obj) throws Exception;
    
    void cambiarEstado(Generic obj) throws Exception;
    
//    void eliminar(int codigo) throws Exception;

    List<Generic> listarTodos() throws Exception;
    
    List<Generic> listarTodos(char estado) throws Exception;
}
