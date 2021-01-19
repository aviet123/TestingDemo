package com.example.demo;


import com.example.demo.model.MyDictionary;
import com.example.demo.model.MyList;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.BDDMockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {

    @Test
    public void whenNotUseMockAnnotation_thenCorrect(){
        List<String> spyList = Mockito.spy(new ArrayList<String>());

        spyList.add("one");
        spyList.add("two");
        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");
        Assert.assertEquals(2, spyList.size());

        Mockito.when(spyList.size()).thenReturn(100);
        Assert.assertEquals(100,spyList.size());
    }

    @Spy
    List<String> spiedList = new ArrayList<>();

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected(){
        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        Assert.assertEquals(2, spiedList.size());

        Mockito.doReturn(100).when(spiedList).size();
        Assert.assertEquals(100,spiedList.size());
    }

    @Mock
    List<Object> list;

    @Captor
    ArgumentCaptor<Object> captor;

    @Test
    public void testCaptor(){
        list.add(1);
        list.contains("ABC");
        Mockito.verify(list).add(captor.capture());
        Assert.assertEquals(1,captor.getValue());
        Mockito.verify(list).contains(captor.capture());
        Assert.assertEquals("ABC",captor.getValue());
    }

    @Mock
    Map<String, String> wordMap;

    @InjectMocks
    MyDictionary dictionary = new MyDictionary();


    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect(){
        Mockito.when(wordMap.get("hello")).thenReturn("lo lo cc");
        Mockito.when((dictionary.getMeaning("very good"))).thenReturn("tot cc");
        Assert.assertEquals("lo lo cc", dictionary.getMeaning("hello"));
        Assert.assertEquals("tot cc", dictionary.getMeaning("very good"));
    }

    @Test(expected = NullPointerException.class)
    public void whenConfigNonVoidReturnMethodToThrowEx_thenExIsThrow(){
        MyDictionary dickMock = mock(MyDictionary.class);
        when(dickMock.getMeaning(anyString()))
                .thenThrow(new NullPointerException("throw exception occur"));

        dickMock.getMeaning("word");
    }

    @Test(expected = IllegalStateException.class)
    public void whenConfigVoidReturnMethodToThrowEx_thenExIsThrow(){
        MyDictionary dickMock = mock(MyDictionary.class);
        doThrow(IllegalStateException.class)
                .when(dickMock).add(anyString(),anyString());
        dickMock.add("word","meaning");
    }

    @Test
    public void whenCallAddMethodVerify(){
        MyList myList = mock(MyList.class);
        doNothing().when(myList).add(isA(Integer.class),isA(String.class));
        myList.add(0,"");

        verify(myList, Mockito.times(1)).add(0,"");
    }
    @Test
    public void whenAddCalledValueCaptured(){
        MyList myList = mock(MyList.class);
        ArgumentCaptor<String> valueCaptor = ArgumentCaptor.forClass(String.class);
        doNothing().when(myList).add(anyInt(),valueCaptor.capture());
        myList.add(0,"captured");

        Assert.assertEquals("captured",valueCaptor.getValue());
    }

    @Test
    public void whenCalledAnswered(){
        MyList myList = mock(MyList.class);
        Mockito.doAnswer(invocation -> {
            Object arg1 = invocation.()[0];
            Object arg2 = invocation.getArguments()[1];

            Assert.assertEquals(3,arg1);
            Assert.assertEquals("answer me", arg2);
            return null;
        }).when(myList).add(anyInt(), anyString());
        myList.add(3,"answer me");
    }











    @Mock
    private List<String> mockList;

    @Spy
    private List<String> spyList = new ArrayList();

    @Test
    public void testMockList() {
        //by default, calling the methods of mock object will do nothing
        mockList.add("test");
        Assert.assertNull(mockList.get(0));
    }

    @Test
    public void testSpyList() {
        //spy object will call the real method when not stub
        spyList.add("test");
//        Mockito.verify(list).add("test");
        Assert.assertEquals("test", spyList.get(0));
    }

    @Test
    public void testMockWithStub() {
        //try stubbing a method
        String expected = "Mock 100";
        Mockito.when(mockList.get(100)).thenReturn(expected);

        Assert.assertEquals(expected, mockList.get(100));
    }

    @Test
    public void testSpyWithStub() {
        //stubbing a spy method will result the same as the mock object
        String expected = "Spy 100";
        //take note of using doReturn instead of when
        Mockito.doReturn(expected).when(spyList).get(100);

        Assert.assertEquals(expected, spyList.get(100));
    }
}
