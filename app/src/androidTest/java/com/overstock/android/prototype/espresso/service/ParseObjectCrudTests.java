package com.overstock.android.prototype.espresso.service;

import android.support.test.rule.ActivityTestRule;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author I Towey Created on 01-Mar-16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParseObjectCrudTests{

    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class, true, false);

    @Before
    public void setUp(){
        activityTestRule.launchActivity(null);
    }

    @Test
    public void a_createTest() throws ParseException {
        onView(withId(R.id.facebook_login_btn)).check(matches(isDisplayed()));
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("msg", "hello");
        testObject.put("username", "eurowhite");
        testObject.save();
    }

    @Test
    public void b_retrieveTest() throws ParseException {
        onView(withId(R.id.facebook_login_btn)).check(matches(isDisplayed()));
        ParseQuery testQuery = new ParseQuery("TestObject");
        testQuery.whereEqualTo("username","eurowhite").whereEqualTo("msg", "hello");
        List<ParseObject> parseObjects= testQuery.find();
        ParseObject po = parseObjects.get(0);
        Assert.assertEquals(po.get("username"), "eurowhite");
    }

    @Test
    public void c_updateTest() throws ParseException {
        onView(withId(R.id.facebook_login_btn)).check(matches(isDisplayed()));
        ParseQuery testQuery = new ParseQuery("TestObject");
        testQuery.whereEqualTo("username", "eurowhite").whereEqualTo("msg", "hello");
        List<ParseObject> parseObjects= testQuery.find();
        Assert.assertEquals(parseObjects.size(), 1);
        ParseObject po = parseObjects.get(0);
        po.put("username", "eurowhite@overstock.com");
        po.save();
        testQuery.whereEqualTo("username","eurowhite@overstock.com").whereEqualTo("msg", "hello");
        parseObjects= testQuery.find();
        Assert.assertEquals(parseObjects.size(), 1);
    }

    @Test
    public void d_deleteTest() throws ParseException {
        onView(withId(R.id.facebook_login_btn)).check(matches(isDisplayed()));
        ParseQuery testQuery = new ParseQuery("TestObject");
        testQuery.whereEqualTo("username","eurowhite@overstock.com").whereEqualTo("msg", "hello");
        List<ParseObject> parseObjects= testQuery.find();
        Assert.assertEquals(parseObjects.size(),1);
        ParseObject po =  parseObjects.get(0);
        po.delete();
        testQuery.whereEqualTo("username","eurowhite@overstock.com").whereEqualTo("msg", "hello");
        parseObjects= testQuery.find();
        Assert.assertEquals(parseObjects.size(),0);

    }

}
