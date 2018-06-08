package Controller.DAOEntities;

import Model.Items;
import Model.Orders;
import Model.Users;

import javax.persistence.NoResultException;
import java.util.List;

public class OrdersController extends DAO<Orders> {
    public OrdersController() {
        super();
    }

    public List<Orders> getOrders(Users user){
        try{
            List<Orders> ordersList = entityManager.createQuery("FROM Orders WHERE user_id = :var", Orders.class)
                    .setParameter("var", user.getUserId())
                    .getResultList();
            return ordersList;
        } catch (NoResultException e){
            return null;
        }
    }

    public Orders getOrderById(Integer orderId) {
        try {
            Orders order = entityManager.createQuery("FROM Orders WHERE order_id = :var", Orders.class)
                    .setParameter("var", orderId)
                    .getSingleResult();
            return order;
        } catch (NoResultException e){
            return null;
        }
    }

    public void deleteItemById(Integer orderId) {
        Orders order = entityManager.createQuery("FROM Orders WHERE order_id = :var", Orders.class)
                .setParameter("var", orderId)
                .getSingleResult();
        delete(order);
    }
}
