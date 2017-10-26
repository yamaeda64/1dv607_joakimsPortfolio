package view;

import controller.CRUDController;

/**
 * Created by joakimbergqvist on 2017-10-19.
 */
public class UserFactory
{
    public UserStrategy getAdminUser(CRUDController controller)
    {
        return new AdminUserStrategy(controller);
    }
    
    public UserStrategy getMemberUser(CRUDController controller)
    {
        return new MemberUserStrategy(controller);
    }
}
