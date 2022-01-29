lexer grammar Little; // define grammar ('Little' must match file name)
// for future context:
//      [0-9]+ requires at least one number in the range [0-9] to be a present/exsisting value
//      [0-9]* makes it optional -> it can have one value or not
KEYWORD: 'PROGRAM' | 'BEGIN' | 'END' | 'FUNCTION' | 'READ' | 'WRITE' | 'IF' | 'ELSE' | 'ENDIF' | 'WHILE' | 'ENDWHILE' | 'CONTINUE' | 'BREAK' | 'RETURN' | 'INT' | 'VOID' | 'STRING' | 'FLOAT'; // predefined values (must be first)
IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*; // will begin with a letter, followed by any number of letters and numbers (case sensitive)
INTLITERAL: [0-9]+; // any integer number
FLOATLITERAL: [0-9]*'.'[0-9]+; // floating point number (decimal value)
STRINGLITERAL:	'"' ~["]* '"';  // any string in between "xxxxxxxx" -> xxxxxxxx
COMMENT: '--'[0-9a-zA-Z]* -> skip; // anything after -- can be ignored
OPERATORS: ':=' | '+' | '-' | '*' | '/' | '=' | '!=' | '<' | '>' | '(' | ')' | ';' | ',' | '<=' | '>='; // predefined values
WS: [ \t\r\n]+ -> skip; //skips all whitespace