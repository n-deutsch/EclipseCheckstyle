package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//counts total number of expressions
public class LocalMethodCheck extends AbstractCheck {

  private int localMethodCount = 0; //default to zero
  
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
    
    if(firstChild.getType() != TokenTypes.DOT)
    {
      //no DOT operator, this is a local method call
      localMethodCount++;
    }
    else if(firstChild.getType() == TokenTypes.DOT && childOfFirstChild.getType() == TokenTypes.LITERAL_THIS)
    {
      //Dot and LITERAL_THIS? This is a local method call
      localMethodCount++;
    }
  }
  
  @Override
  public void finishTree(DetailAST ast) {
    //display the number of expressions
    String message = "Number of local method references: " + this.localMethodCount;
    log(ast.getLineNo(), message);
    localMethodCount = 0;
  }
}