package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts the total number of variable declarations
public class VariableDeclarationsCheck extends AbstractCheck {

  private int declarationsCount = 0; //default counter is zero

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
    declarationsCount++; //increase counter by one
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    //display number of variable declarations to user
    String message = "Number of variable declarations: " + this.declarationsCount;
    log(ast.getLineNo(), message);
    
    //reset counter
    declarationsCount = 0;
  }
}
