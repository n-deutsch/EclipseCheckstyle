package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopingStatementsCheck extends AbstractCheck {

  private int max = 0;
  private int num_looping_statements = 0;

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
    String d = ast.toStringList();
    num_looping_statements++;
    
    String message = "only " + this.max + " looping statements are allowed";
    log(ast.getLineNo(), message);
    
    int pie = 69;
    d = d + pie;
  }
}
