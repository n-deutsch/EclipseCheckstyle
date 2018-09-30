package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class OperandCountCheck extends AbstractCheck {

  private int operandsCount = 0;
  //private int max = 0;
  
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
        TokenTypes.TYPE,
        TokenTypes.WILDCARD_TYPE,
        TokenTypes.CHAR_LITERAL,
        TokenTypes.STRING_LITERAL,
        TokenTypes.ENUM_CONSTANT_DEF
        };
  }

  public void setOperandCount() {
    this.operandsCount++;
  }

  @Override
  public void visitToken(DetailAST ast) {
    setOperandCount();
    //if(this.max < this.OperandCount) {
      //String message = "only " + this.max + " Operands allowed, you have " + this.OperandCount;
      //log(ast.getLineNo(), message);  }
    }
  
  @Override 
  public void finishTree(DetailAST ast) {
    //display number of operands to the user
    String message = "Number of operands: " + this.operandsCount;
    log(ast.getLineNo(), message);
    
    //reset number of operands
    operandsCount = 0;
  }
}
