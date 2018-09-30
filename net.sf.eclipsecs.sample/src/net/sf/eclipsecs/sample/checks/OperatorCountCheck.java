package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of operators
public class OperatorCountCheck extends AbstractCheck {

  private int operatorCount = 0; //default is zero
  
  @Override
  public int[] getAcceptableTokens() {
      return getDefaultTokens();
  }

  @Override
  public int[] getRequiredTokens() {
      return getDefaultTokens();
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] {
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
  }

  public void setOperatorCount() {
    //increase counter
    this.operatorCount++;
  }

  @Override
  public void visitToken(DetailAST ast) {
    setOperatorCount();
    //if(this.max < this.OperatorCount) {
    //String message = "only " + this.max + " Operators allowed, you have " + this.OperatorCount;
    //log(ast.getLineNo(), message);  }
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    String message = "Number of operators: " + this.operatorCount;
    log(ast.getLineNo(), message);
    
    //reset counter
    operatorCount = 0;
  }
}
