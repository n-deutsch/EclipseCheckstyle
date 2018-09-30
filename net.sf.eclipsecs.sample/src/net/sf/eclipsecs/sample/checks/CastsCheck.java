package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of casts
public class CastsCheck extends AbstractCheck {

  private int castsCount = 0; //integer that keeps track of TYPECASTs
  
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
    castsCount++; //increase counter
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    //display number of casts to the user
    String message = "Number of casts: " + this.castsCount;
    log(ast.getLineNo(), message);
    castsCount = 0;
  }
}