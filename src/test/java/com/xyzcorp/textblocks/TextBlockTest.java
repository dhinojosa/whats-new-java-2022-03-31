package com.xyzcorp.textblocks;

import org.junit.jupiter.api.Test;

public class TextBlockTest {
    @Test
    void parseJson() {
        String json = """
            [
              {name: 'Afghanistan', code: 'AF'},
              {name: 'Åland Islands', code: 'AX'},
              {name: 'Albania', code: 'AL'},
              {name: 'Algeria', code: 'DZ'},
              {name: 'American Samoa', code: 'AS'},
              {name: 'Andorra', code: 'AD'},
              {name: 'Angola', code: 'AO'},
              {name: 'Anguilla', code: 'AI'},
              {name: 'Antarctica', code: 'AQ'},
              {name: 'Antigua and Barbuda', code: 'AG'},
              {name: 'Argentina', code: 'AR'},
              {name: 'Armenia', code: 'AM'},
              {name: 'Aruba', code: 'AW'},
              {name: 'Australia', code: 'AU'}
            ]""";
        System.out.println(json);
    }

    @Test
    void textBlocksWillRespectWhiteSpaceOnTheLeft() {
        var jupitersMoons =
            """
              The planet Jupiter's four largest moons are called the Galilean
              satellites after Italian astronomer Galileo Galilei, who first
              observed them in 1610. The German astronomer Simon Marius claimed
            - to have seen the moons around the same time, but he did not publish
              his observations and so Galileo is given the credit for their
               discovery. These large moons, named Io, Europa, Ganymede,
            and Callisto, are each distinctive worlds.""";
        System.out.println(jupitersMoons);
    }

}
