package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopingStatementsCheck extends AbstractCheck {

  private int max = 0;
  private int loopingStatementCount = 0;

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_DO };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_DO };
  }

  public void setMax(int limit) {
    max = limit;
  }

  @Override
  public void visitToken(DetailAST ast) {
    loopingStatementCount++;
    
    String message = "Maximum number of looping statements: " + this.max;
    log(ast.getLineNo(), message);
  }
}
