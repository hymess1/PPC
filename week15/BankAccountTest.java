import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Starts each test with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    /** 1. Add an @AfterEach annotation and method to delete the current bank account to make it available for garbage collection */
    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    void testDeposit() {
    /** 2. Adeposit $50 and check that the balance is 150 */
        account.deposit(50);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testWithdraw() {
    /** 3. withdraw $40 and check that the balance is $60; remember that each test is done on a fresh instance of bank account */
        account.withdraw(40);
        assertEquals(60.0, account.getBalance());
    }

    @Test
    void testInvalidDeposit() {
    /** 4. Deposit a negative amount and check if an exception is thrown */
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
    }

    @Test
    void testOverdraft() {
    /** 5. Verify that Withdrawing more than the current balance
    throws an exception */
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(200));
    }

    @Test
    void testInvalidInitialBalance() {
    /** 6. Add a test to check that an Exception is thrown when
    trying to create a new bankaccout with a negaive initial balance */
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-50));
    }
    //Testing the transfer method
    @Test
    void testTransfer() {
    BankAccount other = new BankAccount(50);

    account.transfer(other, 30);

    assertEquals(70.0, account.getBalance());
    assertEquals(80.0, other.getBalance());
    }

    //Question 2:
    @Test
    void testArrayValues() {
    int[] arr = {25, 30, 40, 21};

    for (int num : arr) {
        assertTrue(num >= 20);
    }
    }
    //Question 3:
    @Test
    void testStrings() {
    String strOne = "hello";
    String strTwo = "hello";

    assertEquals(strOne, strTwo);
    }
    //Question 4: If one test fails, do the others still run?
         //Yes, all test methods still run.
}
