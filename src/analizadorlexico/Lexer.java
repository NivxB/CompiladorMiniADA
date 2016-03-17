/* The following code was generated by JFlex 1.6.1 */

package analizadorlexico;
import java_cup.runtime.Symbol;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>./src/analizadorlexico/Lexer.flex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;
  public static final int COMMENT = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\53\1\55\1\57\1\56\1\56\22\0\1\53\1\0\1\65"+
    "\1\43\3\0\1\54\1\23\1\24\1\31\1\27\1\25\1\30\1\0"+
    "\1\32\12\52\1\36\1\37\1\34\1\35\1\33\2\0\1\51\1\50"+
    "\1\44\1\46\1\45\1\47\1\17\1\22\1\41\1\40\1\62\1\12"+
    "\1\40\1\11\1\3\1\1\1\40\1\2\1\42\1\10\1\7\1\64"+
    "\1\20\1\60\1\63\1\40\1\0\1\66\2\0\1\21\1\0\1\26"+
    "\1\16\1\4\1\6\1\5\1\13\1\17\1\22\1\41\1\40\1\62"+
    "\1\12\1\40\1\11\1\3\1\1\1\40\1\2\1\42\1\10\1\7"+
    "\1\64\1\20\1\60\1\63\1\40\12\0\1\57\252\0\2\14\115\0"+
    "\1\15\u1ea8\0\1\57\1\57\u0100\0\1\61\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udee5\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\12\2\1\1\2\2\1\3\1\4\1\5"+
    "\1\2\2\6\2\7\2\10\1\11\1\12\1\2\1\13"+
    "\1\14\1\1\1\15\1\16\1\17\1\20\1\21\1\22"+
    "\15\2\1\23\10\2\1\0\1\24\1\25\2\2\1\0"+
    "\3\2\1\26\1\10\1\27\1\30\1\2\1\24\1\25"+
    "\2\0\1\31\1\2\1\32\3\2\1\33\1\34\2\2"+
    "\1\0\1\35\1\0\1\2\1\0\6\2\1\36\3\2"+
    "\1\0\3\2\1\0\1\2\1\0\2\2\1\33\1\2"+
    "\1\0\1\37\1\2\1\0\4\2\1\0\1\2\1\40"+
    "\2\41\2\42\1\43\1\44\1\2\1\45\1\46\2\2"+
    "\2\0\2\2\1\0\1\2\1\47\1\50\1\0\1\2"+
    "\1\47\1\2\1\13\1\2\1\0\4\2\1\0\1\2"+
    "\1\0\3\2\1\51\1\43\1\0\1\2\1\52\2\53"+
    "\2\54\2\2\1\0\1\2\1\55\1\0\2\56\1\0"+
    "\1\2\2\57\1\0\1\2\1\0\1\2\1\0\3\2"+
    "\1\0\1\2\1\60\1\0\1\2\1\0\1\2\1\0"+
    "\1\2\1\51\1\2\1\61\1\0\1\2\2\62\2\63"+
    "\1\64\1\65";

  private static int [] zzUnpackAction() {
    int [] result = new int[211];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\67\0\156\0\245\0\334\0\u0113\0\u014a\0\u0181"+
    "\0\u01b8\0\u01ef\0\u0226\0\u025d\0\u0294\0\u02cb\0\u0302\0\u0339"+
    "\0\u0370\0\245\0\245\0\245\0\u03a7\0\245\0\u03de\0\245"+
    "\0\u0415\0\u0415\0\u044c\0\u0483\0\245\0\u04ba\0\u04f1\0\245"+
    "\0\u0528\0\245\0\245\0\245\0\u055f\0\245\0\245\0\u0596"+
    "\0\u05cd\0\u0604\0\u063b\0\u0672\0\u06a9\0\u06e0\0\u0717\0\u074e"+
    "\0\u0785\0\u07bc\0\u07f3\0\u082a\0\u01ef\0\u0861\0\u0898\0\u08cf"+
    "\0\u0906\0\u093d\0\u0974\0\u09ab\0\u09e2\0\u0a19\0\245\0\245"+
    "\0\u0a50\0\u0a87\0\u0abe\0\u0af5\0\u0b2c\0\u0b63\0\245\0\245"+
    "\0\245\0\245\0\u0b9a\0\u01ef\0\u01ef\0\u0bd1\0\u0c08\0\245"+
    "\0\u0c3f\0\u01ef\0\u0c76\0\u0cad\0\u0ce4\0\245\0\u01ef\0\u0d1b"+
    "\0\u0d52\0\u0d89\0\u01ef\0\u0dc0\0\u0df7\0\u0e2e\0\u0e65\0\u0e9c"+
    "\0\u0ed3\0\u0f0a\0\u0f41\0\u0f78\0\u01ef\0\u0faf\0\u0fe6\0\u101d"+
    "\0\u1054\0\u108b\0\u10c2\0\u10f9\0\u1130\0\u1167\0\u119e\0\u11d5"+
    "\0\u120c\0\u01ef\0\u1243\0\u127a\0\245\0\u12b1\0\u12e8\0\u131f"+
    "\0\u1356\0\u138d\0\u13c4\0\u13fb\0\u1432\0\245\0\u1469\0\u14a0"+
    "\0\245\0\u01ef\0\u01ef\0\u01ef\0\u14d7\0\u01ef\0\u01ef\0\u150e"+
    "\0\u1545\0\u157c\0\u15b3\0\u15ea\0\u1621\0\u1658\0\u168f\0\245"+
    "\0\u01ef\0\u16c6\0\u16fd\0\u01ef\0\u1734\0\245\0\u176b\0\u17a2"+
    "\0\u17d9\0\u1810\0\u1847\0\u187e\0\u18b5\0\u18ec\0\u1923\0\u195a"+
    "\0\u1991\0\u19c8\0\u01ef\0\245\0\u19ff\0\u1a36\0\245\0\245"+
    "\0\u01ef\0\245\0\u01ef\0\u1a6d\0\u1aa4\0\u1adb\0\u1b12\0\u01ef"+
    "\0\u1b49\0\245\0\u01ef\0\u1b80\0\u1bb7\0\245\0\u01ef\0\u1bee"+
    "\0\u1c25\0\u1c5c\0\u1c93\0\u1cca\0\u1d01\0\u1d38\0\u1d6f\0\u1da6"+
    "\0\u1ddd\0\245\0\u1e14\0\u1e4b\0\u1e82\0\u1eb9\0\u1ef0\0\u1f27"+
    "\0\245\0\u1f5e\0\245\0\u1f95\0\u1fcc\0\245\0\u01ef\0\245"+
    "\0\u01ef\0\u01ef\0\245";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[211];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\6\1\7\1\10\1\11\2\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\4\1\20\1\12\1\21"+
    "\1\4\1\12\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\2\32\1\33\1\34\1\35\1\12\1\36"+
    "\1\12\1\4\1\10\1\11\1\12\1\16\1\20\1\25"+
    "\1\37\1\40\1\41\2\40\1\0\1\12\1\4\3\12"+
    "\1\42\1\4\57\43\1\0\5\43\1\44\1\45\53\46"+
    "\1\40\1\46\1\47\1\40\1\0\7\46\70\0\1\12"+
    "\1\50\4\12\1\51\4\12\2\0\5\12\3\0\1\52"+
    "\1\0\1\12\7\0\3\12\1\0\5\12\1\52\1\12"+
    "\5\0\1\12\1\0\3\12\3\0\4\12\1\53\6\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\1\12\1\53\5\12\5\0\1\12\1\0\3\12"+
    "\3\0\1\12\1\54\4\12\1\55\1\56\3\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\3\0\2\12\1\57"+
    "\10\12\2\0\5\12\3\0\1\60\1\0\1\12\7\0"+
    "\3\12\1\0\5\12\1\60\1\12\5\0\1\12\1\0"+
    "\3\12\3\0\10\12\1\61\1\62\1\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\7\12"+
    "\5\0\1\63\1\0\3\12\3\0\13\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\7\12"+
    "\5\0\1\12\1\0\3\12\3\0\1\12\1\64\1\65"+
    "\10\12\2\0\4\12\1\66\3\0\1\12\1\0\1\12"+
    "\7\0\3\12\1\0\7\12\5\0\1\12\1\0\3\12"+
    "\3\0\4\12\1\67\1\12\1\70\4\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\1\12"+
    "\1\67\5\12\5\0\1\12\1\0\3\12\3\0\2\12"+
    "\1\71\10\12\2\0\5\12\3\0\1\12\1\0\1\12"+
    "\7\0\3\12\1\0\7\12\5\0\1\12\1\0\3\12"+
    "\3\0\2\12\1\72\3\12\1\73\2\12\1\74\1\12"+
    "\2\0\5\12\3\0\1\75\1\0\1\12\7\0\3\12"+
    "\1\0\5\12\1\75\1\12\5\0\1\12\1\0\3\12"+
    "\13\0\1\76\1\0\1\77\1\0\1\100\24\0\1\100"+
    "\4\0\1\77\20\0\2\12\1\101\1\12\1\102\6\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\1\12\1\102\5\12\5\0\1\12\1\0\3\12"+
    "\3\0\13\12\1\103\1\0\4\12\1\104\3\0\1\12"+
    "\1\0\1\12\7\0\1\12\1\105\1\12\1\0\7\12"+
    "\5\0\1\12\1\0\3\12\3\0\10\12\1\106\2\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\7\12\5\0\1\12\1\0\3\12\32\0\1\107"+
    "\73\0\1\110\64\0\1\111\70\0\1\112\32\0\10\12"+
    "\1\113\1\12\1\114\1\0\1\100\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\2\12\1\115\1\0\3\12\1\114"+
    "\3\12\5\0\1\12\1\0\3\12\45\0\1\116\6\0"+
    "\1\37\14\0\55\117\3\0\7\117\65\0\1\120\2\0"+
    "\2\12\1\121\10\12\2\0\5\12\3\0\1\12\1\0"+
    "\1\12\7\0\3\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\3\0\7\12\1\122\3\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\3\0\3\12\1\123\7\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\1\123\6\12\5\0\1\12\1\0\3\12\3\0\7\12"+
    "\1\124\3\12\2\0\5\12\3\0\1\12\1\0\1\12"+
    "\7\0\3\12\1\0\7\12\5\0\1\12\1\0\2\12"+
    "\1\125\3\0\13\12\2\0\5\12\3\0\1\12\1\0"+
    "\1\12\6\0\1\126\3\12\1\0\7\12\5\0\1\12"+
    "\1\0\3\12\3\0\7\12\1\127\3\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\7\12"+
    "\5\0\1\12\1\0\3\12\3\0\13\12\2\0\4\12"+
    "\1\130\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\3\0\10\12\1\131"+
    "\2\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\3\0"+
    "\13\12\1\0\1\132\5\12\3\0\1\12\1\0\1\12"+
    "\7\0\3\12\1\0\7\12\5\0\1\12\1\0\3\12"+
    "\3\0\5\12\1\133\5\12\2\0\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\2\12\1\133\4\12"+
    "\5\0\1\12\1\0\3\12\3\0\13\12\1\0\1\134"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\2\12\1\135"+
    "\1\0\7\12\5\0\1\12\1\0\3\12\3\0\13\12"+
    "\1\136\1\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\1\12\1\137\1\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\3\0\6\12\1\140\4\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\3\0\4\12\1\141\6\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\1\12\1\141\5\12\5\0\1\12\1\0\3\12\3\0"+
    "\13\12\2\0\2\12\1\142\2\12\3\0\1\12\1\0"+
    "\1\12\7\0\3\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\3\0\11\12\1\143\1\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\3\0\2\12\1\144\10\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\3\0\1\12\1\145"+
    "\11\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\3\0"+
    "\10\12\1\146\2\12\2\0\5\12\3\0\1\12\1\0"+
    "\1\12\7\0\3\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\3\0\2\12\1\147\10\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\3\0\11\12\1\150\1\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\12\0\1\151\57\0"+
    "\2\12\1\152\2\12\1\153\5\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\2\12\1\153"+
    "\4\12\5\0\1\12\1\0\3\12\3\0\13\12\2\0"+
    "\1\12\1\154\3\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\12\0"+
    "\1\155\57\0\4\12\1\156\6\12\1\157\1\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\1\12\1\160\1\12"+
    "\1\0\1\12\1\156\5\12\5\0\1\12\1\0\3\12"+
    "\3\0\7\12\1\161\3\12\2\0\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\7\12\5\0\1\12"+
    "\1\0\3\12\3\0\5\12\1\162\5\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\2\12"+
    "\1\162\4\12\5\0\1\12\1\0\3\12\3\0\7\12"+
    "\1\163\3\12\2\0\5\12\3\0\1\12\1\0\1\12"+
    "\7\0\3\12\1\0\7\12\5\0\1\12\1\0\3\12"+
    "\46\0\7\164\70\0\1\165\13\0\3\12\1\166\7\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\1\166\6\12\5\0\1\12\1\0\3\12\3\0"+
    "\13\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\167\1\170\2\12"+
    "\3\0\6\12\1\171\4\12\2\0\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\7\12\5\0\1\12"+
    "\1\0\3\12\3\0\4\12\1\172\6\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\1\12"+
    "\1\172\5\12\5\0\1\12\1\0\3\12\3\0\4\12"+
    "\1\173\6\12\2\0\5\12\3\0\1\12\1\0\1\12"+
    "\7\0\3\12\1\0\1\12\1\173\5\12\5\0\1\12"+
    "\1\0\3\12\3\0\13\12\1\0\1\174\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\2\12\1\175\1\0\7\12"+
    "\5\0\1\12\1\0\3\12\7\0\1\176\37\0\1\176"+
    "\26\0\1\177\37\0\1\177\22\0\4\12\1\200\6\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\1\12\1\200\5\12\5\0\1\12\1\0\3\12"+
    "\12\0\1\201\57\0\7\12\1\202\3\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\7\12"+
    "\5\0\1\12\1\0\3\12\3\0\4\12\1\203\6\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\1\12\1\203\5\12\5\0\1\12\1\0\3\12"+
    "\3\0\10\12\1\204\2\12\2\0\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\7\12\5\0\1\12"+
    "\1\0\3\12\3\0\13\12\2\0\3\12\1\205\1\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\7\12"+
    "\5\0\1\12\1\0\3\12\3\0\11\12\1\206\1\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\7\12\5\0\1\12\1\0\3\12\3\0\1\207"+
    "\12\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\3\0"+
    "\3\12\1\210\7\12\2\0\5\12\3\0\1\12\1\0"+
    "\1\12\7\0\3\12\1\0\1\210\6\12\5\0\1\12"+
    "\1\0\3\12\3\0\13\12\2\0\5\12\3\0\1\211"+
    "\1\0\1\12\7\0\3\12\1\0\5\12\1\211\1\12"+
    "\5\0\1\12\1\0\3\12\3\0\13\12\1\0\1\212"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\2\12\1\140"+
    "\1\0\7\12\5\0\1\12\1\0\3\12\7\0\1\213"+
    "\37\0\1\213\22\0\11\12\1\214\1\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\7\12"+
    "\5\0\1\12\1\0\3\12\3\0\13\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\7\12"+
    "\5\0\1\12\1\0\1\12\1\215\1\12\3\0\13\12"+
    "\1\216\1\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\1\12\1\217\1\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\24\0\1\220\45\0\10\12\1\221\2\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\14\0\1\222\55\0"+
    "\11\12\1\223\1\12\2\0\5\12\3\0\1\12\1\0"+
    "\1\12\7\0\3\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\3\0\13\12\2\0\4\12\1\224\3\0\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\7\12\5\0\1\12"+
    "\1\0\3\12\3\0\4\12\1\225\6\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\1\12"+
    "\1\225\5\12\5\0\1\12\1\0\3\12\45\0\1\226"+
    "\7\164\15\0\4\12\1\227\6\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\1\12\1\227"+
    "\5\12\5\0\1\12\1\0\3\12\30\0\1\230\22\0"+
    "\1\230\16\0\13\12\2\0\5\12\3\0\1\231\1\0"+
    "\1\12\7\0\3\12\1\0\5\12\1\231\1\12\5\0"+
    "\1\12\1\0\3\12\3\0\1\12\1\232\11\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\3\0\1\12\1\233"+
    "\11\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\3\0"+
    "\1\12\1\234\11\12\2\0\5\12\3\0\1\12\1\0"+
    "\1\12\7\0\3\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\12\0\1\235\57\0\7\12\1\236\3\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\16\0\1\237\24\0"+
    "\1\237\26\0\13\12\1\237\1\0\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\1\12\1\240\1\12\1\0\7\12"+
    "\5\0\1\12\1\0\3\12\3\0\11\12\1\241\1\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\7\12\5\0\1\12\1\0\3\12\3\0\7\12"+
    "\1\242\3\12\2\0\5\12\3\0\1\12\1\0\1\12"+
    "\7\0\3\12\1\0\7\12\5\0\1\12\1\0\3\12"+
    "\3\0\7\12\1\243\3\12\2\0\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\7\12\5\0\1\12"+
    "\1\0\3\12\7\0\1\244\37\0\1\244\40\0\1\245"+
    "\50\0\4\12\1\246\6\12\2\0\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\1\12\1\246\5\12"+
    "\5\0\1\12\1\0\3\12\3\0\13\12\2\0\5\12"+
    "\2\0\1\247\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\13\0\1\250\56\0"+
    "\10\12\1\251\2\12\2\0\5\12\3\0\1\12\1\0"+
    "\1\12\7\0\3\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\7\0\1\252\37\0\1\252\22\0\4\12\1\253"+
    "\6\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\1\12\1\253\5\12\5\0\1\12\1\0"+
    "\3\12\3\0\13\12\2\0\1\12\1\254\3\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\3\0\5\12\1\255\5\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\2\12\1\255\4\12\5\0\1\12\1\0\3\12\21\0"+
    "\1\256\50\0\13\12\2\0\1\12\1\257\3\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\3\0\10\12\1\260\2\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\3\0\13\12\1\0"+
    "\1\261\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\7\12\5\0\1\12\1\0\3\12\3\0\13\12"+
    "\1\0\1\262\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\2\12\1\263\1\0\7\12\5\0\1\12\1\0\3\12"+
    "\30\0\1\264\22\0\1\264\16\0\13\12\2\0\5\12"+
    "\3\0\1\265\1\0\1\12\7\0\3\12\1\0\5\12"+
    "\1\265\1\12\5\0\1\12\1\0\3\12\15\0\1\266"+
    "\33\0\1\266\20\0\12\12\1\267\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\3\12\1\267"+
    "\3\12\5\0\1\12\1\0\3\12\3\0\13\12\1\270"+
    "\1\0\5\12\3\0\1\12\1\0\1\12\7\0\1\12"+
    "\1\271\1\12\1\0\7\12\5\0\1\12\1\0\3\12"+
    "\3\0\13\12\1\272\1\0\5\12\3\0\1\12\1\0"+
    "\1\12\7\0\1\12\1\273\1\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\7\0\1\274\37\0\1\274\22\0"+
    "\13\12\2\0\5\12\3\0\1\275\1\0\1\12\7\0"+
    "\3\12\1\0\5\12\1\275\1\12\5\0\1\12\1\0"+
    "\3\12\3\0\4\12\1\276\6\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\1\12\1\276"+
    "\5\12\5\0\1\12\1\0\3\12\3\0\6\12\1\277"+
    "\4\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\7\0"+
    "\1\300\37\0\1\300\22\0\4\12\1\301\6\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\1\12\1\301\5\12\5\0\1\12\1\0\3\12\7\0"+
    "\1\302\37\0\1\302\32\0\1\303\56\0\10\12\1\304"+
    "\2\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\13\0"+
    "\1\305\56\0\10\12\1\306\2\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\5\0\1\307\64\0\2\12\1\310"+
    "\10\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\4\0"+
    "\1\311\65\0\10\12\1\243\2\12\2\0\5\12\3\0"+
    "\1\12\1\0\1\12\7\0\3\12\1\0\7\12\5\0"+
    "\1\12\1\0\3\12\3\0\1\12\1\243\11\12\2\0"+
    "\5\12\3\0\1\12\1\0\1\12\7\0\3\12\1\0"+
    "\7\12\5\0\1\12\1\0\3\12\3\0\1\12\1\312"+
    "\11\12\2\0\5\12\3\0\1\12\1\0\1\12\7\0"+
    "\3\12\1\0\7\12\5\0\1\12\1\0\3\12\27\0"+
    "\1\313\42\0\13\12\2\0\5\12\2\0\1\313\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\7\12\5\0\1\12"+
    "\1\0\3\12\12\0\1\314\57\0\7\12\1\315\3\12"+
    "\2\0\5\12\3\0\1\12\1\0\1\12\7\0\3\12"+
    "\1\0\7\12\5\0\1\12\1\0\3\12\7\0\1\316"+
    "\37\0\1\316\22\0\4\12\1\317\6\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\1\12"+
    "\1\317\5\12\5\0\1\12\1\0\3\12\13\0\1\320"+
    "\56\0\10\12\1\321\2\12\2\0\5\12\3\0\1\12"+
    "\1\0\1\12\7\0\3\12\1\0\7\12\5\0\1\12"+
    "\1\0\3\12\3\0\4\12\1\322\6\12\2\0\5\12"+
    "\3\0\1\12\1\0\1\12\7\0\3\12\1\0\1\12"+
    "\1\322\5\12\5\0\1\12\1\0\3\12\27\0\1\323"+
    "\42\0\13\12\2\0\5\12\2\0\1\323\1\12\1\0"+
    "\1\12\7\0\3\12\1\0\7\12\5\0\1\12\1\0"+
    "\3\12\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8195];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\15\1\3\11\1\1\1\11\1\1\1\11"+
    "\4\1\1\11\2\1\1\11\1\1\3\11\1\1\2\11"+
    "\26\1\1\0\2\11\2\1\1\0\3\1\4\11\3\1"+
    "\2\0\1\11\5\1\1\11\3\1\1\0\1\1\1\0"+
    "\1\1\1\0\12\1\1\0\3\1\1\0\1\1\1\0"+
    "\4\1\1\0\1\11\1\1\1\0\4\1\1\0\1\1"+
    "\1\11\2\1\1\11\10\1\2\0\2\1\1\0\1\1"+
    "\1\11\1\1\1\0\3\1\1\11\1\1\1\0\4\1"+
    "\1\0\1\1\1\0\4\1\1\11\1\0\1\1\2\11"+
    "\1\1\1\11\3\1\1\0\2\1\1\0\1\11\1\1"+
    "\1\0\1\1\1\11\1\1\1\0\1\1\1\0\1\1"+
    "\1\0\3\1\1\0\1\1\1\11\1\0\1\1\1\0"+
    "\1\1\1\0\1\1\1\11\1\1\1\11\1\0\1\1"+
    "\1\11\1\1\1\11\2\1\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[211];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
     StringBuffer string = new StringBuffer();

     private Symbol symbol(int type) {
       return new Symbol(type, yyline, yycolumn);
      }
      private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
      }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 226) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
              {
                return symbol(sym.EOF);
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return symbol(-1,yytext());
            }
          case 54: break;
          case 2: 
            { return symbol(sym.ID,yytext());
            }
          case 55: break;
          case 3: 
            { return symbol(sym.OPEN_PARENTHESIS);
            }
          case 56: break;
          case 4: 
            { return symbol(sym.CLOSE_PARENTHESIS);
            }
          case 57: break;
          case 5: 
            { return symbol(sym.COMMA);
            }
          case 58: break;
          case 6: 
            { return symbol(sym.SUM_OPERATOR,yytext());
            }
          case 59: break;
          case 7: 
            { return symbol(sym.MULT_OPERATOR,yytext());
            }
          case 60: break;
          case 8: 
            { return symbol(sym.RELATION_OPERATOR,yytext());
            }
          case 61: break;
          case 9: 
            { return symbol(sym.DECLARATION);
            }
          case 62: break;
          case 10: 
            { return symbol(sym.END_INSTRUCTION);
            }
          case 63: break;
          case 11: 
            { return symbol(sym.LITERAL_INT,yytext());
            }
          case 64: break;
          case 12: 
            { /* ignore */
            }
          case 65: break;
          case 13: 
            { string.setLength(0); yybegin(STRING);
            }
          case 66: break;
          case 14: 
            { string.append(yytext());
            }
          case 67: break;
          case 15: 
            { yybegin(YYINITIAL); return symbol(sym.LITERAL_STRING,string.toString());
            }
          case 68: break;
          case 16: 
            { string.append('\\');
            }
          case 69: break;
          case 17: 
            { /* Ignore */
            }
          case 70: break;
          case 18: 
            { yybegin(YYINITIAL);
            }
          case 71: break;
          case 19: 
            { return symbol(sym.TO);
            }
          case 72: break;
          case 20: 
            { return symbol(sym.IF);
            }
          case 73: break;
          case 21: 
            { return symbol(sym.IS);
            }
          case 74: break;
          case 22: 
            { yybegin(COMMENT);
            }
          case 75: break;
          case 23: 
            { return symbol(sym.CASEASIG);
            }
          case 76: break;
          case 24: 
            { return symbol(sym.ASIGNATION);
            }
          case 77: break;
          case 25: 
            { string.append('\"');
            }
          case 78: break;
          case 26: 
            { return symbol(sym.PUT);
            }
          case 79: break;
          case 27: 
            { return symbol(sym.CONDITION_ELEMENT,yytext());
            }
          case 80: break;
          case 28: 
            { return symbol(sym.OUT);
            }
          case 81: break;
          case 29: 
            { return symbol(sym.END);
            }
          case 82: break;
          case 30: 
            { return symbol(sym.FOR);
            }
          case 83: break;
          case 31: 
            { return symbol(sym.LITERAL_CHAR,yytext());
            }
          case 84: break;
          case 32: 
            { return symbol(sym.CASE);
            }
          case 85: break;
          case 33: 
            { return symbol(sym.ELSE);
            }
          case 86: break;
          case 34: 
            { return symbol(sym.EXIT);
            }
          case 87: break;
          case 35: 
            { return symbol(sym.LITERAL_BOOLEAN,yytext());
            }
          case 88: break;
          case 36: 
            { return symbol(sym.THEN);
            }
          case 89: break;
          case 37: 
            { return symbol(sym.NULL);
            }
          case 90: break;
          case 38: 
            { return symbol(sym.LOOP);
            }
          case 91: break;
          case 39: 
            { return symbol(sym.WITH);
            }
          case 92: break;
          case 40: 
            { return symbol(sym.WHEN);
            }
          case 93: break;
          case 41: 
            { return symbol(sym.TYPE,yytext());
            }
          case 94: break;
          case 42: 
            { return symbol(sym.BODY);
            }
          case 95: break;
          case 43: 
            { return symbol(sym.BEGIN);
            }
          case 96: break;
          case 44: 
            { return symbol(sym.WHILE);
            }
          case 97: break;
          case 45: 
            { return symbol(sym.RETURN);
            }
          case 98: break;
          case 46: 
            { return symbol(sym.OTHERS);
            }
          case 99: break;
          case 47: 
            { return symbol(sym.ELSEIF);
            }
          case 100: break;
          case 48: 
            { return symbol(sym.REVERSE);
            }
          case 101: break;
          case 49: 
            { return symbol(sym.PACKAGE);
            }
          case 102: break;
          case 50: 
            { return symbol(sym.NEW_LINE);
            }
          case 103: break;
          case 51: 
            { return symbol(sym.FUNCTION);
            }
          case 104: break;
          case 52: 
            { return symbol(sym.PROCEDURE);
            }
          case 105: break;
          case 53: 
            { return symbol(sym.CONSTANT);
            }
          case 106: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
