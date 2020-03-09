package org.example;

import org.example.util.DBUtil;
import org.junit.Assert;
import org.junit.Test;

public class DBUtilTest {

    @Test
    public void testConnection(){
        Assert.assertNotNull(DBUtil.getConnection());
    }
}
