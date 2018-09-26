package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of expressions
public class ExpressionsCheck extends AbstractCheck {

  private int max = 0;
  private int variableCount = 0;

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

  public void setMax(int limit) {
    max = limit;
  }

  @Override
  public void visitToken(DetailAST ast) {
    variableCount++;
    
    String message = "Maximum number of expressions: " + this.max;
    log(ast.getLineNo(), message);
  }
}