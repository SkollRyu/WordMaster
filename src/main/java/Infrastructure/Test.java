package Infrastructure;

import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;


public class Test {
    Players players = new Players();

    @org.junit.Test
    @DisplayName("Player || set high score")
    public void setHighScoreTest(){
        Player player = new Player("Test1","password", 1, 200);
        assertTrue(player.setHighScore(300));
        assertFalse(player.setHighScore(100));
    }

    @org.junit.Test
    @DisplayName("Player || to string test")
    public void toStringTest(){
        Player player = new Player("Test1","password", 1, 200);
        assertEquals("Test1,1,200", player.toString());
    }

    @org.junit.Test
    @DisplayName("Players || check valid player String Test")
    public void checkValidPlayerStringTest(){
        assertTrue(players.checkValidPlayerString("Test1,1,200"));
        assertTrue(players.checkValidPlayerString("darren,2,400"));
        assertFalse(players.checkValidPlayerString(",1,200"));
        assertFalse(players.checkValidPlayerString(",,200"));
        assertFalse(players.checkValidPlayerString(",,"));
        assertFalse(players.checkValidPlayerString("test1,200"));
        assertFalse(players.checkValidPlayerString("1200"));
    }

    @org.junit.Test
    @DisplayName("Players || Find Player")
    public void findPlayerTest(){
        players.readPlayerFile();
        assertEquals("darren", players.findPlayer("darren").getUserName());
        assertNull(players.findPlayer("Peter"));
    }
}
