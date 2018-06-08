package Controller.DAOEntities;

import Model.Items;
import Model.Users;

import javax.persistence.NoResultException;
import java.util.List;

public class ItemsController extends DAO <Items> {

    public ItemsController() {
        super();
    }

    public List<Items> getItems(Users user){
        try{
            List<Items> itemsList = entityManager.createQuery("FROM Items WHERE user_id = :var", Items.class)
                    .setParameter("var", user.getUserId())
                    .getResultList();
            return itemsList;
        } catch (NoResultException e){
            return null;
        }
    }
    public void deleteItemById(Integer itemId){
        Items item = entityManager.createQuery("FROM Items WHERE item_id = :var", Items.class)
                .setParameter("var", itemId)
                .getSingleResult();
        delete(item);
    }
    public Items getItemById(Integer itemId){

        try {
        Items item = entityManager.createQuery("FROM Items WHERE item_id = :var", Items.class)
                .setParameter("var", itemId)
                .getSingleResult();
        return item;
            } catch (NoResultException e){
                return null;
                }
            }
}
