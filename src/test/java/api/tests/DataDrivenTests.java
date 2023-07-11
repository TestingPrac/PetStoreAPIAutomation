package api.tests;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String userId,String userName,String fName,String lName,String email,String pword,String phoneNum)
    {
        System.out.println(userId);
        User userPayload = new User();


        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(email);
        userPayload.setPassword(pword);
        userPayload.setPhone(phoneNum);

        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
    }


    @Test(priority = 2,dataProvider = "userNames",dataProviderClass = DataProviders.class)
    public void testDeleteByUserName(String userName)
    {
        System.out.println(userName);
        Response response = UserEndpoints.deleteUser(userName);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
    }
}
