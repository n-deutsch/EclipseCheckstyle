package net.sf.eclipsecs.sample.checks;

import java.util.HashSet;
import java.util.stream.IntStream;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//calculates the halstead vocabulary
//HL = sum of the unique operators and operands
public class HalsteadVocabularyCheck extends AbstractCheck {

  //private int totalOperators = 0; //default zero
  //private int totalOperands = 0; //default zero
  HashSet<Integer> foundOperands = new HashSet<Integer>();
  HashSet<Integer> foundOperators = new HashSet<Integer>();
  
  
  int[] operators = {
      TokenTypes.ASSIGN,
      TokenTypes.BAND,
      TokenTypes.BAND_ASSIGN,
      TokenTypes.BNOT,
      TokenTypes.BOR,
      TokenTypes.BOR_ASSIGN,
      TokenTypes.BSR,
      TokenTypes.BSR_ASSIGN,
      TokenTypes.BXOR,
      TokenTypes.BXOR_ASSIGN,
      TokenTypes.COLON,
      TokenTypes.COMMA,
      TokenTypes.DEC,
      TokenTypes.DIV,
      TokenTypes.DIV_ASSIGN,
      TokenTypes.DOT,
      TokenTypes.EQUAL,
      TokenTypes.GE,
      TokenTypes.GT,
      TokenTypes.INC,
      TokenTypes.INDEX_OP,
      TokenTypes.LAND,
      TokenTypes.LE,
      TokenTypes.LITERAL_INSTANCEOF,
      TokenTypes.LNOT,
      TokenTypes.LOR,
      TokenTypes.LT,
      TokenTypes.MINUS,
      TokenTypes.MINUS_ASSIGN,
      TokenTypes.MOD,
      TokenTypes.MOD_ASSIGN,
      TokenTypes.NOT_EQUAL,
      TokenTypes.PLUS,
      TokenTypes.PLUS_ASSIGN,
      TokenTypes.POST_DEC,
      TokenTypes.POST_INC,
      TokenTypes.QUESTION,
      TokenTypes.SL,
      TokenTypes.SL_ASSIGN,
      TokenTypes.SR,
      TokenTypes.SR_ASSIGN,
      TokenTypes.STAR,
      TokenTypes.STAR_ASSIGN,
      TokenTypes.UNARY_MINUS,
      TokenTypes.UNARY_PLUS 
  };
  
  int[] operands = {
      TokenTypes.TYPE,
      TokenTypes.WILDCARD_TYPE,
      TokenTypes.CHAR_LITERAL,
      TokenTypes.STRING_LITERAL,
      TokenTypes.ENUM_CONSTANT_DEF
  };
  
  @Override
  public int[] getAcceptableTokens() {
    int[] allwatches = new int[operators.length + operands.length];
    System.arraycopy(operators, 0, allwatches, 0, operators.length);
    System.arraycopy(operands, 0, allwatches, operators.length, operands.length);
    return allwatches;
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    int[] allwatches = new int[operators.length + operands.length];
    System.arraycopy(operators, 0, allwatches, 0, operators.length);
    System.arraycopy(operands, 0, allwatches, operators.length, operands.length);
    return allwatches;
  }

  @Override
  public void visitToken(DetailAST ast) {
    int type = ast.getType();
    if(IntStream.of(operators).anyMatch(i -> i == type)) {
      //totalOperators++;
      foundOperators.add(type);
    } else if(IntStream.of(operands).anyMatch(i -> i == type)) {
      //totalOperands++;
      foundOperands.add(type);
    }
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    int halsteadVocab = foundOperators.size() + foundOperands.size();
    
    //calculate HalsteadLength
    String message = "Halstead Vocabulary: " + halsteadVocab;
    log(ast.getLineNo(), message);
    
    //reset to default
    //totalOperators = 0;
    //totalOperands = 0;
    foundOperands = new HashSet<Integer>();
    foundOperators = new HashSet<Integer>();
  }
}