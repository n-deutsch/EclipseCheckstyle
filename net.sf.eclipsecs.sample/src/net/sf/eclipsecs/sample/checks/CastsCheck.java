package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of casts
public class CastsCheck extends AbstractCheck {

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

  @Override
  public void visitToken(DetailAST ast) {
    castsCount++;
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    String message = "Number of casts: " + this.castsCount;
    log(ast.getLineNo(), message);
  }
}