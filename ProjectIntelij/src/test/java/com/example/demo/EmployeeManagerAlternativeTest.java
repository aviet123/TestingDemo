package com.example.demo;


import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.Collections;

public class EmployeeManagerAlternativeTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BankService bankService;

    @InjectMocks
    private  EmployeeManager employeeManager;

    @Captor
    private ArgumentCaptor<String> idCaptor;

    @Captor
    private ArgumentCaptor<Double> amountCaptor;

    @Spy
    private Employee notToBePaid = new Employee("1",1000);

    @Spy
    private Employee toBePaid = new Employee("2", 2000);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPayEmployeesWhenNoEmployeesArePresent(){
        Mockito.when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        assertThat(employeeManager.payEmployee()).isZero();
    }

    @Test
    public void testPayEmployeesWhenOneEmployeeIsPresent(){
        Mockito.when(employeeRepository.findAll())
                .thenReturn(Collections.singletonList(
                        new Employee("1", 1000)));
        assertThat(employeeManager.payEmployee()).isEqualTo(1);
        Mockito.verify(bankService).pay("1",1000);
    }
    @Test
    public void testPayEmployeesInOrderWhenSeveralEmployeeArePresent(){
        Mockito.when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(new Employee("2",2000),new Employee("1",1000)));
        assertThat(employeeManager.payEmployee()).isEqualTo(2);
        InOrder inOrder = Mockito.inOrder(bankService);
        inOrder.verify(bankService).pay("2",2000);
        inOrder.verify(bankService).pay("1",1000);
        Mockito.verifyNoMoreInteractions(bankService);
    }
    @Test
    public void testPayEmployeesInOrderWithTwoMocks(){
        Mockito.when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(new Employee("2",2000),new Employee("1",1000)));
        assertThat(employeeManager.payEmployee()).isEqualTo(2);
        InOrder inOrder = Mockito.inOrder(bankService,employeeRepository);
        inOrder.verify(employeeRepository).findAll();
        inOrder.verify(bankService).pay("2",2000);
        inOrder.verify(bankService).pay("1",1000);
        Mockito.verifyNoMoreInteractions(bankService);
    }
    @Test
    public void testExampleOfArgumentCaptor(){
        Mockito.when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(new Employee("1",1000), new Employee("2",2000), new Employee("3",3000)));
        assertThat(employeeManager.payEmployee()).isEqualTo(3);
        Mockito.verify(bankService,Mockito.times(3)).pay(idCaptor.capture(),amountCaptor.capture());
        assertThat(idCaptor.getAllValues()).containsExactly("1","2","3");
        assertThat(amountCaptor.getAllValues()).containsExactly(1000.0,2000.0,3000.0);
        Mockito.verifyNoMoreInteractions(bankService);
    }

    @Test
    public void testEmployeeSetPaidIsCalledAfterPaying(){
        Mockito.when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(toBePaid));
        assertThat(employeeManager.payEmployee()).isEqualTo(1);
        InOrder inOrder = Mockito.inOrder(bankService,toBePaid);
        inOrder.verify(bankService).pay("2",2000);
        inOrder.verify(toBePaid).setPaid(true);
    }

    @Test
    public void testPayEmployeesWhenBankServiceThrowsException() {
        Mockito.when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(notToBePaid));
        Mockito.doThrow(new RuntimeException()).when(bankService).pay(any(), anyDouble());
        // number of payments must be 0
        assertThat(employeeManager.payEmployee()).isZero();
        // make sure that Employee.paid is updated accordingly

        Mockito.verify(notToBePaid).setPaid(false);
    }

    @Test
    public void testOtherEmployeesArePaidWhenBankServiceThrowsException(){
        Mockito.when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(notToBePaid,toBePaid));
        Mockito.doThrow(new RuntimeException()).doNothing()
                .when(bankService).pay(any(), anyDouble());
        assertThat(employeeManager.payEmployee()).isEqualTo(1);
        Mockito.verify(notToBePaid).setPaid(false);
        Mockito.verify(toBePaid).setPaid(true);
    }


}
