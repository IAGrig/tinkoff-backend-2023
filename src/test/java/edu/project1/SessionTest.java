package edu.project1;

import edu.project1.enums.Status;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {
    @Test
    void getCurrentWordTest() {
        Session session = new Session("test");
        assertThat(session.getCurrentWord()).isEqualTo("****");
        session.tryGuess('t');
        assertThat(session.getCurrentWord()).isEqualTo("t**t");
        session.tryGuess('e');
        assertThat(session.getCurrentWord()).isEqualTo("te*t");
        session.tryGuess('s');
        assertThat(session.getCurrentWord()).isEqualTo("test");
    }

    @Test
    void tryGuessVictoryTest() {
        Session session = new Session("test");
        assertThat(session.tryGuess('a')).isEqualTo(Status.MISTAKE);
        assertThat(session.tryGuess('t')).isEqualTo(Status.HIT);
        assertThat(session.tryGuess('e')).isEqualTo(Status.HIT);
        assertThat(session.tryGuess('s')).isEqualTo(Status.VICTORY);
    }

    @Test
    void tryGuessDefeatTest() {
        Session session = new Session("test");
        assertThat(session.tryGuess('a')).isEqualTo(Status.MISTAKE);
        assertThat(session.tryGuess('t')).isEqualTo(Status.HIT);
        assertThat(session.tryGuess('e')).isEqualTo(Status.HIT);
        assertThat(session.tryGuess('b')).isEqualTo(Status.MISTAKE);
        assertThat(session.tryGuess('c')).isEqualTo(Status.MISTAKE);
        assertThat(session.tryGuess('d')).isEqualTo(Status.DEFEAT);
    }
}
