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


//CAPS : CAPS is a token (terminal) made up of one or more characters. Small case symbols are non-terminals.
/* Program */
program           -> PROGRAM id BEGIN pgm_body END
id                -> IDENTIFIER
pgm_body          -> decl func_declarations
decl		        -> string_decl decl | var_decl decl | empty

/* Global String Declaration */
string_decl       -> STRING id := str ;
str               -> STRINGLITERAL

/* Variable Declaration */
var_decl          -> var_type id_list ;
var_type	        -> FLOAT | INT
any_type          -> var_type | VOID
id_list           -> id id_tail
id_tail           -> , id id_tail | empty

/* Function Paramater List */
param_decl_list   -> param_decl param_decl_tail | empty
param_decl        -> var_type id
param_decl_tail   -> , param_decl param_decl_tail | empty

/* Function Declarations */
func_declarations -> func_decl func_declarations | empty
func_decl         -> FUNCTION any_type id (param_decl_list) BEGIN func_body END
func_body         -> decl stmt_list

/* Statement List */
stmt_list         -> stmt stmt_list | empty
stmt              -> base_stmt | if_stmt | while_stmt
base_stmt         -> assign_stmt | read_stmt | write_stmt | return_stmt

/* Basic Statements */
assign_stmt       -> assign_expr ;
assign_expr       -> id := expr
read_stmt         -> READ ( id_list );
write_stmt        -> WRITE ( id_list );
return_stmt       -> RETURN expr ;

/* Expressions */
expr              -> expr_prefix factor
expr_prefix       -> expr_prefix factor addop | empty
factor            -> factor_prefix postfix_expr
factor_prefix     -> factor_prefix postfix_expr mulop | empty
postfix_expr      -> primary | call_expr
call_expr         -> id ( expr_list )
expr_list         -> expr expr_list_tail | empty
expr_list_tail    -> , expr expr_list_tail | empty
primary           -> ( expr ) | id | INTLITERAL | FLOATLITERAL
addop             -> + | -
mulop             -> * | /

/* Complex Statements and Condition */
if_stmt           -> IF ( cond ) decl stmt_list else_part ENDIF
else_part         -> ELSE decl stmt_list | empty
cond              -> expr compop expr
compop            -> < | > | = | != | <= | >=

/* While statements */
while_stmt       -> WHILE ( cond ) decl stmt_list ENDWHILE