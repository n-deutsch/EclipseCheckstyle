package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class VariableDeclarationsCheck extends AbstractCheck {

  private int max = 0;
  private int variableCount = 0;

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.VARIABLE_DEF };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.VARIABLE_DEF };
  }

  public void setMax(int limit) {
    max = limit;
  }

  @Override
  public void visitToken(DetailAST ast) {
    variableCount++;
    
    String message = "Maximum number of variable declarations: " + this.max;
    log(ast.getLineNo(), message);
  }
}
