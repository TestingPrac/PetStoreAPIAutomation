package api.tests;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker= new Faker();
    User userPayload= new User();

    public Logger logger;

    @BeforeClass
    public void setUpData()
    {

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser()
    {
        logger.info("********* Creating User *********");
        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
        System.out.println(userPayload.getFirstName());
        logger.info("********* Created User Successfully *********");
    }

    @Test(priority = 2)
    public void testGetUserByName()
    {
        logger.info("********* Getting User Details *********");
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName()
    {
        logger.info("********* Updating the User Details *********");
        //Update data using payload
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());

        Response response = UserEndpoints.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);

        //Checking data after update
        Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();

        logger.info("********* Successfully Updated the User Details *********");
    }

    @Test(priority = 4)
    public void testDeleteUserByName()
    {
        logger.info("********* Deleting the User *********");
        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
        logger.info("********* Successfully Deleted the User *********");
    }
}
