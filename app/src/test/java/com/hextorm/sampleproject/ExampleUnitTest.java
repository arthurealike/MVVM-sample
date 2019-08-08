package com.hextorm.sampleproject;

import com.hextorm.sampleproject.article.ArticleViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    TestViewModel viewModel;

    @Before
    public void init() {
        //viewModel = new ArticleViewModel();
    }

    @Test
    public void test() {
        TestRepository testRepository = new TestRepository();
        assertNotNull(testRepository.getData());
    }

    class TestRepository {
        DataTypeA dataTypeA;
        DataTypeB dataTypeB;

        public TestRepository() {
            dataTypeA = new DataTypeA(8);
            dataTypeB = new DataTypeB(11);
        }

        DataTypeC getData() {
            if(dataTypeB==null) {
                return dataTypeA.create();
            }
            else {
                return dataTypeB.create();
            }
        }
    }

    class DataTypeC {
        int data;
        public DataTypeC(int data) {
            this.data = data;
        }
    }

    class DataTypeA implements IDataType {
        int data;
        public DataTypeA(int data) {
            this.data = data;
        }
        @Override
        public DataTypeC create() {
            return new DataTypeC(data);
        }
    }

    class DataTypeB implements IDataType {
        int data;
        public DataTypeB(int data) {
            this.data = data;
        }
        @Override
        public DataTypeC create() {
            return new DataTypeC(data);
        }
    }

    interface IDataType {
        DataTypeC create();
    }


}