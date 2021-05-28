package com.palmieri;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

    @RunWith(JUnitPlatform.class)
    @SelectPackages("com.palmieri")
    @ExcludePackages("com.palmieri.unit.TestCrudFile")
    public class TestSuite {

    }


