package com.palmieri;

import com.palmieri.unit.DynamicFactory;
import com.palmieri.unit.TestCrudFile;
import com.palmieri.unit.TestCrudRubrica;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

    @RunWith(JUnitPlatform.class )
    @SelectPackages("com.palmieri.unit")
    @ExcludePackages("DynamicFactory")
    public class TestSuite {

    }


