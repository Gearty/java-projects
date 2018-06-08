package Controller.DAOEntities;

import Model.Orders;
import Model.Supplies;
import Model.Users;

import javax.persistence.NoResultException;
import java.util.List;

public class SuppliesController extends DAO <Supplies> {
    public SuppliesController() {
        super();
    }

    public List<Supplies> getSupplies(Users user) {
        try{
            List<Supplies> suppliesList = entityManager.createQuery("FROM Supplies WHERE user_id = :var", Supplies.class)
                    .setParameter("var", user.getUserId())
                    .getResultList();
            return suppliesList;
        } catch (NoResultException e){
            return null;
        }
    }

    public Supplies getSupplyById(Integer supplyId) {

        try {
            Supplies supply = entityManager.createQuery("FROM Supplies WHERE supply_id = :var", Supplies.class)
                    .setParameter("var", supplyId)
                    .getSingleResult();
            return supply;
        } catch (NoResultException e){
            return null;
        }

    }

    public void deleteItemById(Integer supplyId) {

        Supplies supply = entityManager.createQuery("FROM Supplies WHERE supply_id = :var", Supplies.class)
                .setParameter("var", supplyId)
                .getSingleResult();
        delete(supply);
    }
}
