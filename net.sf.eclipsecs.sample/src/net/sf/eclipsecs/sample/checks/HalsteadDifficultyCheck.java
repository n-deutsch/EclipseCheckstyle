package net.sf.eclipsecs.sample.checks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//calculates the Halstead difficulty
public class HalsteadDifficultyCheck extends AbstractCheck {
  
  public static final String MSG_KEY = "HalsteadDifficulty";
  private static int MAX = 1;
  
  public void setMax(int max) {
    MAX = max;
  }
  
  //is half of the unique operators multiplied by the total 
  //number of operands, divided by the number of distinct operators
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
  
  
  HashSet<Integer> foundoperators = new HashSet<Integer>();
  int totaloperators;
  HashSet<Integer> foundoperands = new HashSet<Integer>();
  int totaloperands;
  
  @Override
  public int[] getDefaultTokens() {
    int[] allwatches = new int[operators.length + operands.length];
    System.arraycopy(operators, 0, allwatches, 0, operators.length);
    System.arraycopy(operands, 0, allwatches, operators.length, operands.length);
    return allwatches;
  }

  @Override
  public int[] getAcceptableTokens() {
    int[] allwatches = new int[operators.length + operands.length];
    System.arraycopy(operators, 0, allwatches, 0, operators.length);
    System.arraycopy(operands, 0, allwatches, operators.length, operands.length);
    return allwatches;
  }

  @Override
  public int[] getRequiredTokens() {
    int[] allwatches = new int[operators.length + operands.length];
    System.arraycopy(operators, 0, allwatches, 0, operators.length);
    System.arraycopy(operands, 0, allwatches, operators.length, operands.length);
    return allwatches;
  }

  @Override
  public void visitToken(DetailAST ast) {
    int type = ast.getType();
    if(IntStream.of(operators).anyMatch(i -> i == type)) {
      totaloperators++;
      foundoperators.add(type);
    } else if(IntStream.of(operands).anyMatch(i -> i == type)) {
      totaloperands++;
      foundoperands.add(type);
    }
    if(foundoperands.size() > 0 && MAX <= (foundoperators.size()/2)*(totaloperands/foundoperands.size())){
      //super.log(ast, "HalsteadDifficulty Exceeds Expected.",null);
    }
  }
  
  @Override 
  public void finishTree(DetailAST ast)
  {
    //calculate halstead difficulty
    double HalsteadDifficulty = (foundoperators.size() / 2) * (totaloperands / foundoperands.size());
    String message = "Halstead Difficulty: " + HalsteadDifficulty;
    //display halstead difficulty
    log(ast.getLineNo(), message);
    
    //reset operands, operators
    totaloperators = 0;
    totaloperands = 0;
    foundoperands = new HashSet<Integer>();
    foundoperators = new HashSet<Integer>();
  }
}

