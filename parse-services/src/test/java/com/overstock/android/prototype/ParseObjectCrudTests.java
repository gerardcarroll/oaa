package com.overstock.android.prototype;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import junit.framework.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

/**
 * @author I Towey Created on 01-Mar-16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParseObjectCrudTests{


    @Test
    public void a_createTest() throws ParseException {
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("msg", "hello");
        testObject.put("username", "eurowhite");
        testObject.save();
    }

    @Test
    public void b_retrieveTest() throws ParseException {
        ParseQuery testQuery = new ParseQuery("TestObject");
        testQuery.whereEqualTo("username","eurowhite").whereEqualTo("msg", "hello");
        List<ParseObject> parseObjects= testQuery.find();
        ParseObject po = parseObjects.get(0);
        Assert.assertEquals(po.get("username"), "eurowhite");
    }

    @Test
    public void c_updateTest() throws ParseException {
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
