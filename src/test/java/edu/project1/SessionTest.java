package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {
    @Test
    void getCurrentWordTest(){
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
    void tryGuessVictoryTest(){
        Session session = new Session("test");
        assertThat(session.tryGuess('a')).isEqualTo(Session.status.MISTAKE);
        assertThat(session.tryGuess('t')).isEqualTo(Session.status.HIT);
        assertThat(session.tryGuess('e')).isEqualTo(Session.status.HIT);
        assertThat(session.tryGuess('s')).isEqualTo(Session.status.VICTORY);
    }

    @Test
    void tryGuessDefeatTest(){
        Session session = new Session("test");
        assertThat(session.tryGuess('a')).isEqualTo(Session.status.MISTAKE);
        assertThat(session.tryGuess('t')).isEqualTo(Session.status.HIT);
        assertThat(session.tryGuess('e')).isEqualTo(Session.status.HIT);
        assertThat(session.tryGuess('b')).isEqualTo(Session.status.MISTAKE);
        assertThat(session.tryGuess('c')).isEqualTo(Session.status.MISTAKE);
        assertThat(session.tryGuess('d')).isEqualTo(Session.status.DEFEAT);
    }
}
