package fr.essant.basilebyvanu.clv;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public CaisseEnregistreuse caisse;

    @Before
    public void init(){
        caisse = new CaisseEnregistreuse();
    }


    @Test
    public void addition_isCorrect() {
        assertEquals(5,caisse.add(5));
        assertEquals(3,caisse.add(-2));
        assertEquals(10,caisse.add(7));
    }

    @Test
    public void division_isCorrect(){
        assertEquals(10,caisse.add(10));
        assertEquals(5, caisse.div(2));
        assertEquals(1, caisse.div(5));
    }

    @Test
    public void multiplication_isCorrect(){
        assertEquals(1,caisse.add(1));
        assertEquals(1,caisse.mult(1));
        assertEquals(8,caisse.mult(8));
        assertEquals(-8,caisse.mult(-1));
    }

    @Test
    public void sub_isCorrect(){
        assertEquals(-8,caisse.sub(8));
        assertEquals(8,caisse.sub(-16));
        assertEquals(6,caisse.sub(2));
        assertEquals(2,caisse.sub(4));
    }

    @Test
    public void total_isAPositiveNumber(){
        caisse.add(10);
        assertThat(caisse.total(),Matchers.greaterThan(0));
        caisse.sub(11);
        assertThat(caisse.total(),Matchers.greaterThan(0));
    }

    @After
    public void empty_isCorrect(){
        caisse.empty();

    }

}