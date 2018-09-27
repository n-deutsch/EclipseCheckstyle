package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of variable declarations
public class VariableDeclarationsCheck extends AbstractCheck {

  private int declarationCount = 0;

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

  @Override
  public void visitToken(DetailAST ast) {
    declarationCount++;
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    String message = "Number of variable declarations: " + this.declarationCount;
    log(ast.getLineNo(), message);
  }
}
