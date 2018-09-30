package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts total number of expressions
public class ExternalMethodCheck extends AbstractCheck {

  private int externalMethodCount = 0; //default to zero
  
  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.METHOD_CALL };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.METHOD_CALL };
  }

  @Override
  public void visitToken(DetailAST ast) {
    DetailAST firstChild = ast.getFirstChild();
    
    if(firstChild == null)
    { return; }
    
    DetailAST childOfFirstChild = firstChild.getFirstChild();
    
    //we look for a DOT and no "this" in the method call 
    if(firstChild.getType() == TokenTypes.DOT && childOfFirstChild.getType() != TokenTypes.LITERAL_THIS)
    {
      //if there's a dot and no "this", external method!
      externalMethodCount++;
    }
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    //display the number of expressions
    String message = "Number of external method references: " + this.externalMethodCount;
    log(ast.getLineNo(), message);
    externalMethodCount = 0;
  }
}