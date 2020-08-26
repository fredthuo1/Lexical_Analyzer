import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int charClass;
    final static int LETTER = 0;
    final static int DIGIT = 1;
    final static int UNKNOWN = 99;
    final static int EOF = -1;
    final static int IDENT = 11;
    final static int INT_LIT = 10;
    private static final int ASSIGN_OP = 20;
    private static final int ADD_OP = 21;
    private static final int SUB_OP = 22;
    private static final int MULT_OP = 23;
    private static final int DIV_OP = 24;
    private static final int LEFT_PAREN = 25;
    private static final int RIGHT_PAREN = 26;

    static int lexLen;
    static int nextToken;
    static int token;
    static char[] temp = new char[100];
    static char[] lexeme = new char[100];
    static int index;

    public static void main(String[] args) throws IOException {
        File file  = new File("src/file.txt");
        FileReader fileReader = new FileReader(file);
        int c;
        if ((c = fileReader.read()) == -1) {
            System.out.println("ERROR - cannot open file \n");
        } else {
            getChar((char) c);
            lex((char) c);
            addChar((char) c);
            index = 0;
            temp[index] = (char) c;
            while ((c = fileReader.read()) != -1) {
                index++;
                temp[index] = (char) c;
                getChar((char) c);
                addChar((char) c);
                lex((char) c);
            }
            }
        System.out.println("\nNext token is: " + EOF + "  Next lexeme is: EOF" );
    }

    private static void addChar(char c) {
        if (!Character.isWhitespace(c)) {
            if (lexLen <= 98) {
                lexeme[lexLen++] = c;
            } else {
                System.out.println("Error - lexeme is too long \n");
            }
        }
    }

    private static int lex(char c) {
        int i = 0;
        int j = 0;
        char[] chars = new char[100];
        int[] ints = new int[100];
        if (Character.isLetter(c) && Character.isLetter(temp[index - 1])) {
            chars[index] = temp[index - 1];
            System.out.print(chars[index]);
        } else if (Character.isDigit(c) && Character.isDigit(temp[index - 1])) {
            ints[j] = c;
            System.out.print(temp[index - 1]);
        } else {
        if (!Character.isWhitespace(c)) {
            switch (charClass) {
                case LETTER:
                    chars[index] = c;
                    nextToken = IDENT;
                    break;
                case DIGIT:
                    ints[index] = c;
                    nextToken = INT_LIT;
                    break;
                case UNKNOWN:
                    lookup(c);
                    nextToken = lookup(c);
                    break;
                case EOF:
                    nextToken = EOF;
                    lexeme[0] = 'E';
                    lexeme[1] = 'O';
                    lexeme[2] = 'F';
                    lexeme[3] = 0;
                    break;
            }
            if (nextToken == 11) {
                System.out.print("Next token is: " + nextToken + "  Next lexeme is:  ");
            } else if (nextToken == 10) {
                System.out.print("Next token is: " + nextToken + "  Next lexeme is:  ");
            } else {
                System.out.print("\nNext token is: " + nextToken + "  Next lexeme is:  " + c + "\n");
            }
        }
        }
        return nextToken;
    }

    static int lookup (char ch) {
        switch (ch) {
            case '(':
                nextToken = LEFT_PAREN;
                break;
            case ')':
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                nextToken = ADD_OP;
                break;
            case '-':
                nextToken = SUB_OP;
                break;
            case '*':
                nextToken = MULT_OP;
                break;
            case '/':
                nextToken = DIV_OP;
                break;
            default:
                nextToken = EOF;
                break;
        }
        return nextToken;
    }
    private static void getChar(char c) {
        if ((c != -1)) {
            if (Character.isLetter(c)) {
                charClass = LETTER;
            } else if (Character.isDigit(c)) {
                charClass = DIGIT;
            } else if (Character.isWhitespace(c)) {

            } else {
                charClass = UNKNOWN;
            }
        } else {
            charClass = EOF;
        }
    }
}
