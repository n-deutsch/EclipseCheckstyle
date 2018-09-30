package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts number of looping statements
public class LoopingStatementsCheck extends AbstractCheck {

  private int loopingStatements = 0; //default counter is zero
  
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

  @Override
  public void visitToken(DetailAST ast) {
    //increase counter
    loopingStatements++;
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    //display # looping statements to user
    String message = "Number of looping statements: " + this.loopingStatements;
    log(ast.getLineNo(), message);
    
    //reset counter
    loopingStatements = 0;
  }
}