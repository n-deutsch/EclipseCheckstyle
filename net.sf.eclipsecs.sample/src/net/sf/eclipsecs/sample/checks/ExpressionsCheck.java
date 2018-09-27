package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts total number of expressions
public class ExpressionsCheck extends AbstractCheck {

  private int expressionCount = 0;
  
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
    expressionCount++;
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    String message = "Number of expressions: " + this.expressionCount;
    log(ast.getLineNo(), message);
  }
}