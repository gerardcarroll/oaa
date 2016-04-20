package com.overstock.android.prototype.parse.services;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by itowey on 15/04/16.
 */

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParseUserTests {

    private static final String username = "euro-white-test" ;
    private static final String password = "eurowh1te-test" ;
    private static final String email = "eurowhitetest@overtock.com";
    private static final String TAG = ParseUserTests.class.getName();


    @Before
    public void setUp(){
        ParseQuery.clearAllCachedResults();
        ParseUser user = ParseUser.getCurrentUser();
        user.logOut();
    }

    @Test
    public void a_useSignUpTest() throws ParseException {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        //custom fields
        user.put("type","service");
        user.put("location", "sligo");
        user.signUp();
        user.logOut();
        Assert.assertTrue(true);
    }

    @Test
    public void b_loginTest() throws ParseException {
        ParseUser user = ParseUser.logIn(username, password);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), email);
        user.logOut();
        //SystemClock.sleep(3000L);
    }

    @Test(expected = Exception.class)
    public void c_deleteUserTest() throws ParseException {

        ParseUser user = ParseUser.logIn(username, password);
        user.delete();
        user = ParseUser.logIn(username, password);

    }

    @Test
    public void d_guestTest() throws ParseException {

        ParseUser parseUser;
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    //Log.i(TAG, "Anonymous login failed.");
                } else {
                    Assert.assertNotNull(user.getSessionToken());
                    //Log.i(TAG, "Anonymous user logged in wiuth session token " + user.getSessionToken());
                    try {
                        user.delete();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        //SystemClock.sleep(3000);
    }

    @Test
    public void e_guestUserToSignedUserTest() throws ParseException {

        ParseUser parseUser;
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    //Log.i(TAG, "Anonymous login failed.");
                } else {
                    Assert.assertNotNull(user.getSessionToken());
                    //Log.i(TAG, "Anonymous user logged in wiuth session token " + user.getSessionToken());
                    user.setUsername("guest");
                    user.setPassword("guest1");
                    user.setEmail("guest@overstock");
                    try {
                        user.signUp();
                        user.logOut();

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        //SystemClock.sleep(3000);
    }

    @Test(expected = Exception.class)
    public void f_deleteUserTest() throws ParseException {

        ParseUser user = ParseUser.logIn("guest", "guest1");
        user.delete();
        user = ParseUser.logIn(username, password);

    }

}

