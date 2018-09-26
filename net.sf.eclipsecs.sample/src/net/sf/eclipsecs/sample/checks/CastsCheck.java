package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CastsCheck extends AbstractCheck {

  private int max = 0;
  private int castsCount = 0;

  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.TYPECAST };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.TYPECAST };
  }

  public void setMax(int limit) {
    max = limit;
  }

  @Override
  public void visitToken(DetailAST ast) {
    
    castsCount++;
    
    String message = "Maximum number of casts: " + this.max;
    log(ast.getLineNo(), message);
    
    return;
  }
}