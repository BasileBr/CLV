package fr.essant.basilebyvanu.clv;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public CaisseEnregistreuse caisse = new CaisseEnregistreuse();
    @Test
    public void addition_isCorrect() {
        assertEquals(5,caisse.add(5));
        assertEquals(3,caisse.add(-2));
        assertEquals(10,caisse.add(7));
    }

}