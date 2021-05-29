/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;


public interface CRUD<Generica> {

    void registrar(Generica g) throws Exception;

    void modificar(Generica g) throws Exception;

    void eliminar(Generica g) throws Exception;

    List<Generica>listar() throws Exception;
}
