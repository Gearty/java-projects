package Controller.DAOEntities;
import Model.Users;

import javax.persistence.*;

public class UserController extends DAO <Users> {

    public UserController() {
        super();
    }

    public Users getUserByLogin(String login){
        try{
            Users user = entityManager.createQuery("FROM Users WHERE login = :var", Users.class)
                    .setParameter("var", login)
                    .getSingleResult();
            return user;
        } catch (NoResultException e){
            return null;
        }
    }

}
