package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts total number of expressions
public class ExpressionsCheck extends AbstractCheck {

  private int expressionsCount = 0; //default to zero
  
  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.EXPR };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.EXPR };
  }

  @Override
  public void visitToken(DetailAST ast) {
    expressionsCount++; //increase # of expressions
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    //display the number of expressions
    String message = "Number of expressions: " + this.expressionsCount;
    log(ast.getLineNo(), message);
    expressionsCount = 0;
  }
}