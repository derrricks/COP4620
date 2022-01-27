lexer grammar Little;

ID: [a-zA-Z]+;	//looks for matching identifiers
INTLITERAL: [0-9]+;
FLOATLITERAL: [0-9]*'.'[0-9]+;
STRINGLITERAL:	'"' ~ ["] '"';
COMMENT: '--'[0-9a-zA-Z]* -> channel(HIDDEN);
KEYWORD: 'PROGRAM' | 'BEGIN' | 'END' | 'FUNCTION' | 'READ' | 'WRITE' | 'IF' | 'ELSE' | 'ENDIF' | 'WHILE' | 'ENDWHILE' | 'CONTINUE' | 'BREAK' | 'RETURN' | 'INT' | 'VOID' | 'STRING' | 'FLOAT';
WS: [ \t\r\n]+ -> skip; //skips all whitespace
