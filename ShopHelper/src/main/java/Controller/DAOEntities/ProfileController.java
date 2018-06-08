package Controller.DAOEntities;

import Model.Profile;
import Model.Users;

import javax.persistence.NoResultException;

public class ProfileController extends DAO <Profile> {

    public ProfileController() {
        super();
    }

    public Profile getProfileByUserId(Users user) {
        try {
            Profile profile = entityManager.createQuery("FROM Profile WHERE user_id = :var", Profile.class)
                    .setParameter("var", user.getUserId())
                    .getSingleResult();
            return profile;
        } catch (NoResultException e) {
            return null;
        }
    }
}
